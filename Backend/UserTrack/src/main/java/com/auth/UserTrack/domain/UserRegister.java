package com.auth.UserTrack.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class UserRegister {
    @Id
    private String userName;
    private String password;
    private String email;
    private String gender;
    private int age;
    private long phone;
    private String address;
}
