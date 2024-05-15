package com.awaion.demo010.base.query;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SystemDictionaryQueryObject extends QueryObject {
    private String keyword;
    private Long parentId;


}
