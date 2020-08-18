package com.example.livedatatest;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class TwoActivity extends AppCompatActivity {
    XmLiveData mutableLiveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        mutableLiveData = LiveDataBus.getInstance().with("wangwu",String.class);
    }

    public void sendMessage(View view)
    {

        mutableLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {

            }
        });

        mutableLiveData.postValue("aaaaa");
    }
}