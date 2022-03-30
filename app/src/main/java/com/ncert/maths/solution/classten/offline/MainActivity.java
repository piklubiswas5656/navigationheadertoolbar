package com.ncert.maths.solution.classten.offline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private Context context = MainActivity.this;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    //for titlebar
    private SimpleDateFormat simpleDateFormat;
    private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titlebartile();
        init();
        navigationViewitemclick();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        drawerLayout = findViewById(R.id.layoutParent);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        navigationView = findViewById(R.id.navigationDrawer);
        navigationView.setCheckedItem(R.id.navHome);
        View headerView = navigationView.getHeaderView(0);
        TextView textName = headerView.findViewById(R.id.textName);
        textName.setText("HI");
    }


    private void titlebartile() {
        //Start of dynamic title code---------------------
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            Calendar cal = Calendar.getInstance();


            simpleDateFormat = new SimpleDateFormat("HH");
            time = Integer.parseInt(simpleDateFormat.format(cal.getTime()));

            if (time >= 4 && time < 12) {
                actionBar.setTitle("Good Morning");

            } else if (time >= 12 && time < 16) {
                actionBar.setTitle("Good Afternoon");
            } else if (time > 16 && time < 23) {
                actionBar.setTitle("Good Evening");
            } else {
                actionBar.setTitle("Good Night");
            }

        }
        //End of dynamic title code----------------------
    }

    private void navigationViewitemclick() {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            navigationDrawerItemSelector(menuItem);
            return false;
        });
    }

    private void navigationDrawerItemSelector(MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.navHome:
                startActivity(new Intent(context, MainActivity.class));
                break;


            case R.id.navMore:
                Toast.makeText(getApplicationContext(), "More", Toast.LENGTH_LONG).show();

                break;
        }

        closeDrawer();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void closeDrawer() {
        this.drawerLayout.closeDrawer(GravityCompat.START);
    }
}