package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.model.login.ResultLoginModel;
import com.example.mrzero.androidadkyaaapp3.model.login.User;
import com.example.mrzero.androidadkyaaapp3.model.register.ResultRegisterModel;
import com.example.mrzero.androidadkyaaapp3.utils.Common;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edt_mail, edt_pass;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        setContentView(R.layout.activity_login);

        Button btn_enter= findViewById(R.id.btn_register);
        TextView btn_forget_pass= findViewById(R.id.txt_forget_pass);
        LinearLayout liner_layout_btns= findViewById(R.id.liner_layout_btns);

        edt_mail= findViewById(R.id.edt_mail);
        edt_pass= findViewById(R.id.edt_pass);


        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendLoginRequest();
            }
        });

        btn_forget_pass.setOnClickListener(new View.OnClickListener() {
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

                     Toast.makeText(LoginActivity.this, "تمت عملية الدخول بنجاح !", Toast.LENGTH_SHORT).show();

                     //SAVE USER DATA WITH TOKEN
                    String token= response.body().getData().getApiToken();
                    User userLogin= new User();
                    userLogin.setRememberToken(token);
                    //todo add isLoginLocal to user to know is login or not
                    Common.saveUserDataPreferance(getApplicationContext(),userLogin);

                    Intent  intent =new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);


                }else {
                    Toast.makeText(LoginActivity.this,
                            "البيانات المدخلة غير صالحة", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultLoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this,
                        "حدث خطأ بالاتصال ..", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

