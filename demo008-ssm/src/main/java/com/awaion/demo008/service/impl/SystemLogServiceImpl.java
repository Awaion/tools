package com.awaion.demo008.service.impl;

import com.awaion.demo008.domain.SystemLog;
import com.awaion.demo008.mapper.SystemLogMapper;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.QueryObject;
import com.awaion.demo008.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SystemLogServiceImpl implements ISystemLogService {
    @Autowired
    private SystemLogMapper systemLogMapper;

    public int deleteByPrimaryKey(Long id) {
        return systemLogMapper.deleteByPrimaryKey(id);
    }

    public int insert(SystemLog record) {
        return systemLogMapper.insert(record);
    }

    public SystemLog selectByPrimaryKey(Long id) {
        return systemLogMapper.selectByPrimaryKey(id);
    }

    public List<SystemLog> selectAll() {
        return systemLogMapper.selectAll();
    }

    public int updateByPrimaryKey(SystemLog record) {
        return systemLogMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = systemLogMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<SystemLog> result = systemLogMapper.queryPageData(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }
}
