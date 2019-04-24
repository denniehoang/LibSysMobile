package com.example.libsysmobile.user_home_pages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.libsysmobile.Fragments.AccountFragmentMember;
import com.example.libsysmobile.Fragments.ExtendFragmentMember;
import com.example.libsysmobile.Fragments.HomeFragmentMember;
import com.example.libsysmobile.Fragments.SearchBookFragmentMember;
import com.example.libsysmobile.R;
import com.example.libsysmobile.login_pages.Page;

public class MemberHomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page_member);


        //Toolbar/navbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MemberHomePage.this, drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//        //open home fragment
//        if(savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new HomeFragmentMember()).commit();
//            navigationView.setCheckedItem(R.id.nav_home_member);
//        }

    }

    //Functionality for navigation drawer
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_home_member:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragmentMember()).commit();
                break;
            case R.id.nav_search_book_member:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SearchBookFragmentMember()).commit();
                break;
            case R.id.nav_extend_member:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ExtendFragmentMember()).commit();
                break;
            case R.id.nav_account_member:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AccountFragmentMember()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //copen/close navbar
    @Override
    public void onBackPressed(){
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer((GravityCompat.START));
        }else{
            super.onBackPressed();
        }
    }
}
