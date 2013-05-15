package com.yehui.androidwithtest.ProjectOne.test.activity;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ActivityUnitTestCase;
import android.test.TouchUtils;
import android.test.suitebuilder.annotation.MediumTest;
import android.widget.Button;
import com.yehui.androidwithtest.ProjectOne.R;
import com.yehui.androidwithtest.ProjectOne.activity.MainActivity;
import com.yehui.androidwithtest.ProjectOne.activity.TouchDemoActivity;
import com.yehui.androidwithtest.ProjectOne.view.CornerListView;

public class MainActivityTest2 extends ActivityUnitTestCase<MainActivity> {

    MainActivity activity;
    Context context;
    private Intent mStartIntent;


    public MainActivityTest2() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        context = getInstrumentation().getContext() ;
        assertNotNull(context);
        mStartIntent = new Intent(Intent.ACTION_MAIN);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        activity = null;
    }

    @MediumTest
    public void testPreconditions() {
       // startActivity(mStartIntent, null, null);
        assertNotNull(getActivity());
    }

    public void testActivity() {
        activity = startActivity(mStartIntent,null,null)  ;
        assertNotNull(activity);
    }

    public void testGo(){

    }

    public void testClickButton1(){
        activity = getActivity();
        Button button = (Button) activity.findViewById(R.id.button);
        assertNotNull(button);
        TouchUtils.clickView(this, button);
    }
    public void testClickButton2(){
        final Button button = (Button) activity.findViewById(R.id.button2);
        assertNotNull(button);
        TouchUtils.clickView(this, button);
//        getActivity().runOnUiThread(new Runnable() {
//            public void run() {
//                button.performClick();
//            }
//        });
//        getInstrumentation().waitForIdleSync();
//        assertTrue(button.isFocused());
    }
    public void testClickButton3(){
        Button button = (Button) activity.findViewById(R.id.button3);
        assertNotNull(button);
       TouchUtils.clickView(this, button);
    }

    public void testListview(){
        CornerListView cornerListView = (CornerListView) activity.findViewById(R.id.list1);
        TouchUtils.clickView(this,cornerListView);
    }

}

