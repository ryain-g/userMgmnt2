package com.hnbafrica.userMgmt.service;

import com.hnbafrica.userMgmt.dto.UserRegistrationDto;
import com.hnbafrica.userMgmt.entity.User;

import javax.mail.MessagingException;

public interface UserService {

     String getVerificationCode();

     void addUser(User user);
     String sendVerificationEmail(User user) ;

     String checkIUserExist(String name);

     Iterable<User> getUserList();

     User getUserByFirstName(String name);


     void updateUserByFirstName(String name);

     User enableUser(String name);

}
