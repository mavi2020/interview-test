package com.surena.interview.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UserDto {
    private long id;

    @NotBlank(message = "نام کاربری نمی تواند خالی باشد")
    @NotNull(message = "نام کاربری الزامی است")
    @ApiModelProperty(required = true, example = "mavi")
    private String username;

    @NotBlank(message = "رمز عبور نمی تواند خالی باشد")
    @NotNull(message = "رمز عبور الزامی است")
    @ApiModelProperty(required = true, example = "123@45")
    private String password;

    @NotBlank(message = "نام نمی تواند خالی باشد")
    @NotNull(message = "نام الزامی است")
    @ApiModelProperty(required = true, example = "shima")
    private String firstName;

    @NotBlank(message = "نام خانوادگی نمی تواند خالی باشد")
    @NotNull(message = "نام خانوادگی الزامی است")
    @ApiModelProperty(required = true, example = "mousavi")
    private String lastName;

    private Date createDate;
    private Date modifiedDate;
}
