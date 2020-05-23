package com.example.demo.controller;

import com.example.demo.common.constant.Result;
import com.example.demo.output.MenuOutput;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RelationSupport;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MenuController {

    @GetMapping("/menu")
    public Result menus(){

        List<MenuOutput> list = new ArrayList<>();
        MenuOutput menu = new MenuOutput();
        menu.setId(4);
        menu.setName("房产汇算");
        menu.setOrder(3);
        menu.setPath("house");
        menu.setIcon("iconfont icon-fangchan");
        MenuOutput menu1 = new MenuOutput();
        menu1.setId(5);
        menu1.setName("收入汇算");
        menu1.setOrder(4);
        menu1.setPath("income");
        MenuOutput menu2 = new MenuOutput();
        menu2.setId(6);
        menu2.setName("税率汇算");
        menu2.setOrder(5);
        menu2.setPath("tax");
        List<MenuOutput> children2 = new ArrayList<>();
        children2.add(menu1);
        children2.add(menu2);

        menu.setChildren(children2);

        MenuOutput menuOutput = new MenuOutput();
        menuOutput.setId(1);
        menuOutput.setName("系统管理");
        menuOutput.setIcon("iconfont icon-xitong");
        menuOutput.setOrder(0);
        menuOutput.setPath("sys");
        MenuOutput menuOutput1 = new MenuOutput();
        menuOutput1.setId(2);
        menuOutput1.setName("用户管理");
        menuOutput1.setOrder(1);
        menuOutput1.setPath("user");
        MenuOutput menuOutput2 = new MenuOutput();
        menuOutput2.setId(3);
        menuOutput2.setName("角色管理");
        menuOutput2.setOrder(2);
        menuOutput2.setPath("role");
        List<MenuOutput> children = new ArrayList<>();
        children.add(menuOutput1);
        children.add(menuOutput2);

        menuOutput.setChildren(children);

        list.add(menu);
        list.add(menuOutput);
        return new Result<>(list);
    }
}
