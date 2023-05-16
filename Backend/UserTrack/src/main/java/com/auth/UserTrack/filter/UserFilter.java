package com.auth.UserTrack.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.rmi.ServerException;

public class UserFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String authHeader = httpServletRequest.getHeader("authorization");
        if (authHeader==null || !authHeader.startsWith("Bearer")){
            throw new ServerException("Token Is Missing");
        }
        else{
            String token = authHeader.substring(7);
            Claims claim =Jwts.parser().setSigningKey("SecretKeyForLogin").parseClaimsJws(token).getBody();
            System.out.println("Retrieved claims :"+claim);
            httpServletRequest.setAttribute("val1",claim.get("userName"));
            httpServletRequest.setAttribute("val2",claim.get("password"));
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
