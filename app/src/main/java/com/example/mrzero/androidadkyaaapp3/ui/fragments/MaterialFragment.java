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
import com.example.mrzero.androidadkyaaapp3.ui.activites.HomeActivity;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialFragment extends Fragment {



    public MaterialFragment() {
        // Required empty public constructor
    }
    private static MaterialFragment Instance;

    public static MaterialFragment getInstance(){
        if (Instance==null) {
            Instance= new MaterialFragment();
        }
        return Instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_material, container, false);


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
        return view;
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
                Toast.makeText(getActivity(), "Fail" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
