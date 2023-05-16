package com.auth.UserAuthentication.repository;

import com.auth.UserAuthentication.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    public User findByUserNameAndPassword(String userName, String Password);

}
