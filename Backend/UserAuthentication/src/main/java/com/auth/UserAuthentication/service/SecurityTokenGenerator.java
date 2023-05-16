package com.auth.UserAuthentication.service;

import com.auth.UserAuthentication.domain.User;


import java.util.Map;

public interface SecurityTokenGenerator {

    Map<String,String> generateToken(User user);
}
