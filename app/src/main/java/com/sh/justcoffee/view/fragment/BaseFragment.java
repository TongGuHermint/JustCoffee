package com.sh.justcoffee.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.ButterKnife;

/**
 * author： TongGuHermit
 * created on： 2018/12/27
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    protected RxPermissions mRxPermissions;
    protected Gson mGson;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentView(),null);
        ButterKnife.bind(this,mRootView);
        //解决fragment点击事件穿透问题
        mRootView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });
        mGson = new Gson();
        initViews();
        return mRootView;
    }

    protected abstract int getContentView();
    protected abstract void initViews();

}
