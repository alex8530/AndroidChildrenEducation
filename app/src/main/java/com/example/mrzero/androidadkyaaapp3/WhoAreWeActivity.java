package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class WhoAreWeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_are_we);

        LinearLayout linearlayout_back= findViewById(R.id.linearlayout_back);
        linearlayout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

    }
}
