package com.reporting.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class UserTo {
    private Integer userId;

    private String userName;

    private String email;

    private Long phone;

    private String password;

    private String role;

    private LocalDate date;


}
