package com.example.libsysmobile.login_pages;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.libsysmobile.AsyncResponse;
import com.example.libsysmobile.MainActivity;
import com.example.libsysmobile.R;

import java.util.List;


public abstract class Page extends Activity implements AsyncResponse {

    private BottomNavigationView bottomNavigationView;
    private String currentPage;
    public AlertDialog.Builder alertDialogBuilder;
    public AlertDialog alertDialog;

    protected void newPage(int pageLayout, int navigationView, String currentPage) {
        showPage(pageLayout);
        setPageBottomNavigationView(navigationView);
        this.currentPage = currentPage;
    }

    protected void showPage(int pageLayout) {
        setContentView(pageLayout);
    }

    protected void setPageBottomNavigationView(int navigationView) {
        bottomNavigationView = findViewById(navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
    }

    protected BottomNavigationView.OnNavigationItemSelectedListener navigationListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                // Press 'Login' on Bottom Navigation
                case R.id.login_navigation_login:
                    // If already viewing the Login Page, clicking login on the bottom navigation will do nothing.
                    if (!currentPage.equals(getString(R.string.login_navigation_login_name_page_name))) {
                        changePage(Page.this, com.example.libsysmobile.login_pages.LoginPage.class);
                        bottomNavigationView.getMenu().findItem(R.id.login_navigation_login).setChecked(true);
                        shiftViewLeft();
                    }
                    break;
                // Press 'Create Account' on Bottom Navigation
                case R.id.login_navigation_create_account:
                    if (!currentPage.equals(getString(R.string.login_navigation_create_account_name_page_name))) {
                        changePage(Page.this, com.example.libsysmobile.login_pages.CreateAccountPage.class);
                        bottomNavigationView.getMenu().findItem(R.id.login_navigation_login).setChecked(true);
                        if (currentPage.equals("ResetPasswordPage")) {
                            shiftViewLeft();
                        } else {
                            shiftViewRight();
                        }
                    }
                    break;
                // Press 'Reset Password' on Bottom Navigation
                case R.id.login_navigation_reset_password:
                    if (!currentPage.equals("ResetPasswordPage")) {
                        changePage(Page.this, com.example.libsysmobile.login_pages.ResetPasswordPage.class);
                        shiftViewRight();
                    }
                    break;
                case R.id.login_navigation_exit:
                    // Is there a way to exit to the mobile home screen? Instead of the landing page? Currently routes to landing page
                    changePage(Page.this, MainActivity.class);
                    break;
            }
            return false;
        }
    };

    private void shiftViewRight() {
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private void shiftViewLeft() {
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    protected void changePage(Context thisPage, Class nextClassPage) {
        Intent nextIntent = new Intent(thisPage, nextClassPage);
        startActivity(nextIntent);
    }

    @Override
    public void processFinish(List<String> resultList) {    }
}
