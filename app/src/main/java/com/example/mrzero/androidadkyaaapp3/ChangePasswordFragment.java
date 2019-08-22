package com.example.mrzero.androidadkyaaapp3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
 import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.utils.Common;
import com.google.android.material.textfield.TextInputEditText;


public class ChangePasswordFragment extends Fragment {

    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private static ChangePasswordFragment Instance;

    public static ChangePasswordFragment getInstance(){
        if (Instance==null) {
            Instance= new ChangePasswordFragment();
        }
        return Instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_change_password, container, false);
     final    TextInputEditText edt_oldpass = view.findViewById(R.id.edt_oldpass);
     final    TextInputEditText edt_new_pass = view.findViewById(R.id.edt_new_pass);
     final    TextInputEditText edt_renew_ass = view.findViewById(R.id.edt_renew_ass);
        Button btn_change_pass = view.findViewById(R.id.btn_change_pass);

        ImageView menubar= view.findViewById(R.id.menubar);

        menubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //THIS IS FOR SHOW THE DrawerLayout
                //because the nav in the activity .. i use this
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

                drawer.openDrawer(GravityCompat.START);
            }
        });

        btn_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldpass=  edt_oldpass.getText().toString();
                String newpass=  edt_new_pass.getText().toString();
                String renewpass=  edt_renew_ass.getText().toString();

                if (TextUtils.isEmpty(oldpass) ||TextUtils.isEmpty(newpass) ||TextUtils.isEmpty(renewpass)  ){
                    Toast.makeText(getActivity(), "الرجاء ملئ كافة الحقول", Toast.LENGTH_SHORT).show();
                }else {

                    changePassword(oldpass,newpass,renewpass);
                }

            }
        });

        return  view;
    }

    private void changePassword(String oldpass, String newpass, String renewpass) {
        String token=Common.retrieveUserDataPreferance(getActivity()).getRememberToken();

        APIService apiService= ServiceGenerator.createService(APIService.class,token);
       Call<ResponseBody> call =  apiService.changePassword(oldpass,newpass,renewpass);
       call.enqueue(new Callback<ResponseBody>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
               if (response.isSuccessful()){
                   Toast.makeText(getActivity(), "تم عملية التعديل بنجاح", Toast.LENGTH_SHORT).show();

               }else {
                   Toast.makeText(getActivity(), "الرجاء التأكد من البيانات المدخلة", Toast.LENGTH_SHORT).show();

               }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {
               Toast.makeText(getActivity(), "حدث خطأ في الاتصال", Toast.LENGTH_SHORT).show();
           }
       });
    }
}
