package com.awaion.demo009.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class BaseDomain implements Serializable {
    private Long id;
}
