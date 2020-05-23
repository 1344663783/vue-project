package com.example.demo;

import com.example.demo.common.constant.Result;
import com.example.demo.common.constant.UserEnum;
import com.example.demo.input.UserInput;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class Login {

    @PostMapping("/login")
    public Result login(@RequestBody UserInput user){
        if (user.getUsername().equals("admin") && user.getPassword().equals("123456")){
            Map<String,String> map=  new HashMap<>();
            map.put("token","xsfetujfgsjkgukgmitj");
            return new Result<>(map);
        }else{
            return new Result<>(UserEnum.LOGIN_FAIL);
        }

    }
}
