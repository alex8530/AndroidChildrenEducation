package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.Arrays;
import java.util.List;

public class Register2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        Button btn_register= findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register3Activity.class));
                finish();

            }
        });


        // Get reference of widgets from XML layout
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        List<String> lists=Arrays.asList(getResources().getStringArray(R.array.type_arrays));
        SpinnerAdapter spinnerAdapter= new com.example.mrzero.androidadkyaaapp3.adapters.SpinnerAdapter(lists,Register2Activity.this);

         spinner.setAdapter(spinnerAdapter);





    }
}
