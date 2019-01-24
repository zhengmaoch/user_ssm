package com.chang.web.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
public class UserForm {
    private String id;
    private String username;
    private String password;
    private String password2;
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String nickname;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdtime;

    private Map errors = new HashMap();

    public boolean validate(){

        return true;
    }
}
