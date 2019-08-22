package com.example.mrzero.androidadkyaaapp3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;


public class EditPersonalFragment extends Fragment {
      TextView edt_school;
    public EditPersonalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private static EditPersonalFragment Instance;

    public static EditPersonalFragment getInstance(){
        if (Instance==null) {
            Instance= new EditPersonalFragment();
        }
        return Instance;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_edit_personal, container, false);

        TextView tv_change_pass= view.findViewById(R.id.tv_change_pass);
        tv_change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                HomeActivity.replaceFragmentFromActivity(ChangePasswordFragment.getInstance());
            }
        });
        changeStatusBarColor();



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


       edt_school = view.findViewById(R.id.textInputLayout3);


        edt_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        }); 
        return view;
    }


    public void showDialog( ){
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity()  );
        View mView = getLayoutInflater().inflate(R.layout.my_custom_dailog, null);

        final Button btn_ok =  mView.findViewById(R.id.btn_ok);
        final Button btn_cancel =  mView.findViewById(R.id.btn_cancel);
        final RadioGroup radio_gruop =  mView.findViewById(R.id.radioGroup2);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get selected item from radio gruop
                int radioButtonID = radio_gruop.getCheckedRadioButtonId();
                View radioButton = radio_gruop.findViewById(radioButtonID);
                int idx = radio_gruop.indexOfChild(radioButton);

                RadioButton r = (RadioButton) radio_gruop.getChildAt(idx);
                String selectedtext = r.getText().toString();
                Toast.makeText(getActivity(), selectedtext, Toast.LENGTH_SHORT).show();



                edt_school.setText(selectedtext);
                dialog.dismiss();
            }
        });
        
        
    }

    private void changeStatusBarColor() {
        Window window =  getActivity().getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(getActivity(),R.color.statusbarColor));
    }

}
