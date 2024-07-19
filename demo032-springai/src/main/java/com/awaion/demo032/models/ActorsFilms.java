package com.awaion.demo032.models;

import lombok.Data;

import java.util.List;

@Data
public class ActorsFilms {
    private String actor;// 演员
    private List<String> movies;// 电影
}
