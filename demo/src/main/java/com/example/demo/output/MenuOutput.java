package com.example.demo.output;

import lombok.Data;

import java.util.List;

@Data
public class MenuOutput {

    private Integer id;

    private String name;

    private String path;

    private Integer order;

    private String icon;

    private String level;

    private Integer parentId;

    private List<MenuOutput> children;


}
