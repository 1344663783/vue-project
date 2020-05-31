package com.example.demo.controller;

import com.example.demo.common.constant.Result;
import com.example.demo.input.UserInput;
import com.example.demo.input.UserSearchInput;
import com.example.demo.output.UserOutput;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    static private List<UserOutput> users= new ArrayList<>();
    static {
        list();
    }

    @PostMapping("/user/list")
    public Result<List<UserOutput>> getUserList(@RequestBody UserSearchInput userSearchInput){

        if (userSearchInput.getUsername()!=null && !userSearchInput.getUsername().equals("")){
            List<UserOutput> users2= new ArrayList<>();
            for (UserOutput userOutput1 : users){
                if (userOutput1.getUsername().contains(userSearchInput.getUsername())){
                    users2.add(userOutput1);
                }
            }
            return new Result<>(users2);
        }

        return new Result<>(users);
    }

    @PostMapping("/user/add")
    public Result addUser(@RequestBody UserInput userInput){
        UserOutput userOutput1 = new UserOutput();
        userOutput1.setUsername(userInput.getUsername());

        users.add(userOutput1);

        return new Result<>("ok");
    }

    @GetMapping("/user/edit/{userId}")
    public Result editUserTo(@PathVariable("userId") Integer userId){
        AtomicReference<UserOutput> userOutput=new AtomicReference();
        users.forEach((user) -> {
            if (userId.equals(user.getId())) {
                userOutput.set(user);
            }
        });
        return new Result<>(userOutput.get());
    }

    @PostMapping("user/editUser")
    public Result editUser(@RequestBody UserInput userInput){
      users.forEach(userOutput -> {
          if (userInput.getId().equals(userOutput.getId())){
              userOutput.setUsername(userInput.getUsername());
          }
      });
      return new Result<>(users);
    }

    @GetMapping("/user/delete/{id}")
    public Result deleteUser(@PathVariable("id") Integer id){
        for (UserOutput user : users) {
            if (id.equals(user.getId())){
                users.remove(user);
                break;
            }
        }
        return new Result<>(users);
    }

    @GetMapping("/user/updateRole")
    public Result updateRole(@RequestParam("userId") Integer userId,
                             @RequestParam("roleName") String roleName){
        for (UserOutput userOutput:users){
            if (userOutput.getId().equals(userId)){
                userOutput.setRoleName(roleName);
            }
        }
        return new Result<>("ok");
    }



    public static void list(){
        UserOutput userOutput = new UserOutput();
        userOutput.setId(1);
        userOutput.setUsername("mic");
        userOutput.setAge(31);
        userOutput.setCity("长沙");
        userOutput.setSex(1);
        userOutput.setRoleName("京津冀销售主管");

        UserOutput userOutput2 = new UserOutput();
        userOutput2.setId(2);
        userOutput2.setUsername("tom");
        userOutput2.setAge(32);
        userOutput2.setCity("长沙");
        userOutput2.setSex(2);
        userOutput2.setRoleName("销售经理");
        users.add(userOutput);
        users.add(userOutput2);
    }
}
