package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mrzero.androidadkyaaapp3.utils.Common;

public class Login1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        Button btn_enter= findViewById(R.id.btn_enter);
        Button btn_register= findViewById(R.id.btn_register);



        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            //check if user has login current !!
                if (Common.retrieveUserDataPreferance(getApplicationContext()).isLogin()){
                   startActivity(new Intent(getApplicationContext(), HomeActivity.class));

                }else {
                   startActivity(new Intent(getApplicationContext(), LoginActivity.class));

                }


            }
        });




        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            }
        });



    }


}
