package com.example.mrzero.androidadkyaaapp3.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mrzero.androidadkyaaapp3.model.CurrentUserSaved;

import com.example.mrzero.androidadkyaaapp3.model.getMaterial.MaterialData;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.SecondMaterialData;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.Section;
import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class Common {
    public static final String KEY_USER_PREF="USER_KEY";

    public static void saveUserDataPreferance(Context context, CurrentUserSaved user){
        SharedPreferences mPrefs = context.getSharedPreferences("preferanceFileUser",MODE_PRIVATE);

        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefsEditor.putString(KEY_USER_PREF, json);
        prefsEditor.apply();
    }
    public static CurrentUserSaved retrieveUserDataPreferance(Context context   ){
        SharedPreferences mPrefs = context.getSharedPreferences("preferanceFileUser",MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString(KEY_USER_PREF, "");
        CurrentUserSaved userSaved=  gson.fromJson(json, CurrentUserSaved.class  );
        if (userSaved ==null){
            //that mean no user save before ...
            return new CurrentUserSaved();

        }else {
            return userSaved;
        }
    }


    public static  Integer classroomId=null;
    public static  Integer genderId=null;

    public static   String TEMP_REGISTER_TOKEN ;




  public static MaterialData CurrentMaterial= new MaterialData();
  public static Section CurrentSection= new Section();
  public static SecondMaterialData CurrentSecondMaterial= new SecondMaterialData();





}
