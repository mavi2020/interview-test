package com.surena.interview.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePasswordDto {
    private String username;
    private String oldPassword;
    private String newPassword;
}
