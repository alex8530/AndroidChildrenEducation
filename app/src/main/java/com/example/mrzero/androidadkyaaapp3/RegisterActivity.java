package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.model.register.ResultRegisterModel;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    TextInputEditText edt_mail,edt_name,edt_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_register= findViewById(R.id.btn_register);
          edt_mail= findViewById(R.id.edt_mail);
          edt_name= findViewById(R.id.edt_name);
          edt_pass= findViewById(R.id.edt_pass);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),Register1Activity.class));
//                finish();
                sendRgisterRequest();


            }
        });

    }

    private void sendRgisterRequest() {
        APIService apiService=  ServiceGenerator.createService(APIService.class);
   Call<ResultRegisterModel>  call =  apiService.createUser(
                edt_name.getText().toString(),
                edt_mail.getText().toString(),
                "2",
                edt_pass.getText().toString()
        );
   
   call.enqueue(new Callback<ResultRegisterModel>() {
       @Override
       public void onResponse(Call<ResultRegisterModel> call, Response<ResultRegisterModel> response) {
           if (response.isSuccessful()){

//              Toast.makeText(RegisterActivity.this, response.body().getData().getApiToken(), Toast.LENGTH_SHORT).show();
             Toast.makeText(RegisterActivity.this, "تمت عملية التسجيل بنجاح !", Toast.LENGTH_SHORT).show();
               
           }else {
               Toast.makeText(RegisterActivity.this,
                       "البيانات المدخلة غير صالحة", Toast.LENGTH_SHORT).show();
           }
       }

       @Override
       public void onFailure(Call<ResultRegisterModel> call, Throwable t) {
           Toast.makeText(RegisterActivity.this,
                   "حدث خطأ بالاتصال ..", Toast.LENGTH_SHORT).show();
       }
   });
    }

}
