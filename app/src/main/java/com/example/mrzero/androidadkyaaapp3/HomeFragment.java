package com.example.mrzero.androidadkyaaapp3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


public class HomeFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


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
//        animation= AnimationUtils.loadAnimation(getActivity(),R.anim.up_to_down);

        //  I do the next because i need to show the nav when click in image icon , and click on fragment itself
         ConstraintLayout constraintLayout= view.findViewById(R.id.constraintLayout2);
         constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= ChalengeFragment.getInstance();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();


            }
        });

        ConstraintLayout  constraintLayoutMath= view.findViewById(R.id.constraintLayout3);
        constraintLayoutMath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment= MathmaticsFragment.getInstance();
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
                DrawerLayout drawer = (DrawerLayout) getActivity().findViewById(R.id.drawer_layout);

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


        //challange clever
        ConstraintLayout constraintLayout2= view.findViewById(R.id.constraintLayout2);
//        constraintLayout2.startAnimation(animation);
        return  view;
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
