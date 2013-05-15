package com.yehui.androidwithtest.ProjectOne.test.activity;

import android.test.ActivityInstrumentationTestCase2;
import com.yehui.androidwithtest.ProjectOne.activity.HelloAndroidActivity;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<HelloAndroidActivity> {

    HelloAndroidActivity activity;

    public HelloAndroidActivityTest() {
        super(HelloAndroidActivity.class); 
    }

    public void testActivity() {
        activity = getActivity();
        assertNotNull(activity);
        assertNotNull(getInstrumentation().getContext());
    }

    public void testAdd(){
        activity = getActivity();
        assertEquals(activity.add(3,5),8);
    }
}

