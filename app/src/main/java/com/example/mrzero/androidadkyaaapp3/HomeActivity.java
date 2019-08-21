package com.example.mrzero.androidadkyaaapp3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.mrzero.androidadkyaaapp3.utils.Common;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.Menu;
import android.view.MenuItem;
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



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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


            fragment= MaaterialFragment.getInstance();
             fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();

        } else if (id == R.id.nav_settings) {

            fragment= EditPersonalFragment.getInstance();
             fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();

        } else if (id == R.id.nav_contact_us) {

            startActivity(new Intent(getApplicationContext(), ContactUsActivity.class));


        } else if (id == R.id.nav_who_us) {


            startActivity(new Intent(getApplicationContext(), WhoAreWeActivity.class));


        } else if (id == R.id.nav_sgin_out) {
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

    public static   void replaceFragmentFromActivity(Fragment fragment){
       fragmentManager.beginTransaction().replace(R.id.contianer_frame, fragment).commit();
    }
}
