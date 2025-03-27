package com.runner.game.ui;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {

    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        ActivityManager.getInstance().addActivity(this);
    }

    protected void jumpActivity(final Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
    }

    protected void jumpActivityFinish(final Class<?> clazz) {
        Intent intent = new Intent(mContext, clazz);
        startActivity(intent);
        finish();
    }

    protected void showMsg(CharSequence msg) {
        Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show();
    }

    protected void showLongMsg(CharSequence msg) {
        Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show();
    }
}
