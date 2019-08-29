package com.example.mrzero.androidadkyaaapp3.ui.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.api.APIService;
import com.example.mrzero.androidadkyaaapp3.api.ServiceGenerator;
import com.example.mrzero.androidadkyaaapp3.model.getMaterial.MaterialData;
import com.example.mrzero.androidadkyaaapp3.model.getMaterial.ResultGetMaterial;
import com.example.mrzero.androidadkyaaapp3.model.getSecondMaterial.SecondMaterialData;
import com.example.mrzero.androidadkyaaapp3.utils.Common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    //class SecondMaterialData come from matrial package
    List<MaterialData> materials= new ArrayList<>();



    public HomeFragment() {
        // Required empty public constructor

    }


    private static HomeFragment Instance;

    public static HomeFragment getInstance(){
        if (Instance==null) {
            Instance= new HomeFragment();
        }

        return Instance;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
       View view=  inflater.inflate(R.layout.fragment_home, container, false);
        TextView tv_name= view.findViewById(R.id.tv_name);
        tv_name.setText(Common.retrieveUserDataPreferance(getActivity().getApplicationContext()).getName());
        //  I do the next because i need to show the nav when click in image icon , and click on fragment itself
         LinearLayout challange_layout= view.findViewById(R.id.challange_layout);
         //todo remove this LinearLayout
        challange_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        LinearLayout  math_layout= view.findViewById(R.id.math_layout);
        math_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.CurrentMaterial=materials.get(3);//note that 3 point to matmatic
                Fragment fragment= SecondMatrialFragment.getInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();


            }
        });

        LinearLayout  ethraa_layout= view.findViewById(R.id.ethraa_layout);
        ethraa_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.CurrentMaterial=materials.get(0);//note that 3 point to matmatic
                Fragment fragment= SecondMatrialFragment.getInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();



            }
        });

        LinearLayout  eng_layout= view.findViewById(R.id.eng_layout);
        eng_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.CurrentMaterial=materials.get(1);//note that 3 point to eng
                Fragment fragment= SecondMatrialFragment.getInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();



            }
        });

        LinearLayout  arabic_layout= view.findViewById(R.id.arabic_layout);
        arabic_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.CurrentMaterial=materials.get(2);//note that 2 point to arabic
                Fragment fragment= SecondMatrialFragment.getInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();


            }
        });


        ImageView menubar= view.findViewById(R.id.menubar);

        menubar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //THIS IS FOR SHOW THE DrawerLayout
                //because the nav in the activity .. i use this
                DrawerLayout drawer =getActivity().findViewById(R.id.drawer_layout);
                drawer.openDrawer(GravityCompat.START);
            }
        });


        ImageView notImage= view.findViewById(R.id.imageView9);
        notImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Fragment fragment= NotificationFragment.getInstance();
             FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
             fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();

            }
        });

        getMaterial(Common.retrieveUserDataPreferance(getActivity()).getRememberToken());

        return  view;
    }

    private void getMaterial(final String token) {

        APIService apiService= ServiceGenerator.createService
                (APIService.class,token );
        Call<ResultGetMaterial> call =  apiService.getMaterial();
        call.enqueue(new Callback<ResultGetMaterial>() {
            @Override
            public void onResponse(Call<ResultGetMaterial> call, Response<ResultGetMaterial> response) {
                if (response.isSuccessful()){
                    Toast.makeText(  getActivity(), "this is suc"+response.body().getData().get(0).getDescription(), Toast.LENGTH_SHORT).show();
                    materials= response.body().getData();
                }else {
                    try {
                        Toast.makeText(getActivity(), "not suc"+response.errorBody().string()+token, Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResultGetMaterial> call, Throwable t) {
                Toast.makeText(getActivity(), "خطأ في الاتصال" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
