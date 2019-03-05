package com.sh.justcoffee.view.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.blankj.utilcode.util.BarUtils;
import com.sh.justcoffee.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * author： TongGuHermit
 * created on： 2018/12/23
 */

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.iv_splash)
    ImageView mIvSplash;

    private static final int ANIM_TIME = 1000;
    private static final float SCALE_END = 1.1F;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        BarUtils.setStatusBarAlpha(this,60);
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mIvSplash, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mIvSplash, "scaleY", 1f, SCALE_END);

        final AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                set.start();
            }
        },200);

        set.addListener(new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                SplashActivity.this.finish();
            }
        });
    }

}
