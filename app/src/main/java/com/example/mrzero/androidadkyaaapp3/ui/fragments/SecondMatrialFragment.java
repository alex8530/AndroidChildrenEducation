package com.example.mrzero.androidadkyaaapp3.ui.fragments;


import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.model.getMaterial.ResultGetMaterial;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.ResultGetSecondMaterial;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.SecondMaterialData;
import com.example.mrzero.androidadkyaaapp3.ui.activites.HomeActivity;
import com.example.mrzero.androidadkyaaapp3.utils.Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondMatrialFragment extends Fragment {


    List<SecondMaterialData> secondMaterialDataList= new ArrayList<>();


    public SecondMatrialFragment() {
        // Required empty public constructor
    }
    private static SecondMatrialFragment Instance;

    public static SecondMatrialFragment getInstance(){
        if (Instance==null) {
            Instance= new SecondMatrialFragment();
        }
        return Instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_second_matrial, container, false);

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


        ImageView notImage= view.findViewById(R.id.imageView9);
        notImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.replaceFragmentFromActivity(NotificationFragment.getInstance());

            }
        });


        getSecondMaterial(Common.retrieveUserDataPreferance(getActivity()).getRememberToken());
        return view;
    }
    private void getSecondMaterial(final String token) {

        APIService apiService= ServiceGenerator.createService
                (APIService.class,token );
        Call<ResultGetSecondMaterial> call =  apiService.getSecondMaterial(String.valueOf(Common.CurrentMaterial.getId()));
        call.enqueue(new Callback<ResultGetSecondMaterial>() {
            @Override
            public void onResponse(Call<ResultGetSecondMaterial> call, Response<ResultGetSecondMaterial> response) {
                if (response.isSuccessful()){
                    Toast.makeText(  getActivity(), "this is suc" , Toast.LENGTH_SHORT).show();
                    secondMaterialDataList= response.body().getData();
                }else {
                    try {
                        Toast.makeText(getActivity(), "not suc"+response.errorBody().string()+token, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResultGetSecondMaterial> call, Throwable t) {
                Toast.makeText(getActivity(), "خطأ في الاتصال" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
