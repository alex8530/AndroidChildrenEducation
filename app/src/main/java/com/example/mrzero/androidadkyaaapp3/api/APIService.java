package com.example.mrzero.androidadkyaaapp3.api;

import com.example.mrzero.androidadkyaaapp3.model.aboutus.ResultAboutUs;
import com.example.mrzero.androidadkyaaapp3.model.country.ResultCountryModel;
import com.example.mrzero.androidadkyaaapp3.model.forgetpassword.ResultForgetPassword;
import com.example.mrzero.androidadkyaaapp3.model.getMaterial.ResultGetMaterial;
import com.example.mrzero.androidadkyaaapp3.model.getQuestion.ResultGetQuestion;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.ResultGetSecondMaterial;
import com.example.mrzero.androidadkyaaapp3.model.getStatistics.ResultGetStatistics;
import com.example.mrzero.androidadkyaaapp3.model.getanswer.ResultGetAnswer;
import com.example.mrzero.androidadkyaaapp3.model.login.ResultLoginModel;
import com.example.mrzero.androidadkyaaapp3.model.login.User;
import com.example.mrzero.androidadkyaaapp3.model.register.ResultRegisterModel;
import com.example.mrzero.androidadkyaaapp3.model.updateuser.ResultUpdateUser;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

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
    @POST("student/complete-profile")
    Call<ResponseBody> completeProfile(
            @Part("class_id") RequestBody class_id,
            @Part("gender_id") RequestBody gender_id,
            @Part  MultipartBody.Part file
    );





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



    //because the return object same as login,, so ResultLoginModel is used
    @Multipart
    @POST("student/profile")
    Call<ResultUpdateUser> UpdateProfile(@Part("name")  RequestBody name,
                                         @Part("email")  RequestBody email,
                                         @Part("class_id")  RequestBody class_id,
                                         @Part("country_id")  RequestBody country_id,
                                         @Part("gender_id")  RequestBody gender_id,
                                         @Part  MultipartBody.Part file
    );



    @Headers({
            "Accept: application/json"
    })
    @GET("educational/material")
    Call<ResultGetMaterial> getMaterial();

    @GET("pages/about-us")
    Call<ResultAboutUs> getAboutUs();


    @GET("educational/{id}/secondary-material")
    Call<ResultGetSecondMaterial> getSecondMaterial(@Path("id") String id);



    @GET("educational/{material_id}/{secondary_material_id}/{section_id}/questions")
    Call<ResultGetQuestion> getQuestion(
            @Path("material_id") String material_id,
            @Path("secondary_material_id") String secondary_material_id,
            @Path("section_id") String section_id
    );


    @FormUrlEncoded
    @POST("educational/answer-check")
    Call<ResultGetAnswer> getAnswer(
            @Field("question_id") String question_id ,
            @Field("option_id") String option_id ,
            @Field("is_repeat") String is_repeat ,
            @Field("time_elapsed") String time_elapsed
    );



    @GET("/student/statistics")
    Call<ResultGetStatistics> getStatistics();


}
