package com.example.mrzero.androidadkyaaapp3;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mikhaellopez.circularimageview.CircularImageView;


public class PersonalFragment extends Fragment {


    public PersonalFragment() {
        // Required empty public constructor
    }
    private static PersonalFragment Instance;

    public static PersonalFragment getInstance(){
        if (Instance==null) {
            Instance= new PersonalFragment();
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
       View view =  inflater.inflate(R.layout.fragment_personal, container, false);

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


        //this is for hide the shadow of the image
        CircularImageView circularImageView = view.findViewById(R.id.circularImageView);
        circularImageView.setShadowEnable(false);
        circularImageView.setShadowRadius(1);
        circularImageView.setShadowColor(Color.TRANSPARENT);
        circularImageView.setBackgroundColor(Color.TRANSPARENT);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);


        ImageView notImage= view.findViewById(R.id.imageView9);
        notImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.replaceFragmentFromActivity(NotificationFragment.getInstance());


            }
        });




        return view;


    }


}
