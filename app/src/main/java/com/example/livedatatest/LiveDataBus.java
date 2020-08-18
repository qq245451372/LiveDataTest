package com.example.livedatatest;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

public class LiveDataBus {
    private  Map<String, XmLiveData<Object>> map;

    private static LiveDataBus liveDataBus = new LiveDataBus();

    private LiveDataBus(){
        map = new HashMap<>();
    }

    public  static LiveDataBus getInstance()
    {
        return liveDataBus;
    }

    public<T> XmLiveData<T> with(String key,Class<T> tClass)
    {
        if (!map.containsKey(key))
        {
            map.put(key,new XmLiveData<Object>());
        }

        return (XmLiveData<T>) map.get(key);
    }


}
