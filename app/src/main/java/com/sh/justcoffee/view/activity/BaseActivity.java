package com.sh.justcoffee.view.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.blankj.utilcode.util.BarUtils;
import com.google.gson.Gson;
import com.sh.justcoffee.constans.ConstValues;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.Stack;

import butterknife.ButterKnife;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;


/**
 * author： TongGuHermit
 * created on： 2018/12/27
 */
public abstract class BaseActivity extends SwipeBackActivity  {

    protected RxPermissions mRxPermissions;
    protected Gson mGson;
    protected Stack<Fragment> mFragStack;
    protected int statusBarHeight;
    protected SwipeBackLayout mSwipeBackLayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        ButterKnife.bind(this);
        statusBarHeight = BarUtils.getStatusBarHeight();
        mRxPermissions = new RxPermissions(this);
        requestPermissions();
        mGson = new Gson();
        setSwipeBackEnable(true);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);
        mSwipeBackLayout.setEdgeSize(400);
        initViews();
    }

    protected abstract int getContentView();
    protected abstract void initViews();

    private void requestPermissions(){
        mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE)
                .subscribe(new io.reactivex.functions.Consumer<Boolean>() {

                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!aBoolean){
                            Toast.makeText(BaseActivity.this,"为保证应用正常运行,请在权限管理开启必要权限!",Toast.LENGTH_SHORT).show();
                        }else {
                            File file = new File(Environment.getExternalStorageDirectory().getPath()+ ConstValues.FILE_ROOT_DIRECTORY);
                            if (!file.exists()){
                                file.mkdirs();
                            }
                        }
                    }
                });
    }



    public void startAcvityWithNoData(Context context, Class<?> cls){
        Intent intent = new Intent(context,cls);
        startActivity(intent);
    }

    public void hideFragment(Fragment fragment){
        if (fragment!=null & mFragStack !=null){
            getSupportFragmentManager().beginTransaction().hide(fragment).commitAllowingStateLoss();
        }
    }


    @Override
    public void onBackPressed() {
            super.onBackPressed();
    }

    public void showFragment(Fragment fragment,boolean hidePrevius){
        if (fragment!=null & mFragStack !=null){
            for (Fragment fragment1:mFragStack){
                if (fragment1 == fragment){
                    getSupportFragmentManager().beginTransaction().show(fragment1).commitAllowingStateLoss();
                }else {
                    if (hidePrevius){
                        getSupportFragmentManager().beginTransaction().hide(fragment1).commitAllowingStateLoss();
                    }
                }
            }
        }
    }

    public int getStatusBarHeight(){
        return statusBarHeight;
    }
}
