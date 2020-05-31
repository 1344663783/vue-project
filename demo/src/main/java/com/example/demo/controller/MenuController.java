package com.example.demo.controller;

import com.example.demo.common.constant.Result;
import com.example.demo.output.MenuOutput;
import com.example.demo.output.PromiseOutput;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MenuController {

    @GetMapping("/menu")
    public Result menus(){

        return new Result<>(menuTree());
    }

    @GetMapping("/menu/{list}")
    public Result menus(@PathVariable("list") String list){

        if (list.equals("list")){
            return new Result<>(menuList());
        }else {
            return new Result<>(menuTree());
        }

    }

    public List<MenuOutput> menuTree(){

        List<MenuOutput> list = new ArrayList<>();

        MenuOutput menu = new MenuOutput();
        menu.setId(5);
        menu.setParentId(0);
        menu.setName("房产汇算");
        menu.setOrder(3);
        menu.setLevel("0");
        menu.setPath("house");
        menu.setIcon("iconfont icon-fangchan");

        MenuOutput menu1 = new MenuOutput();
        menu1.setId(6);
        menu1.setParentId(5);
        menu1.setName("收入汇算");
        menu1.setOrder(4);
        menu1.setLevel("1");
        menu1.setPath("income");

        MenuOutput menu2 = new MenuOutput();
        menu2.setId(7);
        menu2.setParentId(5);
        menu2.setName("税率汇算");
        menu2.setOrder(5);
        menu2.setPath("tax");
        menu2.setLevel("1");

        List<MenuOutput> children2 = new ArrayList<>();
        children2.add(menu1);
        children2.add(menu2);

        menu.setChildren(children2);

        MenuOutput menuOutput = new MenuOutput();
        menuOutput.setId(1);
        menuOutput.setParentId(0);
        menuOutput.setName("系统管理");
        menuOutput.setIcon("iconfont icon-xitong");
        menuOutput.setOrder(0);
        menuOutput.setPath("sys");
        menuOutput.setLevel("0");

        MenuOutput menuOutput1 = new MenuOutput();
        menuOutput1.setId(2);
        menuOutput1.setParentId(1);
        menuOutput1.setName("用户管理");
        menuOutput1.setOrder(1);
        menuOutput1.setPath("user");
        menuOutput1.setLevel("1");

        MenuOutput menuOutput2 = new MenuOutput();
        menuOutput2.setId(3);
        menuOutput1.setParentId(1);
        menuOutput2.setName("角色管理");
        menuOutput2.setOrder(2);
        menuOutput2.setPath("role");
        menuOutput2.setLevel("1");

        MenuOutput menuOutput3 = new MenuOutput();
        menuOutput3.setId(4);
        menuOutput1.setParentId(1);
        menuOutput3.setName("权限管理");
        menuOutput3.setOrder(3);
        menuOutput3.setPath("promise");
        menuOutput3.setLevel("1");

        List<MenuOutput> children = new ArrayList<>();
        children.add(menuOutput1);
        children.add(menuOutput2);
        children.add(menuOutput3);

        menuOutput.setChildren(children);

        list.add(menu);
        list.add(menuOutput);
        return list;
    }

    public List<MenuOutput> menuList(){
        List<MenuOutput> promises = new ArrayList<>();
        MenuOutput menu = new MenuOutput();
        menu.setId(5);
        menu.setName("房产汇算");
        menu.setOrder(3);
        menu.setLevel("0");
        menu.setPath("house");
        menu.setIcon("iconfont icon-fangchan");
        promises.add(menu);

        MenuOutput menu1 = new MenuOutput();
        menu1.setId(6);
        menu1.setName("收入汇算");
        menu1.setOrder(4);
        menu1.setLevel("1");
        menu1.setPath("income");
        promises.add(menu1);

        MenuOutput menu2 = new MenuOutput();
        menu2.setId(7);
        menu2.setName("税率汇算");
        menu2.setOrder(5);
        menu2.setPath("tax");
        menu2.setLevel("1");
        promises.add(menu2);

        MenuOutput menuOutput = new MenuOutput();
        menuOutput.setId(1);
        menuOutput.setName("系统管理");
        menuOutput.setIcon("iconfont icon-xitong");
        menuOutput.setOrder(0);
        menuOutput.setPath("sys");
        menuOutput.setLevel("0");
        promises.add(menuOutput);

        MenuOutput menuOutput1 = new MenuOutput();
        menuOutput1.setId(2);
        menuOutput1.setName("用户管理");
        menuOutput1.setOrder(1);
        menuOutput1.setPath("user");
        menuOutput1.setLevel("1");
        promises.add(menuOutput1);

        MenuOutput menuOutput2 = new MenuOutput();
        menuOutput2.setId(3);
        menuOutput2.setName("角色管理");
        menuOutput2.setOrder(2);
        menuOutput2.setPath("role");
        menuOutput2.setLevel("1");
        promises.add(menuOutput2);

        MenuOutput menuOutput3 = new MenuOutput();
        menuOutput3.setId(4);
        menuOutput3.setName("权限管理");
        menuOutput3.setOrder(3);
        menuOutput3.setPath("promise");
        menuOutput3.setLevel("1");
        promises.add(menuOutput3);
        return promises;
    }
}
