package com.yehui.androidwithtest.ProjectOne.activity;

import android.os.Bundle;
import android.view.*;
import com.yehui.androidwithtest.ProjectOne.activity.base.BaseActivity;
import com.yehui.androidwithtest.ProjectOne.view.OverView;

public class SurfaceViewAcitvity extends BaseActivity {

    OverView mAnimView = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 全屏显示窗口
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 显示自定义的游戏View
        mAnimView = new OverView(this);
        //mAnimView.setBackgroundResource(R.drawable.kaka);

        setContentView(mAnimView);
    }


}
