package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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



        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.row_spinner_item2,getResources().getStringArray(R.array.type_arrays)
        );
        spinnerArrayAdapter.setDropDownViewResource(R.layout.row_spinner_item2);
        spinner.setAdapter(spinnerArrayAdapter);





    }
}
