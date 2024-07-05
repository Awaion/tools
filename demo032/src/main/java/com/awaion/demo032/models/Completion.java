package com.awaion.demo032.models;

import lombok.Getter;

public class Completion {
    @Getter
    private final String completion;// 完成

    public Completion(String completion) {
        this.completion = completion;
    }

}
