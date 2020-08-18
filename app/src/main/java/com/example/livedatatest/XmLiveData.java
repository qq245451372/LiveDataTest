package com.example.livedatatest;

import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.GenericLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

public class XmLiveData<T> {
    private T mPendingData;
    private List<OberverWrapper> mObservers = new ArrayList<>();

    private int mVersion = -1;
    public void observe(LifecycleOwner lifecycleOwner,Observer<T> observer)
    {
        //组件被销毁了
        if (lifecycleOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED)
        {
            return;
        }
        OberverWrapper oberverWrapper = new OberverWrapper();
        oberverWrapper.observer = observer;
        oberverWrapper.lifecycle = lifecycleOwner.getLifecycle();
        oberverWrapper.myLifeCyleBound = new MyLifeCyleBound();
        mObservers.add(oberverWrapper);

        lifecycleOwner.getLifecycle().addObserver(oberverWrapper.myLifeCyleBound);
        dispactionValue();
    }

    public void postValue(T value)
    {
        this.mPendingData = value;
        mVersion ++;
        dispactionValue();
    }

    public void dispactionValue()
    {
        for (OberverWrapper mObserver : mObservers) {
            toChange(mObserver);
        }
    }

    public void toChange(OberverWrapper mObserver)
    {
//        mObserver.onChanged(mPendingData);

        if (mObserver.lifecycle.getCurrentState() != Lifecycle.State.RESUMED)
        {
            return;
        }

        if (mObserver.mLastVersion >= mVersion)
        {
            return;
        }
        mObserver.mLastVersion = mVersion;
        mObserver.observer.onChanged(mPendingData);
    }


    class MyLifeCyleBound implements LifecycleEventObserver {
        @Override
        public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
            System.out.println("-------------> "+source.getLifecycle().getCurrentState().toString());

            if (source.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED)
            {
                remove(source.getLifecycle());
            }
            if (mPendingData != null){
                dispactionValue();
            }
        }

    }

    public void remove(Lifecycle lifecycle)
    {
        for (OberverWrapper mObserver : mObservers) {

            if (mObserver.lifecycle == lifecycle)
            {
                mObserver.lifecycle.removeObserver(mObserver.myLifeCyleBound);
                mObservers.remove(mObserver);
            }
        }
    }


    private class OberverWrapper{
        Lifecycle lifecycle;
        Observer<T> observer;
        int mLastVersion = -1;
        MyLifeCyleBound myLifeCyleBound;
    }

}
