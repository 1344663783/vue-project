package com.example.demo.controller;

import com.example.demo.common.constant.Result;
import com.example.demo.output.MenuOutput;
import com.example.demo.output.RoleOutput;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class RoleController {

    @Resource
    private MenuController menuController;

    private static List<RoleOutput> roles = new ArrayList<>();
    static {
        RoleOutput roleOutput = new RoleOutput();
        roleOutput.setRoleId(1);
        roleOutput.setDesc("主管京津冀事务");
        roleOutput.setRoleName("京津冀销售主管");

        roleOutput.setChildren(menuRole());

        RoleOutput roleOutput2 = new RoleOutput();
        roleOutput2.setRoleId(2);
        roleOutput2.setDesc("地产销售");
        roleOutput2.setRoleName("销售经理");
        roleOutput2.setChildren(menuRole1());

        roles.add(roleOutput);
        roles.add(roleOutput2);
    }


    @GetMapping("/user/roles")
    public Result getRoleList(){
        return new Result<>(roles);
    }

    @GetMapping("/role/changePromise")
    public Result changePromise(@RequestParam("roleId") Integer roleId,
                                @RequestParam("menuId") String menuId){
        String[] split = menuId.split(",");

        List<MenuOutput> menuOutputs = menuController.menuTree();

        List<MenuOutput> objects = new ArrayList<>();

        for (String str : split){
            for (MenuOutput menuOutput: menuOutputs){
                if (menuOutput.getId().equals(Integer.valueOf(str))){
                    objects.add(menuOutput);
                }
            }
        }

        List<MenuOutput> objects2 = new ArrayList<>();
        for (String str : split){
            for (MenuOutput menuOutput: menuOutputs){
                for (MenuOutput menuOutput1: menuOutput.getChildren()){
                    if (Integer.valueOf(str).equals(menuOutput1.getId())){
                        objects2.add(menuOutput1);
                    }
                }
            }
        }
        for (MenuOutput m1:objects){
            m1.setChildren(new ArrayList<>());
        }

        for (MenuOutput m1:objects){
            for (MenuOutput m2 : objects2){
                if (m1.getId().equals(m2.getParentId())){
                    m1.getChildren().add(m2);
                }
            }
        }

        for (RoleOutput roleOutput: roles){
            if (roleId.equals(roleOutput.getRoleId())){
                roleOutput.setChildren(objects);
            }
        }

        return new Result<>(objects);

    }

    @GetMapping("/role/deletePromise")
    public Result deletePromise(@RequestParam("roleId") Integer roleId,
                                @RequestParam("menuId") Integer menuId){

        for (RoleOutput role : roles){
            if (role.getRoleId().equals(roleId)){
                for (MenuOutput menu:role.getChildren()){
                    if (menu.getId().equals(menuId)){
                        role.getChildren().remove(menu);
                        break;
                    }
                    for (MenuOutput menu1 : menu.getChildren()){
                        if (menu1.getId().equals(menuId)){
                            menu.getChildren().remove(menu1);
                            break;
                        }
                    }

                }
            }
        }


        for (RoleOutput role : roles){
            if (role.getRoleId().equals(roleId)){
                return new Result<>(role);
            }
        }
        return new Result<>("ok");
    }


    private static List<MenuOutput> menuRole1(){
        List<MenuOutput> promises = new ArrayList<>();

        MenuOutput menu = new MenuOutput();
        menu.setId(4);
        menu.setName("房产汇算");
        menu.setOrder(3);
        menu.setLevel("0");
        menu.setPath("house");
        menu.setIcon("iconfont icon-fangchan");


        MenuOutput menu1 = new MenuOutput();
        menu1.setId(5);
        menu1.setName("收入汇算");
        menu1.setOrder(4);
        menu1.setLevel("1");
        menu1.setPath("income");

        MenuOutput menu2 = new MenuOutput();
        menu2.setId(6);
        menu2.setName("税率汇算");
        menu2.setOrder(5);
        menu2.setPath("tax");
        menu2.setLevel("1");

        List<MenuOutput> objects = new ArrayList<>();
        objects.add(menu1);
        objects.add(menu2);
        menu.setChildren(objects);

        promises.add(menu);
        return promises;
    }

    private static List<MenuOutput> menuRole(){
        List<MenuOutput> promises = new ArrayList<>();

        MenuOutput menu = new MenuOutput();
        menu.setId(4);
        menu.setName("房产汇算");
        menu.setOrder(3);
        menu.setLevel("0");
        menu.setPath("house");
        menu.setIcon("iconfont icon-fangchan");


        MenuOutput menu1 = new MenuOutput();
        menu1.setId(5);
        menu1.setName("收入汇算");
        menu1.setOrder(4);
        menu1.setLevel("1");
        menu1.setPath("income");

        MenuOutput menu2 = new MenuOutput();
        menu2.setId(6);
        menu2.setName("税率汇算");
        menu2.setOrder(5);
        menu2.setPath("tax");
        menu2.setLevel("1");

        List<MenuOutput> objects = new ArrayList<>();
        objects.add(menu1);
        objects.add(menu2);

        menu.setChildren(objects);
        promises.add(menu);

        MenuOutput menuOutput = new MenuOutput();
        menuOutput.setId(1);
        menuOutput.setName("系统管理");
        menuOutput.setIcon("iconfont icon-xitong");
        menuOutput.setOrder(0);
        menuOutput.setPath("sys");
        menuOutput.setLevel("0");

        MenuOutput menuOutput1 = new MenuOutput();
        menuOutput1.setId(2);
        menuOutput1.setName("用户管理");
        menuOutput1.setOrder(1);
        menuOutput1.setPath("user");
        menuOutput1.setLevel("1");

        MenuOutput menuOutput2 = new MenuOutput();
        menuOutput2.setId(3);
        menuOutput2.setName("角色管理");
        menuOutput2.setOrder(2);
        menuOutput2.setPath("role");
        menuOutput2.setLevel("1");

        MenuOutput menuOutput3 = new MenuOutput();
        menuOutput3.setId(4);
        menuOutput3.setName("权限管理");
        menuOutput3.setOrder(3);
        menuOutput3.setPath("promise");
        menuOutput3.setLevel("1");

        List<MenuOutput> objects2 = new ArrayList<>();
        objects2.add(menuOutput1);
        objects2.add(menuOutput2);
        objects2.add(menuOutput3);
        menuOutput.setChildren(objects2);
        promises.add(menuOutput);
        return promises;
    }

    @GetMapping("/getRolePromise/{roleId}")
    public Result getRolePromise(@PathVariable("roleId") Integer roleId){

        List<Integer> list = new ArrayList<>();
        for (RoleOutput role : roles){
            if (role.getRoleId().equals(roleId)){
                for (MenuOutput menuOutput : role.getChildren()){
                   for (MenuOutput menuOutput1:menuOutput.getChildren()){
                       list.add(menuOutput1.getId());
                   }
                }
            }
        }
        return new Result<>(list);
    }
}
