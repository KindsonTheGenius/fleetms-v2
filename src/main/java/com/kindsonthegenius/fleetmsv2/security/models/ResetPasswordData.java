package com.kindsonthegenius.fleetmsv2.security.models;

import lombok.Data;

@Data
public class ResetPasswordData {

    private String token;
    private String email;
    private String password;
    private String repeatPassword;
}
