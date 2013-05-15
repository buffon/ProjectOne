package com.yehui.androidwithtest.ProjectOne.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.yehui.androidwithtest.ProjectOne.activity.TouchDemoActivity;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13-5-8
 * Time: 上午11:59
 * To change this template use File | Settings | File Templates.
 */
public class MyLayout extends FrameLayout {


    public MyLayout(Context context){
        super(context);
    }

    public MyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e(TouchDemoActivity.TAG, "MyLayout onInterceptTouchEvent.");
        Log.e(TouchDemoActivity.TAG, "MyLayout onInterceptTouchEvent default return "
                + super.onInterceptTouchEvent(ev));
        return super.onInterceptTouchEvent(ev);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(TouchDemoActivity.TAG, "MyLayout onTouchEvent.");
        Log.e(TouchDemoActivity.TAG, "MyLayout onTouchEvent default return "
                + super.onTouchEvent(event));
        return super.onTouchEvent(event);
    }
}
