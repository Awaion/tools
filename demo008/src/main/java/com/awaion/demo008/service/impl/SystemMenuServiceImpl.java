package com.awaion.demo008.service.impl;

import com.awaion.demo008.domain.SystemMenu;
import com.awaion.demo008.mapper.SystemMenuMapper;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.QueryObject;
import com.awaion.demo008.service.ISystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SystemMenuServiceImpl implements ISystemMenuService {
    @Autowired
    private SystemMenuMapper systemMenuMapper;

    public int deleteByPrimaryKey(Long id) {
        return systemMenuMapper.deleteByPrimaryKey(id);
    }

    public int insert(SystemMenu record) {
        return systemMenuMapper.insert(record);
    }

    public SystemMenu selectByPrimaryKey(Long id) {
        return systemMenuMapper.selectByPrimaryKey(id);
    }

    public List<SystemMenu> selectAll() {
        return systemMenuMapper.selectAll();
    }

    public int updateByPrimaryKey(SystemMenu record) {
        return systemMenuMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = systemMenuMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<SystemMenu> result = systemMenuMapper.queryPageDataResult(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }

    @Override
    public List<SystemMenu> queryTree() {
        return systemMenuMapper.queryTree();
    }

    @Override
    public List<SystemMenu> queryForRole() {
        return systemMenuMapper.queryTree();
    }

    @Override
    public List<Long> queryMenuIdsListForRole(Long roleId) {
        return systemMenuMapper.systemMenuMapper(roleId);
    }

    @Override
    public List<SystemMenu> indexMenu() {
        return systemMenuMapper.queryTree();
    }

    @Override
    public List<Long> queryIdsByUserId(Long id) {
        return systemMenuMapper.queryMenuIdListByEmployeeId(id);
    }
}
