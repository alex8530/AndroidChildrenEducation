package com.example.mrzero.androidadkyaaapp3.ui.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.google.android.material.textfield.TextInputEditText;

public class ContactUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        LinearLayout linearlayout_back= findViewById(R.id.linearlayout_back);
        linearlayout_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        TextInputEditText txt_message = findViewById(R.id.txt_message);

        txt_message.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b==true){
                    Toast.makeText(ContactUsActivity.this, "foces true", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(ContactUsActivity.this, "foces false", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }
}
