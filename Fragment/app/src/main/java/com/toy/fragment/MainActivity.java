package com.toy.fragment;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import com.toy.fragment.ui.main.LoadingFragment;
import com.toy.fragment.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private LoadingFragment loadingFragment;
    private MainFragment mainFragment;
    private Handler mHandler;
    private static String TAG = "easy";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        
        //title 가리기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }*/


        replaceFragment(LoadingFragment.newInstance());

        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "replace with MainFragment");
                replaceFragment(MainFragment.newInstance());
            }
        },3000);

    }

    public void replaceFragment( Fragment fragment){
        Log.d(TAG, "replaceFragment ");
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

    public void foo(){

    }
}