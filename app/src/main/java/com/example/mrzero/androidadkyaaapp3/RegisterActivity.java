package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button btn_register= findViewById(R.id.btn_register);

        //to change the color of sppiner

        // Get reference of widgets from XML layout
        Spinner spinner = (Spinner) findViewById(R.id.spinner);



        // Initializing an ArrayAdapter
//        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
//                this,R.layout.row_spinner_item,getResources().getStringArray(R.array.country_arrays)
//        );
//        spinnerArrayAdapter.setDropDownViewResource(R.layout.row_spinner_item);
//        spinner.setAdapter(spinnerArrayAdapter);
//
//
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Register1Activity.class));
                finish();

            }
        });

    }

}
