package com.example.mrzero.androidadkyaaapp3;


import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChalengeFragment extends Fragment {

    public ChalengeFragment() {
        // Required empty public constructor
    }
    private static ChalengeFragment Instance;

    public static ChalengeFragment getInstance(){
        if (Instance==null) {
            Instance= new ChalengeFragment();
        }
        return Instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_chalenge, container, false);
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

        final Button btn1= view.findViewById(R.id.btn_answer_1);
        final Button btn2= view.findViewById(R.id.btn_answer_2);
        final Button btn3= view.findViewById(R.id.btn_answer_3);
        final Button btn4= view.findViewById(R.id.btn_answer_4);


        btn1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                 btn1.setTextColor(getResources().getColor(R.color.white));
                btn2.setTextColor(getResources().getColor(R.color.black));
                btn3.setTextColor(getResources().getColor(R.color.black));
                btn4.setTextColor(getResources().getColor(R.color.black));
                return false;
            }
        });

        btn2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                btn1.setTextColor(getResources().getColor(R.color.black));
                btn2.setTextColor(getResources().getColor(R.color.white));
                btn3.setTextColor(getResources().getColor(R.color.black));
                btn4.setTextColor(getResources().getColor(R.color.black));

                return false;
            }
        });

        btn3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                btn1.setTextColor(getResources().getColor(R.color.black));
                btn2.setTextColor(getResources().getColor(R.color.black));
                btn3.setTextColor(getResources().getColor(R.color.white));
                btn4.setTextColor(getResources().getColor(R.color.black));
                return false;
            }
        });


        btn4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                btn1.setTextColor(getResources().getColor(R.color.black));
                btn2.setTextColor(getResources().getColor(R.color.black));
                btn3.setTextColor(getResources().getColor(R.color.black));
                btn4.setTextColor(getResources().getColor(R.color.white));
                return false;
            }
        });


        return view ;
    }

}
