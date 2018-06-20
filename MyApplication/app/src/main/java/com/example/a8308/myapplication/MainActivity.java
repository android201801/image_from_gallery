package com.example.a8308.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private final String IMG_FILE_PATH = "imgfilepath";
    private final String IMG_TITLE = "imgtitle";
    private final String IMG_ORIENTATION = "imgorientation";
    //YWFhYWE6YmJiYml= //auth_key\
    private final String auth_key = "YWFhYWE6YmJiYmI=";

    private final int REQ_CODE_SELECT_IMAGE = 1001;
    private String mImgPath = null;
    private String mImgTitle = null;
    private String mImgOrient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button selectbtn = (Button) findViewById(R.id.selectimg);
        selectbtn.setOnClickListener(this);

        Button sendinfo = (Button) findViewById(R.id.sendinfo);
        sendinfo.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.selectimg:
                getGallery();
                break;
            //case R.id.sendinfo:
                //new HttpRequestAsyncTask().execute(this.mImgPath, this.mImgTitle, this.mImgOrient);
                //break;
            default:
                break;
        }
    }
    private void getGallery()
    {
        Intent intent = null;

        // 안드로이드 KitKat(level 19)부터는 ACTION_PICK 이용
        if(Build.VERSION.SDK_INT >= 19)
        {
            intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        }
        else
        {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        }

        intent.setType("image/*");
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // 선택된 사진을 받아 서버에 업로드한다.
        if (requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                Uri uri = data.getData();
                //getImageNameToUri(uri);

                try
                {
                    Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ImageView img = (ImageView) findViewById(R.id.imageview);
                    img.setImageBitmap(bm);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    
}
