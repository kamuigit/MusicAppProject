package com.auth.UserAuthentication.controller;

import com.auth.UserAuthentication.domain.User;
import com.auth.UserAuthentication.domain.UserDto;
import com.auth.UserAuthentication.exception.UserAlreadyExistsException;
import com.auth.UserAuthentication.service.SecurityTokenGenerator;
import com.auth.UserAuthentication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/user/")
public class UserController {
    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    UserController(UserService userService,SecurityTokenGenerator securityTokenGenerator){
        this.securityTokenGenerator=securityTokenGenerator;
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody UserDto userDto) {
        User user = new User(userDto.getUserName(),userDto.getPassword());
        if(user.getUserName()!=null && user.getPassword()!=null) {
            return new ResponseEntity<>(userService.registerAccount(user), HttpStatus.ACCEPTED);
        }
        else{
            return new ResponseEntity<>("either user or password is null.",HttpStatus.CONFLICT);
        }
        }
        @PostMapping("/login")
        public ResponseEntity<?> loginAccount(@RequestBody User user){
            User retrieved =userService.checkLogin(user);
            if(retrieved!=null){
                System.out.println(retrieved.getUserName()+" "+retrieved.getPassword());
                return new ResponseEntity<>(securityTokenGenerator.generateToken(retrieved),HttpStatus.OK);
            }
            else{
                System.out.println(retrieved.getUserName()+" "+retrieved.getPassword());
                return new ResponseEntity<>("unable to login",HttpStatus.CONFLICT);
            }
        }
}
