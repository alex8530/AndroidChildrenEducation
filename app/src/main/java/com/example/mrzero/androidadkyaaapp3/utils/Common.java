package com.example.mrzero.androidadkyaaapp3.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mrzero.androidadkyaaapp3.model.login.User;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class Common {
    public static final String KEY_USER_PREF="USER_KEY";

    public static void saveUserDataPreferance(Context context, User user){
        SharedPreferences mPrefs = context.getSharedPreferences("preferanceFileUser",MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(KEY_USER_PREF, json);
        prefsEditor.apply();
    }
    public static User retrieveUserDataPreferance(Context context   ){
        SharedPreferences mPrefs = context.getSharedPreferences("preferanceFileUser",MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString(KEY_USER_PREF, "");
        return  gson.fromJson(json, User.class  );
    }


    public static  Integer classroomId=null;
    public static  Integer genderId=null;




}
