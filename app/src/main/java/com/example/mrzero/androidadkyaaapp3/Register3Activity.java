package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Register3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        Button btn_register= findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnimation(HomeActivity.class);
                finish();
            }
        });


    }


    private void startAnimation(Class aClass) {
        ActivityOptions options=null;
        Intent  intent =new Intent(getApplicationContext(), aClass);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            options = ActivityOptions.makeSceneTransitionAnimation(this );
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent );
        }
    }


}
