package com.yehui.androidwithtest.ProjectOne.activity;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13-5-8
 * Time: 下午12:01
 * To change this template use File | Settings | File Templates.
 */
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.yehui.androidwithtest.ProjectOne.R;

public class TouchDemoActivity extends Activity {
    public static final String TAG = "TouchDemoActivity";
    Button button;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        button = (Button) findViewById(R.id.button);
        Drawable d = getResources().getDrawable(R.drawable.ic_arrow_down_black);
        d.setBounds(0,0,d.getIntrinsicWidth(),d.getIntrinsicHeight());
        button.setCompoundDrawables(null,null,d,null);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }


}
