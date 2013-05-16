package com.yehui.androidwithtest.ProjectOne.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.*;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ThumbnailUtils;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.yehui.androidwithtest.ProjectOne.R;
import com.yehui.androidwithtest.ProjectOne.activity.base.BaseActivity;
import com.yehui.androidwithtest.ProjectOne.view.CornerListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity {

    private Uri imageUri;
    private ImageView image;
    private ImageView image3;
    private VideoView image2;
    private Button button ;
    private Button button2 ;
    private Button button3 ;

    private CornerListView mListView1 = null;
    private CornerListView mListView2 = null;
    private CornerListView mListView3 = null;
    private CornerListView mListView4 = null;
    ArrayList<HashMap<String, String>> map_list1 = null;
    ArrayList<HashMap<String, String>> map_list2 = null;
    ArrayList<HashMap<String, String>> map_list3 = null;

    public static final int TAKE_PICTURE = 100;
    public static final String TAG = "MainActivity";

    public static Uri photoUri;
    BroadcastReceiver connectionReceiver = null;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_main);
        System.out.println("isNetworkAvaliable: " + isNetworkAvailable(this));

        connectionReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                if(isNetworkAvailable(MainActivity.this)){
                    //showLongToast("1");
                }  else {
                    //showLongToast("2");
                }
            }
        };

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(connectionReceiver, intentFilter);

        image = (ImageView) findViewById(R.id.image);
        image3 = (ImageView) findViewById(R.id.image3);
        image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PicActivity.class);
                intent.putExtra("url", photoUri);
                startActivity(intent);
            }
        });
        image2 = (VideoView) findViewById(R.id.image2);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        handlePic();

        getDataSource1();
        getDataSource2();
        getDataSource3();

        SimpleAdapter adapter1 = new SimpleAdapter(this, map_list1,
                R.layout.simple_list_item_1, new String[] { "item" },
                new int[] { R.id.item_title });
        SimpleAdapter adapter2 = new SimpleAdapter(this, map_list2,
                R.layout.simple_list_item_1, new String[] { "item" },
                new int[] { R.id.item_title });
        SimpleAdapter adapter3 = new SimpleAdapter(this, map_list3,
                R.layout.simple_list_item_1, new String[] { "item" },
                new int[] { R.id.item_title });


        mListView1 = (CornerListView) findViewById(R.id.list1);
        mListView2 = (CornerListView) findViewById(R.id.list2);
        mListView3 = (CornerListView) findViewById(R.id.list3);


        mListView1.setAdapter(adapter1);
        mListView2.setAdapter(adapter2);
        mListView3.setAdapter(adapter3);

        mListView1.setOnItemClickListener(new OnItemListSelectedListener());
        mListView2.setOnItemClickListener(new OnItemListSelectedListener());
        mListView3.setOnItemClickListener(new OnItemListSelectedListener());

        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SurfaceViewAcitvity.class);       //MediaStore.ACTION_VIDEO_CAPTURE
                startActivityForResult(intent, TAKE_PICTURE);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                image2.start();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                System.out.println("chenyehui");
                final CharSequence[] items = { "手机相册", "相机拍摄" };
                AlertDialog dlg = new AlertDialog.Builder(MainActivity.this).setTitle("选择图片").setItems(items,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int item) {
                                //这里item是根据选择的方式,
                                //在items数组里面定义了两种方式, 拍照的下标为1所以就调用拍照方法
                                if(item==1){
                                    Intent getImageByCamera= new Intent("android.media.action.IMAGE_CAPTURE");
//                                    photoUri = MainActivity.this.getContentResolver().insert(
//                                            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
//                                    getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);

                                    startActivityForResult(getImageByCamera,2);
                                }else{
                                    Intent getImage = new Intent(Intent.ACTION_GET_CONTENT);
                                    getImage.addCategory(Intent.CATEGORY_OPENABLE);
                                    getImage.setType("image/*");
                                    startActivityForResult(getImage, 3);
                                }
                            }
                        }).create();
                dlg.show();
            }
        });
    }

    public ArrayList<HashMap<String, String>> getDataSource1() {

        map_list1 = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("item", "扫一扫");
        map_list1.add(map1);
        return map_list1;
    }

    public ArrayList<HashMap<String, String>> getDataSource2() {

        map_list2 = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("item", "巡检轨迹");
        map_list2.add(map1);
        return map_list2;
    }
    public ArrayList<HashMap<String, String>> getDataSource3() {

        map_list3 = new ArrayList<HashMap<String, String>>();
        HashMap<String,String> map1 = new HashMap<String, String>();
        HashMap map2 = new HashMap<String, String>();
        map1.put("item", "服务器同步");
        map2.put("item", "数据同步");
        map_list3.add(map1);
        map_list3.add(map2);
        return map_list3;
    }

    public void handlePic(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.kaka);
        Log.i(TAG,bitmap.getWidth()+" Init ===== "+bitmap.getHeight());
        Matrix matrix = new Matrix();

        //float scale = Math.min(200/(float)230,200/(float)326);

        Bitmap bitmap1 = ThumbnailUtils.extractThumbnail(bitmap,230,326); //40+200 < bitmap.getWidth() 不然报错
        Bitmap bitmap2 = ThumbnailUtils.extractThumbnail(bitmap, 230 / 5, 326 / 5);
        Log.i(TAG,bitmap1.getWidth()+" Init ===== "+bitmap1.getHeight());
        image.setImageBitmap(bitmap);
        //image3.setImageBitmap(bitmap1);
    }


    class OnItemListSelectedListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            if (arg2 == 0) {
                Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(localIntent, 0);
            }else{
                startActivityForResult(new Intent(MainActivity.this, TouchDemoActivity.class), 1);
            }
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if(resultCode == Activity.RESULT_OK){
                    Bundle extras = data.getExtras();
                    Bitmap b = (Bitmap) extras.get("data");
                }
            case 2:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap b = (Bitmap) extras.get("data");

                    ThumbnailUtils.extractThumbnail(b,200,200);

                    Bitmap photoViewBitmap = null;
//                    try {
//                         photoViewBitmap = BitmapFactory.decodeStream(MainActivity.this.getContentResolver().openInputStream(photoUri));
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println("=====InitPic Width="+b.getWidth()+"  Height"+b.getHeight());
                    image.setImageBitmap(b);
                    showShortToast(image.getWidth()+"--"+image.getHeight());
                }
                break;
            case 3:
                if (resultCode == Activity.RESULT_OK) {
                    ContentResolver resolver = getContentResolver();
                    Uri originalUri = data.getData();
                    Bitmap bitmap = null;
                    Bitmap originalBitmap = null;

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 2;
                    try {
                        BitmapFactory.decodeFileDescriptor(new FileInputStream(new File("")).getFD());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    try {
                        originalBitmap = BitmapFactory.decodeStream(resolver.openInputStream(originalUri));
                        System.out.println("InitPic Width=" + originalBitmap.getWidth() + "  Height" + originalBitmap.getHeight());
                        bitmap = Bitmap.createBitmap(originalBitmap, 0, 0, 200, 200);
                        //bitmap = ThumbnailUtils.extractThumbnail(originalBitmap, 200, 200);
                        Log.i(TAG,String.valueOf(bitmap.getWidth()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    image.setImageBitmap(bitmap);

                }
                break;
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    image2.setVideoURI(data.getData());

                    image2.start();
                }
                break;
        }
    }

}
