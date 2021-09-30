package com.hnbafrica.userMgmt.service;

import com.hnbafrica.userMgmt.entity.Role;
import com.hnbafrica.userMgmt.entity.User;
import com.hnbafrica.userMgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;


    public void addUser(User user){
     userRepository.save(user);
    }

    public String checkIUserExist(String name){
       String userName = userRepository.findUserByFirstName(name).getFirstName();
       if(userName.contains(name)){
           return userName;
       }
       else{
           return null;
       }
    }

    public Iterable<User> getUserList(){
       Iterable<User> userList= userRepository.findAll();
       return userList;
    }

    public User getUserByFirstName(String name){
        if(checkIUserExist(name).isEmpty()){
            return null;
        }
        else {
            User user = userRepository.findUserByFirstName(name);
            return user;
        }


    }

    public void updateUserByFirstName(String name){
        userRepository.updateUserByFirstName(name);
    }
}
