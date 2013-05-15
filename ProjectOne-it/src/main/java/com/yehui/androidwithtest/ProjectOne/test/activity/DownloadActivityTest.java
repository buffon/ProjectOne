package com.yehui.androidwithtest.ProjectOne.test.activity;

import android.app.Instrumentation;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import com.yehui.androidwithtest.ProjectOne.R;
import com.yehui.androidwithtest.ProjectOne.activity.DownloadActivity;
import com.yehui.androidwithtest.ProjectOne.activity.MainActivity;
import com.yehui.androidwithtest.ProjectOne.view.CornerListView;

public class DownloadActivityTest extends ActivityInstrumentationTestCase2<DownloadActivity> {

    DownloadActivity activity;
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

    public DownloadActivityTest() {
        super(DownloadActivity.class);
    }

    public void testActivity(){
        assertNotNull(activity);
    }

    public void testDownload(){
        activity.download(DownloadActivity.path,"Mo");
    }

}

