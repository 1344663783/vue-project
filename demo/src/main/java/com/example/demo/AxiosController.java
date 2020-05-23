package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("axios")
@CrossOrigin
public class AxiosController {

    @GetMapping("/getUser")
    public User getUser(@RequestParam("id") Integer id){
        System.out.println("收到get请求");
        User user = new User();
        user.setId(1);
        user.setName("栗子");
        user.setDept("研发部");
        return user;
    }

    @GetMapping("/getData/{id}")
    public User getUserRest(@PathVariable("id") Integer id){
        System.out.println("收到rest请求");
        User user = new User();
        user.setId(1);
        user.setName("栗子");
        user.setDept("研发部");
        return user;
    }

    @PostMapping("/saveUser")
    public User saveUser(@RequestBody User user){
        System.out.println("收到post请求");
        System.out.println(user);
        return user;
    }
}
