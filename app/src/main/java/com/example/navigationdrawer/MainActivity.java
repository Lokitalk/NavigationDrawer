package com.example.navigationdrawer;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        // step 1
        setSupportActionBar(toolbar);

        // step 2 write code for open or close drwawer how they open and close

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.OpenDrwawer, R.string.Closedrawer);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        // this line for default fragment means if you want when app open so b fragment open so set default fragment
        loadFragment(new AFragment(), true);


        // step 3 set up fragments in content in mainactivity

        navigationView.setNavigationItemSelectedListener(item -> {

            int id = item.getItemId();

            if (id == R.id.optNotes) {

                loadFragment(new AFragment(), false);


            } else if (id == R.id.optHome) {

                loadFragment(new BFragment(), false);

            } else { // optsettings

                loadFragment(new CFragment(), false);
            }

            // this line for drwawer when user click any items so drwawer will be automatically closed
            drawerLayout.closeDrawer(GravityCompat.START);

            return true;
        });

    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment, boolean flag) {

        // 4 lines code for connenct fragment in activity

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (flag)
            // this line for add fragment into frame layout because frame layout handles fragments
            ft.add(R.id.containner, fragment);
        else
            ft.replace(R.id.containner, fragment);
        ft.commit();
    }

}
