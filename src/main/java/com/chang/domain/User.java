package com.chang.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    private Date birthday;
    private String nikename;
    private Date createdTime;
}
