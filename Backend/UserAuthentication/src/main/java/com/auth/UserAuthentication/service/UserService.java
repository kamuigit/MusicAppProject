package com.auth.UserAuthentication.service;

import com.auth.UserAuthentication.domain.User;
import com.auth.UserAuthentication.exception.UserAlreadyExistsException;
import com.auth.UserAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkLogin(User user) {
        return userRepository.findByUserNameAndPassword(user.getUserName(),user.getPassword());
    }

    @Override
    public User registerAccount(User user) {
//        if(userRepository.findById(user.getUserName()).isPresent()){
//            throw new UserAlreadyExistsException();
//        }
        return userRepository.save(user);
    }
}
