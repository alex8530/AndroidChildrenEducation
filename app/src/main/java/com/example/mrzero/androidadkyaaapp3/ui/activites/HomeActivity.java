package com.example.mrzero.androidadkyaaapp3.ui.activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.mrzero.androidadkyaaapp3.ui.fragments.EditPersonalFragment;
import com.example.mrzero.androidadkyaaapp3.ui.fragments.HomeFragment;
import com.example.mrzero.androidadkyaaapp3.ui.fragments.MaterialFragment;
import com.example.mrzero.androidadkyaaapp3.ui.fragments.PersonalFragment;
import com.example.mrzero.androidadkyaaapp3.R;
import com.example.mrzero.androidadkyaaapp3.model.CurrentUserSaved;
import com.example.mrzero.androidadkyaaapp3.utils.Common;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
, HomeFragment.OnFragmentInteractionListener {


    //this is init fragment to replace between them
  Fragment fragment;
  static   FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        //init fragment
        fragment= HomeFragment.getInstance();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //update header data with image and title from saved user

        View  mHeaderView = navigationView.getHeaderView(0);
        TextView  mDrawerHeaderTitle =  mHeaderView.findViewById(R.id.textView13);
        CircleImageView profile_image_header =  mHeaderView.findViewById(R.id.profile_image_header);

        mDrawerHeaderTitle.setText(Common.retrieveUserDataPreferance(this).getName());

        Picasso.get().load(Common.retrieveUserDataPreferance(this).getImage()).into(profile_image_header);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
            // go
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_personal_file) {

            fragment= PersonalFragment.getInstance();
             fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();

        } else if (id == R.id.nav_materials_study) {


            fragment= MaterialFragment.getInstance();
             fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();

        } else if (id == R.id.nav_settings) {

            fragment= EditPersonalFragment.getInstance();
             fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();

        } else if (id == R.id.nav_contact_us) {

            startActivity(new Intent(getApplicationContext(), ContactUsActivity.class));


        } else if (id == R.id.nav_who_us) {


            startActivity(new Intent(getApplicationContext(), WhoAreWeActivity.class));


        } else if (id == R.id.nav_sgin_out) {
            //clear token and data
            CurrentUserSaved userSaved= new CurrentUserSaved();
            userSaved.setIsLogin(false);
             Common.saveUserDataPreferance(getApplicationContext(),userSaved);
            startActivity(new Intent(getApplicationContext(), Login1Activity.class));
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static void replaceFragmentFromActivity(Fragment fragment){
       fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();
    }
}
