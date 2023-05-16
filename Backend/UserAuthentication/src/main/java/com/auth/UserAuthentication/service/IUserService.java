package com.auth.UserAuthentication.service;

import com.auth.UserAuthentication.domain.User;
import com.auth.UserAuthentication.exception.UserAlreadyExistsException;

public interface IUserService {
    public User checkLogin(User user);
    public User registerAccount(User user);

}
