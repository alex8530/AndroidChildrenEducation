package com.example.mrzero.androidadkyaaapp3.api;

import com.example.mrzero.androidadkyaaapp3.model.login.ResultLoginModel;
import com.example.mrzero.androidadkyaaapp3.model.register.ResultRegisterModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APIService {
    //The register call
    @FormUrlEncoded
    @POST("register")
    Call<ResultRegisterModel> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("country") String country,
            @Field("password") String password);


    @FormUrlEncoded
    @POST("login")
    Call<ResultLoginModel> loginUser(
             @Field("email") String email,
             @Field("password") String password);
}
