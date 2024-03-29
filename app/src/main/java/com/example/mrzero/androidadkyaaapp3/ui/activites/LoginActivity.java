package com.example.mrzero.androidadkyaaapp3.ui.activites;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.model.CurrentUserSaved;
import com.example.mrzero.androidadkyaaapp3.model.getMaterial.ResultGetMaterial;
import com.example.mrzero.androidadkyaaapp3.model.login.ResultLoginModel;
import com.example.mrzero.androidadkyaaapp3.utils.Common;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edt_mail, edt_pass;
    private static final String TAG = "LoginActivity";
   static String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_login);

        Button btn_enter= findViewById(R.id.btn_register);
        TextView tv_forget_pass= findViewById(R.id.txt_forget_pass);
        LinearLayout liner_layout_btns= findViewById(R.id.liner_layout_btns);

        edt_mail= findViewById(R.id.edt_mail);
        edt_pass= findViewById(R.id.edt_pass);


        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendLoginRequest();
            }
        });

        tv_forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             startActivity(new Intent(getApplicationContext(),RestorPassActivity.class));


            }
        });

        liner_layout_btns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });


    }


    private void sendLoginRequest() {
        APIService apiService=  ServiceGenerator.createService(APIService.class);
        Call<ResultLoginModel> call =  apiService.loginUser(
                 edt_mail.getText().toString(),
                 edt_pass.getText().toString()
        );

        call.enqueue(new Callback<ResultLoginModel>() {
            @Override
            public void onResponse(Call<ResultLoginModel> call, Response<ResultLoginModel> response) {
                if (response.isSuccessful()){

                     Toast.makeText(LoginActivity.this, "تمت عملية تسجيل الدخول بنجاح !" , Toast.LENGTH_SHORT).show();

                     //SAVE USER DATA WITH TOKEN

                    com.example.mrzero.androidadkyaaapp3.model.login.Data data= response.body().getData() ;

                    CurrentUserSaved userSaved= new CurrentUserSaved();
                    userSaved.setIsLogin(true);
                    userSaved.setRememberToken(data.getApiToken());
                    userSaved.setName(data.getUser().getName());
                    userSaved.setEmail(data.getUser().getEmail());
                    userSaved.setId(data.getUser().getId());
                    userSaved.setClassId(data.getUser().getClassId());
                    userSaved.setGenderId(data.getUser().getGenderId());
                    userSaved.setImage(data.getUser().getImage());
                    userSaved.setCountry_name(data.getUser().getAddress().getCountry().getName());
                    userSaved.setCountry_id( data.getUser().getAddress().getCountry().getId() );

                     Common.saveUserDataPreferance(getApplicationContext(),userSaved);


                    Intent  intent =new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);



                }else {

                    try {
                        Toast.makeText(LoginActivity.this,
                                "البيانات المدخلة غير صالحة" + response.errorBody().string() , Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResultLoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "يرجى التحقق من الانترنت", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

