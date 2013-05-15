package com.yehui.androidwithtest.ProjectOne.test.activity;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import com.yehui.androidwithtest.ProjectOne.R;
import com.yehui.androidwithtest.ProjectOne.activity.MainActivity;
import com.yehui.androidwithtest.ProjectOne.activity.TouchDemoActivity;
import com.yehui.androidwithtest.ProjectOne.view.CornerListView;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    MainActivity activity;
    private Instrumentation mInstrumentation;
    private Instrumentation.ActivityMonitor mSessionMonitor;
    private Intent mStartIntent;

    @Override
    protected void setUp() throws Exception {
        activity = getActivity();
        mInstrumentation = getInstrumentation();
        mStartIntent = new Intent();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        activity = null;
    }

    public MainActivityTest() {
        super(MainActivity.class);
    }

    public void testActivity() {

        assertNotNull(activity);
    }

    public void testGo(){

    }

    public void testClickButton1(){
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

