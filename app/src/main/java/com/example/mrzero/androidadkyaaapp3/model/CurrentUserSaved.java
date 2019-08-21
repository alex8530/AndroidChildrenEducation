package com.example.mrzero.androidadkyaaapp3.model;

import com.example.mrzero.androidadkyaaapp3.model.login.User;

public class CurrentUserSaved  extends User {
    private  boolean isLogin=false;

    public boolean isLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean login) {
        isLogin = login;
    }
}
