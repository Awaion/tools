package com.awaion.demo008.service.impl;

import com.awaion.demo008.domain.Cat;
import com.awaion.demo008.mapper.CatMapper;
import com.awaion.demo008.page.PageResult;
import com.awaion.demo008.query.QueryObject;
import com.awaion.demo008.service.ICatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CatServiceImpl implements ICatService {
    @Autowired
    private CatMapper catMapper;

    public int deleteByPrimaryKey(Long id) {
        return catMapper.deleteByPrimaryKey(id);
    }

    public int insert(Cat record) {
        return catMapper.insert(record);
    }

    public Cat selectByPrimaryKey(Long id) {
        return catMapper.selectByPrimaryKey(id);
    }

    public List<Cat> selectAll() {
        return catMapper.selectAll();
    }

    public int updateByPrimaryKey(Cat record) {
        return catMapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPage(QueryObject qo) {
        Long count = catMapper.queryPageCount(qo);
        if (count <= 0) {
            return new PageResult(0L, Collections.EMPTY_LIST);
        }
        List<Cat> result = catMapper.queryPageData(qo);
        PageResult pageResult = new PageResult(count, result);
        return pageResult;
    }
}
