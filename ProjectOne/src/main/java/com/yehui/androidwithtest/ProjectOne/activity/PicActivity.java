package com.yehui.androidwithtest.ProjectOne.activity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import com.yehui.androidwithtest.ProjectOne.R;
import com.yehui.androidwithtest.ProjectOne.activity.base.BaseActivity;

import java.io.FileNotFoundException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13-5-15
 * Time: 上午9:22
 * To change this template use File | Settings | File Templates.
 */
public class PicActivity extends BaseActivity {
    ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic);
        imageView = (ImageView) findViewById(R.id.image);

        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            imageView.setImageBitmap(BitmapFactory.decodeStream(PicActivity.this.getContentResolver().openInputStream(MainActivity.photoUri),null,options));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
