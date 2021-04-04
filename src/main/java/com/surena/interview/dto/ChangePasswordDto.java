package com.surena.interview.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ChangePasswordDto {
    @NotBlank(message = "نام کاربری نمی تواند خالی باشد")
    @ApiModelProperty(required = true, example = "mavi")
    private String username;

    @NotBlank(message = "رمز عبور قبلی نمی تواند خالی باشد")
    @ApiModelProperty(required = true, example = "123")
    private String oldPassword;

    @NotBlank(message = "رمز عبور جدید نمی تواند خالی باشد")
    @ApiModelProperty(required = true, example = "123@45")
    private String newPassword;
}
