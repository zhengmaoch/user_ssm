package com.chang.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class User {
    private String id;

    private String username;

    private String password;

    private String email;

    private Date birthday;

    private String nickname;

    private Date createdtime;
}