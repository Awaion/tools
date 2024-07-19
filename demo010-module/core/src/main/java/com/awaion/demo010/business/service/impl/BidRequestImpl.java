package com.awaion.demo010.business.service.impl;

import com.awaion.demo010.base.domain.*;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.service.IAccountService;
import com.awaion.demo010.base.service.IUserinfoService;
import com.awaion.demo010.base.util.BitStatesUtils;
import com.awaion.demo010.base.util.UserContext;
import com.awaion.demo010.business.domain.*;
import com.awaion.demo010.business.mapper.*;
import com.awaion.demo010.business.query.BidRequestQueryObject;
import com.awaion.demo010.business.service.IAccountFlowService;
import com.awaion.demo010.business.service.IBidRequestService;
import com.awaion.demo010.business.service.ISystemAccountService;
import com.awaion.demo010.business.util.CalculatetUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static com.awaion.demo010.base.domain.BidConsts.*;

@Service
public class BidRequestImpl implements IBidRequestService {

    @Autowired(required = false)
    private BidRequestMapper bidRequestMapper;

    @Autowired
    private BidMapper bidMapper;

    @Autowired
    private BidRequestAuditMapper bidRequestAuditMapper;

    @Autowired
    private IAccountService accountService;

    @Autowired
    private IUserinfoService userinfoService;

    @Autowired
    private IAccountFlowService accountFlowService;

    @Autowired
    private ISystemAccountService systemAccountService;

    @Autowired
    private PaymentScheduleMapper paymentScheduleMapper;
    @Autowired
    private PaymentScheduleDetailMapper paymentScheduleDetailMapper;


    @Override
    public int insert(BidRequest record) {
        return bidRequestMapper.insert(record);
    }

    @Override
    public BidRequest selectByPrimaryKey(Long id) {
        return bidRequestMapper.selectByPrimaryKey(id);
    }

    @Override
    public int update(BidRequest record) {

        int ret = bidRequestMapper.updateByPrimaryKey(record);

        if (ret <= 0) {
            throw new RuntimeException("乐观锁失效" + record);
        }
        return ret;
    }


    //借款进行申请对应的方法
    @Override
    public void apply(BidRequest br) {

        //得到当前用户
        UserInfo userInfo = userinfoService.selectByPrimaryKey(UserContext.getCurrentUser().getId());

        Account account = accountService.selectByPrimaryKey(UserContext.getCurrentUser().getId());

        //判断当前用户能不能借款
        if (userInfo.getIsRealAuth()
                && userInfo.getIsVedioAuth()
                && userInfo.getIsBasicInfo()
                && userInfo.getScore() >= Consts.CREDIT_BORROW_SCORE_LIMIT
                && !userInfo.getHasBidRequestInProcess() ////用户没有借款在审核流程当中
                && br.getBidRequestAmount().compareTo(account.getBorrowLimit()) <= 0 //借款金额<=剩余信用额度
                && br.getBidRequestAmount().compareTo(BidConsts.SMALLEST_BIDREQUEST_AMOUNT) >= 0 //最小借款金额<=借款金额
                && br.getCurrentRate().compareTo(BidConsts.SMALLEST_CURRENT_RATE) >= 0 //最小年化利息<=年化利息
                && br.getCurrentRate().compareTo(BidConsts.MAX_CURRENT_RATE) <= 0 //年化利息<=最大年化利息
                && br.getMinBidAmount().compareTo(BidConsts.SMALLEST_BID_AMOUNT) >= 0 //最小投标金额>=系统最小投标金额
        ) {
            BidRequest newbr = new BidRequest();//创建一个借款对象

            //设置相关属性

            newbr.setApplyTime(new Date());
            newbr.setBidRequestAmount(br.getBidRequestAmount());
            newbr.setBidRequestState(BIDREQUEST_STATE_PUBLISH_PENDING);
            newbr.setBidRequestType(BidConsts.BIDREQUEST_TYPE_NORMAL);
            newbr.setCreateUser(UserContext.getCurrentUser());
            newbr.setCurrentRate(br.getCurrentRate());
            newbr.setDescription(br.getDescription());
            newbr.setDisableDays(br.getDisableDays());
            newbr.setMonths2Return(br.getMonths2Return());
            newbr.setMinBidAmount(br.getMinBidAmount());
            newbr.setReturnType(br.getReturnType());
            newbr.setTitle(br.getTitle());

            newbr.setTotalRewardAmount(CalculatetUtil.calTotalInterest(
                    newbr.getReturnType(),
                    newbr.getBidRequestAmount(),
                    newbr.getCurrentRate(),
                    newbr.getMonths2Return()));  //出现了by zero错误

            //给用户添加状态码
            userInfo.setBitState(BitStatesUtils.addState(userInfo.getBitState(), BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS));

            bidRequestMapper.insert(newbr);
            userinfoService.updateByPrimaryKey(userInfo);
        }
    }


    @Override
    public PageResult advanQuery(BidRequestQueryObject qo) {

//查询总的记录条数
        Integer count = bidRequestMapper.querycount(qo);
        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<BidRequest> data = bidRequestMapper.querydata(qo);

        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());
    }


    @Override
    public void fullAudit1(int id, String remark, int state) {

        BidRequest br = bidRequestMapper.selectByPrimaryKey((long) id);

        if (br != null && br.getBidRequestState() == BIDREQUEST_STATE_APPROVE_PENDING_1) {

            //得到借款对象对应的审核对象
            BidRequestAudit ba = new BidRequestAudit();

            //设置相关属性
            ba.setState(state);
            ba.setApplyTime(br.getApplyTime());
            ba.setApplier(br.getCreateUser());
            ba.setAuditor(UserContext.getCurrentUser());
            ba.setAuditTime(new Date());
            ba.setRemark(remark);

            ba.setAuditType(BidRequestAudit.TYPE_FULL1);
            ba.setBidRequestId((long) id);

            //保存审核对象
            bidRequestAuditMapper.insert(ba);
            if (state == BidRequestAudit.PASS) {//如果审核成功
                //1,修改借款状态
                br.setBidRequestState(BIDREQUEST_STATE_APPROVE_PENDING_2);  //计入[满标二审]状态
                //2,修改对应的所有投标对象的状态
                bidMapper.updateStates((long) id, BIDREQUEST_STATE_APPROVE_PENDING_2);

            } else {//审核w拒绝
                auditFailedReturnMoney(br);
            }
            update(br);  //更新整个借款标
        }
    }


    public void auditFailedReturnMoney(BidRequest br) {
        //1,遍历投标对象
        for (Bid bid : br.getBids()) {
            Account bidAccount = accountService.selectByPrimaryKey(bid.getBidUser().getId());//这个分标对应的投资人账户
            //2,投资账户可用余额增加,冻结金额减少
            bidAccount.setUsableAmount(bidAccount.getUsableAmount().add(bid.getAvailableAmount()));
            bidAccount.setFreezedAmount(bidAccount.getFreezedAmount().subtract(bid.getAvailableAmount()));
            //3,生成投资失败流水
            accountFlowService.createBidFailedFlow(bidAccount, bid);
            //4,更新投标人的账户
            accountService.updateByPrimaryKey(bidAccount);
        }
        //修改借款状态
        br.setBidRequestState(BidConsts.BIDREQUEST_STATE_REJECTED);
        //修改投标对象状态
        bidMapper.updateStates(br.getId(), BidConsts.BIDREQUEST_STATE_REJECTED);

        //去掉借款人的状态码
        UserInfo userInfo = userinfoService.selectByPrimaryKey(br.getCreateUser().getId()); //获取借款人的状态
        userInfo.setBitState(BitStatesUtils.removeState(userInfo.getBitState(), BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS));
        userinfoService.updateByPrimaryKey(userInfo);

    }


    @Override
    public void fullAudit2(int id, String remark, int state) {

        BidRequest br = bidRequestMapper.selectByPrimaryKey((long) id);
        //判断,借款处于二审的状态
        if (br != null && br.getBidRequestState() == BIDREQUEST_STATE_APPROVE_PENDING_2) {

            //得到借款对象对应的审核对象
            BidRequestAudit ba = new BidRequestAudit();

            //设置相关属性
            /* ba.setApplier(br.getCreateUser());*/
            ba.setApplier(br.getCreateUser());
            ba.setAuditor(UserContext.getCurrentUser());
            ba.setApplyTime(new Date()); //将上一次的审核时间设置为本次的申请时间
            ba.setAuditTime(new Date());
            ba.setAuditType(BidRequestAudit.TYPE_FULL2);
            ba.setRemark(remark);
            ba.setState(state);
            ba.setBidRequestId((long) id);
            //保存审核对象
            bidRequestAuditMapper.insert(ba);

            //如果审核通过

            if (state == BidRequestAudit.PASS) {
                //1,//针对借款对象的变化
                br.setBidRequestState(BIDREQUEST_STATE_PAYING_BACK);  //设置为还款中的状态
                //2,修改对应的所有投标对象的状态
                bidMapper.updateStates((long) id, BIDREQUEST_STATE_PAYING_BACK);

                //2,针对借款人的变化
                //2.1,收款,可用余额增加,生成借款成功流水;
                Account borrowAccount = accountService.selectByPrimaryKey(br.getCreateUser().getId()); //得到借款人的账户
                borrowAccount.setUsableAmount(borrowAccount.getUsableAmount().add(br.getBidRequestAmount()));
                accountFlowService.createBorrowSuccessFlow(borrowAccount, br);  //生成借款成功流水
                //2.2,减少剩余信用额度;
                borrowAccount.setRemainBorrowLimit(borrowAccount.getRemainBorrowLimit().subtract(br.getBidRequestAmount()));
                //2.3,待还本息增加;
                borrowAccount.setUnReturnAmount(borrowAccount.getUnReturnAmount().add(br.getTotalRewardAmount()).add(br.getBidRequestAmount()));
                //2.4,移除借款状态码;
                UserInfo userInfo = userinfoService.selectByPrimaryKey(br.getCreateUser().getId()); //获取借款人的状态
                userInfo.setBitState(BitStatesUtils.removeState(userInfo.getBitState(), BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS));
                //userinfoService.updateByPrimaryKey(userInfo);--->后面要去更新

                //2.5,支付借款手续费,可用余额减少,生成支付流水;
                BigDecimal manageFee = CalculatetUtil.calAccountManagementCharge(br.getBidRequestAmount());
                borrowAccount.setUsableAmount(borrowAccount.getUsableAmount().subtract(manageFee));  //可用金额扣除手续费
                accountFlowService.createManageFeeFlow(borrowAccount, br, manageFee);

                //2.6,平台账户收取借款手续费;平台账户生成流水
                systemAccountService.chargeManageFee(manageFee, br);
                //3,针对投资人的变化
                //3.1遍历投标
                Map<Long, Account> updates = new HashMap<>();

                for (Bid bid : br.getBids()) {
                    Long bidAccountId = bid.getBidUser().getId(); //投标账户对应的用户id
                    Account bidAccount = updates.get(bidAccountId);
                    if (bidAccount == null) {
                        bidAccount = this.accountService.selectByPrimaryKey(bidAccountId);
                        updates.put(bidAccountId, bidAccount);
                    }
                    bidAccount.setFreezedAmount(bidAccount.getFreezedAmount().subtract(bid.getAvailableAmount()));//3.2冻结金额减少,生成投资成功流水

                    accountFlowService.createBidSuccessFlow(bidAccount, bid);
                }

                //4,todo针对还款/回款做些???
                List<PaymentSchedule> pss = this.createPaymentSchedule(br);  //根据这个借款生成对应的还款对象

                //3.3增加待收本金和待收利息todo
                for (PaymentSchedule ps : pss) {
                    for (PaymentScheduleDetail psd : ps.getPaymentScheduleDetails()) {
                        Account bidAccount = updates.get(psd.getToLogininfoId());
                        bidAccount.setUnReceiveInterest(bidAccount.getUnReceiveInterest().add(psd.getInterest()));
                        bidAccount.setUnReceivePrincipal(bidAccount.getUnReceivePrincipal().add(psd.getPrincipal()));
                    }
                }
                for (Account account : updates.values()) {
                    accountService.updateByPrimaryKey(account);
                }
                userinfoService.updateByPrimaryKey(userInfo);
                accountService.updateByPrimaryKey(borrowAccount);

            } else {
                //审核失败,等同于满标一审
                auditFailedReturnMoney(br);
            }
            this.update(br);
        }

    }

    //------------------根据这个借款生成对应的还款对象---------------------------
    private List<PaymentSchedule> createPaymentSchedule(BidRequest br) {

        List<PaymentSchedule> pss = new ArrayList<>();
        BigDecimal totalInterest = BidConsts.ZERO;
        BigDecimal totalPrincipal = BidConsts.ZERO;
        // 循环借款月数,针对每个月,生成一个对应的还款对象
        for (int i = 0; i < br.getMonths2Return(); i++) {
            PaymentSchedule ps = new PaymentSchedule();
            ps.setBidRequestId(br.getId());
            ps.setBidRequestTitle(br.getTitle());
            ps.setBidRequestType(br.getBidRequestType());
            ps.setBorrowUser(br.getCreateUser());
            ps.setDeadLine(DateUtils.addMonths(new Date(), i + 1));
            ps.setMonthIndex(i + 1);
            ps.setReturnType(br.getReturnType());
            ps.setState(BidConsts.PAYMENT_STATE_NORMAL);

            if (i < br.getMonths2Return() - 1) {
                ps.setTotalAmount(CalculatetUtil.calMonthToReturnMoney(ps.getReturnType(), br.getBidRequestAmount(), br.getCurrentRate(), i + 1,
                        br.getMonths2Return()));
                ps.setInterest(CalculatetUtil.calMonthlyInterest(ps.getReturnType(), br.getBidRequestAmount(), br.getCurrentRate(), i + 1,
                        br.getMonths2Return()));
                ps.setPrincipal(ps.getTotalAmount().subtract(ps.getInterest()));
                totalInterest = totalInterest.add(ps.getInterest());
                totalPrincipal = totalPrincipal.add(ps.getPrincipal());
            } else {
                ps.setInterest(br.getTotalRewardAmount().subtract(totalInterest));
                ps.setPrincipal(br.getBidRequestAmount().subtract(totalPrincipal));
                ps.setTotalAmount(ps.getInterest().add(ps.getPrincipal()));
            }
            this.paymentScheduleMapper.insert(ps);
            this.createPaymentScheduleDetail(ps, br);
            pss.add(ps);
        }
        return pss;
    }
    //--------------------------------------------------------------------

    //--------------------------------------------------------------------

    private void createPaymentScheduleDetail(PaymentSchedule ps, BidRequest br) {
        // 遍历投资
        BigDecimal totalInterest = BidConsts.ZERO;
        BigDecimal totalPrincipal = BidConsts.ZERO;
        for (int i = 0; i < br.getBidCount(); i++) {
            Bid bid = br.getBids().get(i);
            PaymentScheduleDetail psd = new PaymentScheduleDetail();

            psd.setBidAmount(bid.getAvailableAmount());
            psd.setBidId(bid.getId());
            psd.setBidRequestId(br.getId());
            psd.setDeadLine(ps.getDeadLine());
            psd.setFromLogininfo(br.getCreateUser());
            psd.setMonthIndex(ps.getMonthIndex());
            psd.setPaymentScheduleId(ps.getId());
            psd.setReturnType(br.getReturnType());
            psd.setToLogininfoId(bid.getBidUser().getId());

            if (i < br.getBidCount() - 1) {
                BigDecimal rate = bid.getAvailableAmount().divide(br.getBidRequestAmount(), BidConsts.CAL_SCALE, RoundingMode.HALF_UP);
                psd.setPrincipal(ps.getPrincipal().multiply(rate).setScale(BidConsts.SAVE_SCALE, RoundingMode.HALF_UP));
                psd.setInterest(ps.getInterest().multiply(rate).setScale(BidConsts.SAVE_SCALE, RoundingMode.HALF_UP));
                psd.setTotalAmount(ps.getPrincipal().add(ps.getInterest()));

                totalInterest = totalInterest.add(psd.getInterest());
                totalPrincipal = totalPrincipal.add(psd.getPrincipal());
            } else {
                psd.setPrincipal(ps.getPrincipal().subtract(totalPrincipal));
                psd.setInterest(ps.getInterest().subtract(totalInterest));
                psd.setTotalAmount(psd.getPrincipal().add(psd.getInterest()));
            }
            this.paymentScheduleDetailMapper.insert(psd);
            ps.getPaymentScheduleDetails().add(psd);
        }
    }

    //--------------------------------------------------------------------


    @Override
    public void publishAudit(int id, String remark, int state) {

        BidRequest br = bidRequestMapper.selectByPrimaryKey((long) id);

        if (br != null && br.getBidRequestState() == BIDREQUEST_STATE_PUBLISH_PENDING) {

            //得到借款对象对应的审核对象
            BidRequestAudit ba = new BidRequestAudit();

            //设置相关属性
            ba.setState(state);
            ba.setApplyTime(br.getApplyTime());
            ba.setApplier(br.getCreateUser());
            ba.setAuditor(UserContext.getCurrentUser());
            ba.setAuditTime(new Date());
            ba.setRemark(remark);

            ba.setAuditType(BidConsts.BIDREQUEST_STATE_PUBLISH_PENDING);
            ba.setBidRequestId((long) id);

            //保存审核对象
            bidRequestAuditMapper.insert(ba);
            //如果审核成功

            if (state == BidRequestAudit.PASS) {
                //1,设置借款状态
                br.setBidRequestState(BIDREQUEST_STATE_BIDDING); //设置为招标中状态
                //2,设置发布时间
                br.setPublishTime(new Date());
                //3,计算招标截止日期
                br.setDisableDate(DateUtils.addDays(new Date(), br.getDisableDays()));
                //4,设置风控意见
                br.setNote(remark);

            } else {
                br.setBidRequestState(BidConsts.BIDREQUEST_STATE_PUBLISH_REFUSE);

                UserInfo currentUserinfo = userinfoService.selectByPrimaryKey(br.getCreateUser().getId());

                currentUserinfo.setBitState(BitStatesUtils.removeState(currentUserinfo.getBitState(), BitStatesUtils.OP_HAS_BIDREQUEST_PROCESS));

                //去掉状态码
                this.userinfoService.updateByPrimaryKey(currentUserinfo);
            }

            update(br);  //更新整个借款标
        }
    }

    @Override
    public List<BidRequestAudit> listBidRequestAuditsByRequestId(Long bidRequestId) {
        return bidRequestAuditMapper.listBidRequestAuditsByRequestId(bidRequestId);
    }

    @Override
    public List<BidRequest> list5items() {

        BidRequestQueryObject qo = new BidRequestQueryObject();
        //设置qo的属性
        qo.setPageSize(0);
        qo.setOrderType("ASC");
        qo.setOrderBy("b.bidRequestState");
        qo.setStates(new int[]{BIDREQUEST_STATE_BIDDING, BidConsts.BIDREQUEST_STATE_PAYING_BACK, BidConsts.BIDREQUEST_STATE_COMPLETE_PAY_BACK});

        return bidRequestMapper.querydata(qo);
    }

    @Override
    public void bid(Long bidRequestId, BigDecimal amount) {

        BidRequest br = bidRequestMapper.selectByPrimaryKey(bidRequestId); ////获取借款对象
        Logininfo current = UserContext.getCurrentUser(); //获取当前操作的用户
        Account account = accountService.selectByPrimaryKey(current.getId()); ////获取当前操作的用户对应的账户

        //进行条件判断
        if (br != null
                && br.getBidRequestState() == BIDREQUEST_STATE_BIDDING //1,借款处于招标中
                && !br.getCreateUser().getId().equals(current.getId()) //2,当前用户不是借款人自己
                && amount.compareTo(br.getRemainAmount().min(account.getUsableAmount())) <= 0 //3,投标金额<min(剩余可投金额,自己账户余额)
                && amount.compareTo(br.getMinBidAmount()) > 0 //4,投标金额>最小投标金额
        ) {
            //投标
            //1,创建一个投标对象
            Bid bid = new Bid();
            //2,设置相关属性
            bid.setActualRate(br.getCurrentRate());
            bid.setAvailableAmount(amount);
            bid.setBidRequestId(bidRequestId);
            bid.setBidRequestState(br.getBidRequestState());
            bid.setBidRequestTitle(br.getTitle());
            bid.setBidTime(new Date());
            bid.setBidUser(current);

            bidMapper.insert(bid);

            //3,投标次数增加,已投资金额增加
            br.setBidCount(br.getBidCount() + 1);
            br.setCurrentSum((br.getCurrentSum().add(amount)));
            //4,用户可用余额减少,冻结金额增加
            account.setUsableAmount(account.getUsableAmount().subtract(amount));
            account.setFreezedAmount(account.getFreezedAmount().add(amount));
            //5,增加一个投标流水
            accountFlowService.createBidFlow(account, bid);

            //6,判断借款是否已经投满了,如果已经投满了,进入满标一审状态
            if (br.getCurrentSum().equals(br.getBidRequestAmount())) {
                br.setBidRequestState(BIDREQUEST_STATE_APPROVE_PENDING_1);
                bidMapper.updateStates(br.getId(), BIDREQUEST_STATE_APPROVE_PENDING_1); //把这个借款对应的所有投标都设置为招标中
            }
            accountService.updateByPrimaryKey(account);
            update(br);  //使用本类中的方法,有乐观锁
        } else {
            throw new RuntimeException("投标失败");
        }
    }



/*
    @Override
    public void bid(Long bidRequestId, BigDecimal amount) {
        // 做条件判断
        BidRequest request = this.bidRequestMapper.selectByPrimaryKey(bidRequestId);
        // 处于投标状态
        if (request != null && request.getBidRequestState() == BidConsts.BIDREQUEST_STATE_BIDDING) {
            // 检查投标资格
            Logininfo current = UserContext.getCurrentUser();
            Account account = this.accountService.selectByPrimaryKey(current.getId());
            if (request.getBidRequestAmount().compareTo(request.getCurrentSum()) > 0// 1，判断该标是否还有余额
                    && amount.compareTo(account.getUsableAmount()) <= 0// 投标金额小于等于用户余额
                    && amount.compareTo(request.getMinBidAmount()) >= 0// 投标金额大于投标最小金额
                    && amount.compareTo(request.getRemainAmount()) <= 0// 投标余额小于等于可投金额
                    && !current.getId().equals(request.getCreateUser().getId())) {//当前用户不是借款人
                // 创建投标对象
                Bid bid = new Bid();
                bid.setActualRate(request.getCurrentRate());
                bid.setAvailableAmount(amount);
                bid.setBidRequestId(request.getId());
                bid.setBidTime(new Date());
                bid.setBidRequestTitle(request.getTitle());
                bid.setBidUser(current);
                this.bidMapper.insert(bid);

                // 修改标的对象数据
                request.setBidCount(request.getBidCount() + 1);
                request.setCurrentSum(request.getCurrentSum().add(amount));
                // 修改用户的账户信息和生成用户账户流水（投标冻结）
                account.setUsableAmount(account.getUsableAmount().subtract(amount));
                account.setFreezedAmount(account.getFreezedAmount().add(amount));
                this.accountService.update(account);
                // 生成冻结流水
                this.accountFlowService.createBidFlow(bid, account);

                // 如果标的投标金额等于标的需要金额，修改标的状态
                if (request.getCurrentSum().equals(request.getBidRequestAmount())) {
                    request.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
                }
                this.update(request);
            }
        }
    }


    @Override
    public void createBidFlow(Bid bid, Account account) {
        AccountFlow flow = new AccountFlow();
        flow.setAccount(account);
        flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_BID_FREEZED);
        flow.setActionTime(new Date());
        flow.setAmount(bid.getAvailableAmount());
        flow.setBalance(account.getUsableAmount());
        flow.setFreezed(account.getFreezedAmount());
        flow.setNote("投标冻结资金");
        this.accountFlowMapper.insert(flow);
    }*/


}
