package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/send")
public class TestController {

    @GetMapping("/getUser")
    public User getUser(){
        User user = new User();
        user.setId(1);
        user.setName("栗子");
        user.setDept("研发部");
        System.out.println("收到get请求");
        return user;
    }

    @PostMapping("/saveUser")
    public Map saveUser(@RequestBody User user){
        System.out.println("收到post请求");
        System.out.println(user);
        Map<String, String > map = new HashMap<>();
        map.put("post","success");
        return map;
    }
}
