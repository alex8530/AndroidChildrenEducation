package com.example.mrzero.androidadkyaaapp3.api;

import com.example.mrzero.androidadkyaaapp3.model.country.ResultCountryModel;
import com.example.mrzero.androidadkyaaapp3.model.forgetpassword.ResultForgetPassword;
import com.example.mrzero.androidadkyaaapp3.model.getMaterial.ResultGetMaterial;
import com.example.mrzero.androidadkyaaapp3.model.login.ResultLoginModel;
import com.example.mrzero.androidadkyaaapp3.model.register.ResultRegisterModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;

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


    /*******************************************************************************/

//
    @Multipart
    @POST("student/profile")
    Call<ResponseBody> completeProfile(
            @Part("class_id") RequestBody class_id,
            @Part("gender_id") RequestBody gender_id,
            @Part  MultipartBody.Part file
    );



    @Headers({
            "Accept: application/json"
    })
    @GET("educational/material")
    Call<ResultGetMaterial> getMaterial();


    @GET("general/countries")
    Call<ResultCountryModel> getAllCountry();


    @FormUrlEncoded
    @POST("password/forget")
    Call<ResultForgetPassword> getForgetPassword(@Field("email") String email);


    @FormUrlEncoded
    @PATCH("password")
    Call<ResponseBody> changePassword(@Field("old_password") String old_password,
                                              @Field("password") String password,
                                              @Field("password_confirmation") String password_confirmation);

}
