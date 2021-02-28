package com.surena.interview.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "C_Username",length = 20)
    private String username;

    @Column(name = "C_Password",length = 20)
    private String password;

    @Column(name = "C_First_Name", length = 50)
    private String firstName;

    @Column(name = "D_Create_Date",length = 8)
    private String createDate;

    @Column(name = "D_Modified_Date", length = 8)
    private String modifiedDate;

}
