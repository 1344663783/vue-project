package com.example.demo.output;

import lombok.Data;

import java.util.List;

@Data
public class RoleOutput {

    private Integer roleId;

    private String roleName;

    private String desc;

    private List<MenuOutput> children;


}
