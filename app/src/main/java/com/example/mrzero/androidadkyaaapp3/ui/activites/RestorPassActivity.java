package com.example.mrzero.androidadkyaaapp3.ui.activites;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.model.forgetpassword.ResultForgetPassword;
import com.google.android.material.textfield.TextInputEditText;

public class RestorPassActivity extends AppCompatActivity {
    TextInputEditText edt_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

          edt_email= findViewById(R.id.edt_email);
        Button btn_resetPass= findViewById(R.id.btn_resetPass);
        btn_resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edt_email.getText().toString();

                if (!TextUtils.isEmpty(email)){
                    sendForgetPasswordRequest(email);

                }else {
                    Toast.makeText(RestorPassActivity.this, "الرجاء كتابة الإيميل بشكل صحيح", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void sendForgetPasswordRequest(String email) {
        APIService apiService=  ServiceGenerator.createService(APIService.class);
        Call<ResultForgetPassword> call =  apiService.getForgetPassword(email);
        call.enqueue(new Callback<ResultForgetPassword>() {
            @Override
            public void onResponse(Call<ResultForgetPassword> call, Response<ResultForgetPassword> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RestorPassActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else {
                     Toast.makeText(RestorPassActivity.this, "يرجى التأكد من البريد الالكتروني", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResultForgetPassword> call, Throwable t) {
                Toast.makeText(RestorPassActivity.this, "غير قادر على الإتصال..يرجى التأكد من البريد الالكتروني", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
