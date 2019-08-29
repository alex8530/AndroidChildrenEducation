package com.example.mrzero.androidadkyaaapp3.ui.fragments;

import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import io.netopen.hotbitmapgg.library.view.RingProgressBar;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.ui.activites.HomeActivity;


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
    public void onResume() {
        super.onResume();
        //this is for handel back press button inside fragment
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    // handle back button's click listener

                    Fragment fragment= HomeFragment.getInstance();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();


                    return true;
                }
                return false;
            }
        });
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



        ImageView notImage= view.findViewById(R.id.imageView9);
        notImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity.replaceFragmentFromActivity(NotificationFragment.getInstance());


            }
        });

//
        RingProgressBar  progress_bar_1 =  view.findViewById(R.id.progress_bar_1);
        RingProgressBar  progress_bar_2 =  view.findViewById(R.id.progress_bar_2);
        RingProgressBar  progress_bar_3 =  view.findViewById(R.id.progress_bar_3);
        RingProgressBar  progress_bar_4 =  view.findViewById(R.id.progress_bar_4);

        // Set the progress bar's progress
        progress_bar_1.setProgress(50);
        progress_bar_2.setProgress(100);
        progress_bar_3.setProgress(50);
        progress_bar_4.setProgress(70);
        progress_bar_1.setOnProgressListener(new RingProgressBar.OnProgressListener()
        {

            @Override
            public void progressToComplete()
            {
                // Progress reaches the maximum callback default Max value is 100
                Toast.makeText(getActivity(), "complete", Toast.LENGTH_SHORT).show();
            }
        });



        return view;


    }


}
