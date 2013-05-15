package com.yehui.androidwithtest.ProjectOne.activity;

import android.app.Activity;
import android.os.Bundle;
import com.yehui.androidwithtest.ProjectOne.R;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13-5-8
 * Time: 下午5:19
 * To change this template use File | Settings | File Templates.
 */
public class FenBeiLvActivity extends Activity {

    int i = 3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fenbian);

        testIf();
        testWhile();
        testFor();
        testSwitch();
    }

    public void testIf() {
        if (i == 1) {
            System.out.println(i);
        } else {
            System.out.println(i);
        }
    }

    public void testWhile() {
        while (i < 10) {
            System.out.println(i);
            i++;
        }
    }

    public void testFor() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
    }

    public void testSwitch() {
        try {
            Integer i = new Integer(1);
            if (i.intValue() > 0) {
                System.out.print("2");
            } else {
                System.out.print("3");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch (i) {
            case 1:
                System.out.println(i);
                break;
            case 2:
                System.out.println(i);
                break;
            default:
                System.out.println(i);
                break;
        }
    }
}
