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
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;


@Service
public class UserServiceImp implements UserService{

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    Uuid uuid;

    @Autowired
    EmailService emailService;

    @Autowired
    Email email;

    @Autowired
    Environment environment;

    @Value("${server.address}")
    private String serverAddress;

    @Value("${server.port}")
    private String serverPort;

    @Value("${user.activation.api}")
    private String userActivationApi;

    @Value("${server.http}")
    private String serverHttp;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String getVerificationCode(){
        return uuid.createCode();
    }

    public void addUser(User user){
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserEntity userEntity=modelMapper.map(user,UserEntity.class);
        String name = userEntity.getFirstName();
        userEntity.setVerificationCode(getVerificationCode());
        userEntity.setEnable(false);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity savedUser=userRepository.save(userEntity);
        //use future class here
        try{
            sendVerificationEmail(userEntity);
        } catch ( InterruptedException e){
            e.printStackTrace();

        }
       


    }
    @Async
    public String sendVerificationEmail(UserEntity user) throws MailException, InterruptedException   {
        Thread.sleep(30000);
       
            String url = serverHttp+serverAddress+":"+serverPort+userActivationApi+user.getVerificationCode();
            log.info("User Activation URL {}",url);
            email.setFrom("innovationhnb@gmail.com");
            email.setTo(user.getEmail());
            email.setSubject("Verification Email");
            email.setBody("To confirm your account, please click here : " +url);
            try {
                emailService.send(email);
            }catch (MessagingException e){
                e.printStackTrace();
            }
            finally {
                return "sending email";
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

    public List<User> getUserList(){
        List<UserEntity> userList= userRepository.findAll();
        return modelMapper.map(userList, new TypeToken<List<User>>() {}.getType());
    }

    public UserEntity getUserByFirstName(String name){
        if(checkIUserExist(name).isEmpty()){
            return null;
        }
        else {
            UserEntity user = userRepository.findUserByFirstName(name);
            return user;
        }
    }

    public void updateUserByFirstName(String name){
        userRepository.updateUserByFirstName(name);
    }

    public int enableUser(String code){
        return userRepository.enableUser(code);
    }

}
