package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
 import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.utils.Common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Register3Activity extends AppCompatActivity {
    Bitmap bitmap;
    private static final String TAG = "Register3Activity";
    ImageView profile_image;
    private static final int IMAGE_PICK = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);

        Button btn_register= findViewById(R.id.btn_register);

        profile_image= findViewById(R.id.profile_image);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
                Toast.makeText(Register3Activity.this, "s", Toast.LENGTH_SHORT).show();

            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadImage();
//                Intent  intent =new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);
//                finish();
            }
        });

    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGE_PICK);
    }



    private String convertToString()
    {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        byte[] imgByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgByte,Base64.DEFAULT);
    }


    private void uploadImage(){

        //get tokecn
//        String token = Common.retrieveUserDataPreferance(this).getRememberToken();


        String image = convertToString();
        APIService apiService= ServiceGenerator.createService(APIService.class,"433679db06f1d86b327a1552d0bd5bb3db41ebc965e4802d49aacd3f4fc1");
       Call<ResponseBody> call =  apiService.completeProfile("1","3",image);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    Toast.makeText(Register3Activity.this, "sucessful", Toast.LENGTH_SHORT).show();
                }else {
                    try {
                        Toast.makeText(Register3Activity.this, "not sucessful"+response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "not sucessful"+response.toString());
                        Log.d(TAG, "not sucessful"+response.message());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Register3Activity.this, "العملية فشلت", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: " + t.getMessage());

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGE_PICK && resultCode==RESULT_OK && data!=null)
        {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                profile_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
