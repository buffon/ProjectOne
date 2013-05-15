package com.yehui.androidwithtest.ProjectOne.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.yehui.androidwithtest.ProjectOne.R;
import com.yehui.androidwithtest.ProjectOne.activity.base.BaseActivity;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: user
 * Date: 13-5-14
 * Time: 上午4:49
 * To change this template use File | Settings | File Templates.
 */
public class DownloadActivity extends BaseActivity {

    public static final String path = "http://jira.justonetech.com:9000/secure/attachment/32867/MobileTerminal1.apk";
    public static final String appname = "MobileTerminal1.apk";
    Button button;
    TextView textView;
    public static String s;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()   // or .detectAll() for all detectable problems
                .penaltyLog()
                .build());



        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.install);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        download(path, appname);
                        installAPK();
                    }
                }).start();


            }
        });
    }

    public void download(String strPath,String fileName ){
        URL myURL = null;  //取得URL连接
        URLConnection conn = null;
        try {
            myURL = new URL(strPath);
            conn = myURL.openConnection();

            InputStream is = conn.getInputStream();  // 获取下载文件的字符流
            if (is == null) {
                Log.i("DownloadActivity", "stream is null");
            }
            String s = getApplicationContext().getFilesDir().getAbsolutePath();
            File root = new File(s +"/apks");
            if (!root.exists()) {
                root.mkdir();
            }
            File file = new File(root, fileName);
            FileOutputStream fos = new FileOutputStream(file);  //* 将文件写入临时文件 */

            byte buf[] = new byte[1024];
            do
            {
                int numread = is.read(buf);
                if (numread <= 0)
                {
                    break;
                }
                fos.write(buf, 0, numread);
            } while (true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void installAPK() {
        handler.sendEmptyMessage(0);
        String fileName = " /data/data/com.yehui.androidwithtest.ProjectOne/files/apks/"+appname;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(fileName)), "application/vnd.android.package-archive");
        startActivity(intent);
        handler.sendEmptyMessage(1);
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    textView.setText("downloading...");
                    break;
                case 1:
                    showLongToast(s);
                    textView.setText("finished...");
                    break;
                default:
                    break;
            }
        }
    };

    public void install() {

        new Thread() {
            public void run() {
                try {
                    System.out.println("1234567890");
                    handler.sendEmptyMessage(1);
                    Process process = Runtime.getRuntime().exec("su"); // 得到root 权限
                    OutputStream out = process.getOutputStream();
                    // 向进程里 写入命令
                   // out.write(("cp /data/data/com.yehui.androidwithtest.ProjectOne/files/apks/"+ appname+" /data/local/tmp" + "\n").getBytes()); // 先把sdcard里的apk copy到这里目录
                    out.write(("pm install -r /data/data/com.yehui.androidwithtest.ProjectOne/files/apks/"+appname + "\n").getBytes());// 调用安装
                    out.flush();
                    out.close();
                    InputStream in = process.getInputStream();
                    int len = 0;
                    byte[] bs = new byte[256];
                    while (-1 != (len = in.read(bs))) {
                        showShortToast(new String(bs, 0, len));
                    }
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                    s = e.getMessage();
                } finally {
                    handler.sendEmptyMessage(0);
                }
            }
        }.start();

    }
}
