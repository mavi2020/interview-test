package com.surena.interview.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserDto {
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Date createDate;
    private Date modifiedDate;
}
