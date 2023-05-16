package com.auth.UserAuthentication.service;

import com.auth.UserAuthentication.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityTokenGeneratorImpl implements SecurityTokenGenerator{
    @Override
    public Map<String, String> generateToken(User user) {
        Map<String,String> resultToken = new HashMap<>();
        Map<String, Object> userData = new HashMap<>();
        userData.put("userName",user.getUserName());
        userData.put("password",user.getPassword());
        String myToken = Jwts.builder()  //build web token
                .setClaims(userData)   //build claims  --payload is dev responsibility  aa.bb.cc --aa-header-algo,typ hmac sha256 base64-type of encoding oftoken,bb-payload-contains all information id-,iss-issuer.sub-subject,exp-expiration date--cc-signature -authenticity of token secret held which is the imp
                .setIssuedAt(new Date()) //to record data and time of generation
                .signWith(SignatureAlgorithm.HS512,"SecretKeyForLogin")  //securitykey with algorithm
                .compact(); // to encode it
        resultToken.put("Token",myToken);
        resultToken.put("Message","User Logged In Successfully...");
        return resultToken;
    }
}
