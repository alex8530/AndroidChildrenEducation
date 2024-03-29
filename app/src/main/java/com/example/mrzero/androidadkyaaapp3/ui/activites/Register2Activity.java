package com.example.mrzero.androidadkyaaapp3.ui.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.utils.Common;

public class Register2Activity extends AppCompatActivity {

    Integer id  =  null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);

        Button btn_register= findViewById(R.id.btn_register);
        final RadioGroup radio_gruop =   findViewById(R.id.radioGroup);



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get selected item from radio gruop
                int radioButtonID = radio_gruop.getCheckedRadioButtonId();
                View radioButton = radio_gruop.findViewById(radioButtonID);
                int idx = radio_gruop.indexOfChild(radioButton);

                RadioButton r = (RadioButton) radio_gruop.getChildAt(idx);
                //check if user choose any btn , if user does not choose.. r will be null

                if (r!=null){
                    int selectedId=r.getId();
                    switch (selectedId){
                        case R.id.male :
                            id = 2;
                            break;

                        case R.id.female :
                            id = 3;
                            break;

                        default:
                            id=null;
                    }


                    if (id!= null){
                        Common.genderId=id;
                        startActivity(new Intent(getApplicationContext(),Register3Activity.class));
                        finish();
                    }else {
                        Toast.makeText(Register2Activity.this, "الرجاء اختيار النوع", Toast.LENGTH_SHORT).show();
                    }



                }else {
                    Toast.makeText(Register2Activity.this, "الرجاء اختيار النوع", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }



}
