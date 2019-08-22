package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.model.forgetpassword.ResultForgetPassword;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.Objects;

public class RestorPassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        TextInputEditText edt_email= findViewById(R.id.edt_email);
        String email= Objects.requireNonNull(edt_email.getText()).toString();
        sendForgetPassword(email);


    }


    private void sendForgetPassword(String email) {
        APIService apiService=  ServiceGenerator.createService(APIService.class);
        Call<ResultForgetPassword> call =  apiService.getForgetPassword(email);
        call.enqueue(new Callback<ResultForgetPassword>() {
            @Override
            public void onResponse(Call<ResultForgetPassword> call, Response<ResultForgetPassword> response) {
                if (response.isSuccessful()){
                    Toast.makeText(RestorPassActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Toast.makeText(RestorPassActivity.this, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultForgetPassword> call, Throwable t) {
                Toast.makeText(RestorPassActivity.this, "غير قادر على الإتصال..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
