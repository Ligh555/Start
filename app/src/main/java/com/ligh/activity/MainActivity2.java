package com.ligh.activity;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewbinding.ViewBinding;

import com.ligh.R;
import com.ligh.UtilsKt;
import com.ligh.view.CustomConstraintLayout;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity2 extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(this).inflate(R.layout.activity_main2,null);
        setContentView(view);
    }

    @NonNull
    @Override
    public ViewBinding getViewBinding() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}