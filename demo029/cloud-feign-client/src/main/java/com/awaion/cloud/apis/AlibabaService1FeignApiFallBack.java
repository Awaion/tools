package com.awaion.cloud.apis;

import com.awaion.cloud.resp.ResultData;
import com.awaion.cloud.resp.ReturnCodeEnum;
import org.springframework.stereotype.Component;

@Component
public class AlibabaService1FeignApiFallBack implements AlibabaService1FeignApi {
    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.fail(ReturnCodeEnum.RC500.getCode(), "对方服务宕机或不可用，FallBack服务降级o(╥﹏╥)o");
    }
}
