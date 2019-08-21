package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.adapters.CountryAdapter;
import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.listener.MyItemListener;
import com.example.mrzero.androidadkyaaapp3.model.CurrentUserSaved;
import com.example.mrzero.androidadkyaaapp3.model.country.Country;
import com.example.mrzero.androidadkyaaapp3.model.country.ResultCountryModel;
import com.example.mrzero.androidadkyaaapp3.model.login.User;
import com.example.mrzero.androidadkyaaapp3.model.register.Data;
import com.example.mrzero.androidadkyaaapp3.model.register.ResultRegisterModel;
 import com.example.mrzero.androidadkyaaapp3.utils.Common;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements MyItemListener {
    TextInputEditText edt_mail,edt_name,edt_pass;
    Button btn_register;
    TextView txt_country;
    Integer id  =  null;
    CountryAdapter countryAdapter;
    ArrayList<Country> listCountry;
    RecyclerView mRecyleCountry;
    AlertDialog dialog;
    AlertDialog.Builder mBuilder;
    Country selectedCountry=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //init
          btn_register= findViewById(R.id.btn_register);
          edt_mail= findViewById(R.id.edt_mail);
          edt_name= findViewById(R.id.edt_name);
          edt_pass= findViewById(R.id.edt_pass);


        //init adapter
        countryAdapter = new CountryAdapter(this  );
        countryAdapter.setMyItemListener(this);


        listCountry=  getListCountry();


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              sendRgisterRequest();


            }
        });



        txt_country = findViewById(R.id.txt_country);
        txt_country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog();
             }
        });

    }
    public void showDialog( ){
          mBuilder = new AlertDialog.Builder(this );
        View mView = getLayoutInflater().inflate(R.layout.my_custom_dailog_countries, null);
        mRecyleCountry=mView.findViewById(R.id.rec_countries);
        mRecyleCountry.setLayoutManager(new LinearLayoutManager(this ));
        mRecyleCountry.setHasFixedSize(true);

        countryAdapter.setmListCountry(listCountry);
        countryAdapter.notifyDataSetChanged();
        mRecyleCountry.setAdapter(countryAdapter);

        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();

    }



    private ArrayList<Country> getListCountry() {
        final ArrayList<Country>countries = new ArrayList<>();
        APIService apiService=  ServiceGenerator.createService(APIService.class);
        Call<ResultCountryModel> call= apiService.getAllCountry();
        call.enqueue(new Callback<ResultCountryModel>() {
            @Override
            public void onResponse(Call<ResultCountryModel> call, Response<ResultCountryModel> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    countries.addAll(response.body().getCountries());

                }
            }

            @Override
            public void onFailure(Call<ResultCountryModel> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "حدث خطأ في الاتصال في السيرفر",  Toast.LENGTH_SHORT).show();
            }
        });


        return countries;
    }

    private void sendRgisterRequest() {
        if (selectedCountry==null){

            Toast.makeText(this, "يرجى اختيار الدولة ", Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty( edt_mail.getText().toString())){
            Toast.makeText(this, "يرجى كتابة الايميل ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty( edt_name.getText().toString())){
            Toast.makeText(this, "يرجى كتابة الاسم ", Toast.LENGTH_SHORT).show();

        }
        else if (TextUtils.isEmpty( edt_pass.getText().toString())){
            Toast.makeText(this, "يرجى كتابة الباسورد ", Toast.LENGTH_SHORT).show();

        }
        else {
            APIService apiService=  ServiceGenerator.createService(APIService.class);
            Call<ResultRegisterModel>  call =  apiService.createUser(
                    edt_name.getText().toString(),
                    edt_mail.getText().toString(),
                    String.valueOf(selectedCountry.getId()),
                    edt_pass.getText().toString()
            );

            call.enqueue(new Callback<ResultRegisterModel>() {
                @Override
                public void onResponse(Call<ResultRegisterModel> call, Response<ResultRegisterModel> response) {
                    if (response.isSuccessful()){

                        // save token and data of user

                        Data  data= response.body().getData() ;

                        CurrentUserSaved userSaved= new CurrentUserSaved();
                        userSaved.setIsLogin(true);
                        userSaved.setRememberToken(data.getApiToken());
                        userSaved.setName(data.getUser().getName());
                        userSaved.setEmail(data.getUser().getEmail());
                        userSaved.setId(data.getUser().getId());

                        //save user to referance
                        Common.saveUserDataPreferance(getApplicationContext(),userSaved);


                        Toast.makeText(RegisterActivity.this, "تمت عملية التسجيل بنجاح !", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Register1Activity.class));
                        finish();

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

    @Override
    public void onClickItem(int position) {
          selectedCountry=listCountry.get(position);
        Toast.makeText(this, ""+selectedCountry.getName(), Toast.LENGTH_SHORT).show();
        dialog.dismiss();
    }
}
