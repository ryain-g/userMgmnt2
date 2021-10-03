package com.hnbafrica.userMgmt.service;


import com.hnbafrica.userMgmt.Utility.Uuid;
import com.hnbafrica.userMgmt.entity.User;
import com.hnbafrica.userMgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.standard.expression.MessageExpression;

import javax.mail.MessagingException;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Uuid uuid;

    @Autowired
    EmailService emailService;

    public String getVerificationCode(){
        return uuid.createCode();
    }

    public void addUser(User user){
        String name = user.getFirstName();
        user.setVerificationCode(getVerificationCode());
        user.setEnable(false);
        userRepository.save(user);
    }
    public String sendVerificationEmail(User user) throws MessagingException{
        try {
            emailService.sendVerificationEmail(user);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        String note = "email is sent";
        return note;

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
