package com.awaion.demo010.base.service;

import com.awaion.demo010.base.domain.SystemDictionary;
import com.awaion.demo010.base.domain.SystemDictionaryItem;
import com.awaion.demo010.base.query.PageResult;
import com.awaion.demo010.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface ISystemDictionaryService {

    PageResult advanQuery(SystemDictionaryQueryObject qo);

    void saveOrUpdate(SystemDictionary sd);

    List<SystemDictionary> listAllDics();


    //-------------------------------------

    PageResult advanQueryItem(SystemDictionaryQueryObject qo);

    void saveOrUpdateItem(SystemDictionaryItem item);


    //----------
    List<SystemDictionaryItem> listItemsBySn(String sn);


}
