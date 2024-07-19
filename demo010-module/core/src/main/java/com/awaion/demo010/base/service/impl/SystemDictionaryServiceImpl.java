package com.awaion.demo010.base.service.impl;

import com.awaion.demo010.base.domain.SystemDictionary;
import com.awaion.demo010.base.domain.SystemDictionaryItem;
import com.awaion.demo010.base.mapper.SystemdictionaryItemMapper;
import com.awaion.demo010.base.mapper.SystemdictionaryMapper;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.SystemDictionaryQueryObject;
import com.awaion.demo010.base.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {

    @Autowired
    private SystemdictionaryMapper systemdictionaryMapper;

    @Autowired
    private SystemdictionaryItemMapper systemdictionaryItemMapper;


    @Override
    public PageResult advanQueryItem(SystemDictionaryQueryObject qo) {
        //查询总的记录条数
        Integer count = systemdictionaryItemMapper.queryCount(qo);
        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<SystemDictionaryItem> data = systemdictionaryItemMapper.queryData(qo);
        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void saveOrUpdateItem(SystemDictionaryItem item) {
        Long id = item.getId();
        if (id == null) {
            //如果没有id--->新增
            systemdictionaryItemMapper.insert(item);

        } else {
            //如果有id--->编辑
            systemdictionaryItemMapper.updateByPrimaryKey(item);
        }
    }


    //=======================================================================


    @Override
    public PageResult advanQuery(SystemDictionaryQueryObject qo) {


        //查询总的记录条数
        Integer count = systemdictionaryMapper.queryCount(qo);
        if (count == 0) {
            return new PageResult(Collections.EMPTY_LIST, 0, 1, qo.getPageSize());
        }
        //查询所有的对象形成的集合
        List<SystemDictionary> data = systemdictionaryMapper.queryData(qo);

        return new PageResult(data, count, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public List<SystemDictionary> listAllDics() {
        return systemdictionaryMapper.selectAll();
    }


    @Override
    public void saveOrUpdate(SystemDictionary sd) {
        Long id = sd.getId();

        if (id == null) {
            //如果没有id--->新增
            systemdictionaryMapper.insert(sd);

        } else {
            //如果有id--->编辑
            systemdictionaryMapper.updateByPrimaryKey(sd);
        }
    }

    //----------------------------
    @Override
    public List<SystemDictionaryItem> listItemsBySn(String sn) {

        return systemdictionaryItemMapper.listItemsBySn(sn);
    }


}
