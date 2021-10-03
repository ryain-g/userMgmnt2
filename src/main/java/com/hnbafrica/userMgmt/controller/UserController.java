package com.hnbafrica.userMgmt.controller;


import com.hnbafrica.userMgmt.entity.User;
import com.hnbafrica.userMgmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping(path="/users")
public class UserController {
    @Autowired
    UserService userService;
//    @Autowired
//    ConfirmCode confirmCode;

    @PostMapping(path="/addUser")
    public void addNewUser (@RequestBody User user) {
        userService.addUser(user);
        try{
        userService.sendVerificationEmail(user);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
//    @GetMapping (path="confirm-code")
//    @ResponseBody
//    public void confirmCode(){
//        confirmCode.
//
//    }

    @GetMapping(path="/allUsers")
    @ResponseBody
    public Iterable<User> getAllUser() {
        return userService.getUserList();
    }


    @GetMapping(path="/getUser/{name}")
    @ResponseBody
    public User getUserByName(@PathVariable ("name") String name) {
        return userService.getUserByFirstName(name);
    }


    @GetMapping(path="/deleteUser/{name}")
    @ResponseBody
    public void updateUserByName(@PathVariable ("name") String name) {
         userService.updateUserByFirstName(name);
    }
}
