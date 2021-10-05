package com.hnbafrica.userMgmt.service;


import com.hnbafrica.userMgmt.Utility.Email;
import com.hnbafrica.userMgmt.Utility.EmailService;
import com.hnbafrica.userMgmt.Utility.Uuid;
import com.hnbafrica.userMgmt.dto.User;
import com.hnbafrica.userMgmt.entity.UserEntity;
import com.hnbafrica.userMgmt.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

public interface UserService {


         String getVerificationCode();
         void addUser(User user);
         String sendVerificationEmail(UserEntity user);

         String checkIUserExist(String name);

         List<User> getUserList();

         UserEntity getUserByFirstName(String name);

         void updateUserByFirstName(String name);

         int enableUser(String code);


}
