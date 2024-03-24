package com.ligh.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.ligh.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity2 extends BaseActivity {

    private static String TAG = "ligh MainActivity2";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main2,null);
        setContentView(view);
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: ");
        super.onStart();
    }
    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: ");
        super.onResume();

    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        super.onDestroy();
    }

    @NonNull
    @Override
    public ViewBinding getViewBinding() {
        return null;
    }
    
}