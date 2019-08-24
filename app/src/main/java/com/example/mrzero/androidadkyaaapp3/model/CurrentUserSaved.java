package com.example.mrzero.androidadkyaaapp3.model;

import com.example.mrzero.androidadkyaaapp3.model.login.User;

public class CurrentUserSaved  extends User {
    private  boolean isLogin=false;
    String country_name;

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean login) {
        isLogin = login;
    }
}
