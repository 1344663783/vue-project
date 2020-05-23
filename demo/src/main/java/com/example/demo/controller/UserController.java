package com.example.demo.controller;

import com.example.demo.common.constant.Result;
import com.example.demo.input.UserSearchInput;
import com.example.demo.output.UserOutput;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @PostMapping("/user/list")
    public Result<List<UserOutput>> getUserList(@RequestBody UserSearchInput userSearchInput){
        System.out.println(userSearchInput);
        UserOutput userOutput = new UserOutput();
        userOutput.setId(1);
        userOutput.setUsername("mic");
        userOutput.setAge(31);
        userOutput.setCity("长沙");
        userOutput.setSex(1);

        UserOutput userOutput2 = new UserOutput();
        userOutput2.setId(2);
        userOutput2.setUsername("tom");
        userOutput2.setAge(32);
        userOutput2.setCity("长沙");
        userOutput2.setSex(2);

        List<UserOutput> users= new ArrayList<>();
        users.add(userOutput);
        users.add(userOutput2);
        return new Result<>(users);
    }
}
