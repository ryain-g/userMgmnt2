package com.hnbafrica.userMgmt.service;


import com.hnbafrica.userMgmt.Utility.Uuid;
import com.hnbafrica.userMgmt.dto.UserRegistrationDto;
import com.hnbafrica.userMgmt.entity.User;
import com.hnbafrica.userMgmt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public class UserServiceImp  implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    Uuid uuid;

    @Autowired
    EmailService emailService;

    public String getVerificationCode(){
        return uuid.createCode();
    }

    public void addUser(UserRegistrationDto userRegistrationDto){
        User user = new User(userRegistrationDto.getFirstName(),userRegistrationDto.getLastName(),
                     userRegistrationDto.getPassword(),userRegistrationDto.getEmail(),userRegistrationDto.getPhone());
        userRepository.save(user);
    }
    public String sendVerificationEmail(User user) {
        try {
            emailService.sendVerificationEmail(user);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        finally {
            String note = "email is sent";
            return note;
        }
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

    public User enableUser(String name){
        User user = getUserByFirstName(name);
        user.setEnable(true);
       return userRepository.save(user);
    }

}
