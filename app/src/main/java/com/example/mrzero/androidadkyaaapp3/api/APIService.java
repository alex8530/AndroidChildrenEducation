package com.example.mrzero.androidadkyaaapp3.api;

import com.example.mrzero.androidadkyaaapp3.model.login.ResultLoginModel;
import com.example.mrzero.androidadkyaaapp3.model.register.ResultRegisterModel;

import okhttp3.ResponseBody;
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


    /*******************************************************************************/
    //this is very usefull link to show you how to send image to server
    /// https://stackoverflow.com/questions/40926698/how-to-send-image-file-with-retrofitfields
    //http://www.androidcoding.in/2018/02/17/android-upload-image-using-retrofit-library-part2/


    @FormUrlEncoded
    @POST("student/profile")
    Call<ResponseBody> completeProfile(
            @Field("class_id") String class_id,
            @Field("gender_id") String gender_id,
            @Field("img") String img);



    /****************************************************************************/

}
