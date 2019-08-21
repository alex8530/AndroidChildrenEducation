package com.example.mrzero.androidadkyaaapp3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.loader.content.CursorLoader;
import id.zelory.compressor.Compressor;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.utils.Common;
import java.io.File;
 import java.io.IOException;

public class Register3Activity extends AppCompatActivity {
      String realPathImage;
       ProgressBar progressBar;
    private static final String TAG = "Register3Activity";
    ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register3);
        //Requesting storage permission
        requestStoragePermission();


        Button btn_register= findViewById(R.id.btn_register);
         progressBar= findViewById(R.id.progress_bar_1);



        profile_image= findViewById(R.id.profile_image);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickFromGallery();
            }
        });


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        if (realPathImage==null) {
            Toast.makeText(getApplicationContext(), "يرجى اختيار صورة", Toast.LENGTH_SHORT).show();
        }else {
            uploadImageAndData( );

        }
            }
        });

    }


    private void pickFromGallery(){
        //Create an Intent with action as ACTION_PICK
        Intent intent=new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png","image/jpg"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES,mimeTypes);
        // Launching the Intent
        startActivityForResult(intent,100);
    }


    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
    }



    private void uploadImageAndData(){


        progressBar.setVisibility(View.VISIBLE);
        APIService apiService= ServiceGenerator.createService
                (APIService.class, Common.retrieveUserDataPreferance(getApplicationContext()).getRememberToken());

        /************image*************/
                // create RequestBody instance from file

        
        File file=  new File(realPathImage);

        File compressedImageFile =   compressFile(file);
        RequestBody requestFile =
                RequestBody.create(
                      MediaType.parse("*/*"),
                        compressedImageFile
                );

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part img =
                MultipartBody.Part.createFormData("img", file.getName(), requestFile);



/************image*************/

        // add another part within the multipart request
        String classString = "1";
        String genderString = "3";

        RequestBody classroom =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, classString);



        RequestBody gender =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, genderString);


//
       Call<ResponseBody> call =  apiService.completeProfile(classroom,gender,img);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                progressBar.setVisibility(View.INVISIBLE);

                if (response.isSuccessful()){
                     Toast.makeText(Register3Activity.this, " تم اكمال بيانات ملفك الشخصي بنجاح", Toast.LENGTH_SHORT).show();

                     Intent intent= new Intent(getApplicationContext(),HomeActivity.class);
                    startActivity(intent);
                    finish();


                 }else {

                     try {
                        Toast.makeText(Register3Activity.this, "لم تتم العملية.. يرجى اختيار صورة أصغر حجما"+response.errorBody().string(), Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Register3Activity.this, "العملية فشلت", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private File compressFile(File file) {
        File f =null;
        try {
            f =  new Compressor(this)
                    .setMaxWidth(640)
                    .setMaxHeight(480)
                    .setQuality(75)
                    .compressToFile(file);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return f;
    }

    @Override
    protected void   onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode){
                case 100:
                    //data.getData return the content URI for the selected Image
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = { MediaStore.Images.Media.DATA };
                    // Get the cursor
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();
                    //Get the column index of MediaStore.Images.Media.DATA
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    //Gets the String value in the column
                    realPathImage = cursor.getString(columnIndex);
                    cursor.close();
                    // Set the Image in ImageView after decoding the String
                    profile_image.setImageBitmap(BitmapFactory.decodeFile(realPathImage));
                    break;

            }
    }


}
