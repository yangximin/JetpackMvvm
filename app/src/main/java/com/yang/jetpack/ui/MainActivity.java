package com.yang.jetpack.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.yang.jetpack.R;
import com.yang.jetpack.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding viewDataBinding;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_main, R.id.nav_square, R.id.nav_about, R.id.nav_update)
//                .build();
        mAppBarConfiguration = new AppBarConfiguration.Builder()
                .build();

        NavController navController = Navigation.findNavController(this, R.id.main_fg_content);
//        NavigationUI.setupActionBarWithNavController(this,navController,mAppBarConfiguration);
        NavigationUI.setupWithNavController(viewDataBinding.mainBottomNav,navController);

//        BottomNavigationView navView = findViewById(R.id.main_bottom_nav);
//        NavigationUI.setupWithNavController(navView, navController);
//        mAppBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.nav_home, R.id.nav_square, R.id.nav_about, R.id.nav_update)
//                .setDrawerLayout(drawer)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//        NavigationUI.setupWithNavController(mDataBinding.navView, navController);
//
//        BottomNavigationView navView = findViewById(R.id.nav_view_bottom);
//        NavigationUI.setupWithNavController(navView, navController);
    }
}