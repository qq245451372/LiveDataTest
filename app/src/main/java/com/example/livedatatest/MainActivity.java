package com.example.livedatatest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.MessageQueue;
import android.view.View;
import android.widget.Toast;

import dalvik.system.PathClassLoader;

public class MainActivity extends AppCompatActivity {

    XmLiveData mutableLiveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mutableLiveData = LiveDataBus.getInstance().with("wangwu",String.class);

        mutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
//                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                System.out.println("MainActivity---------"+s);
            }

        });

    }


    public void getMessage(View view)
    {
        mutableLiveData.postValue("bbbb");
        Intent intent = new Intent(this,TwoActivity.class);
        startActivity(intent);
    }
}