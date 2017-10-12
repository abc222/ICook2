package com.example.wangyuchao.icook.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wangyuchao.icook.R;

/**
 * Created by WangYuchao on 2017/6/8.
 */

public class CookerHoodActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private Button cookerHoodOpen;
    private Button cookerHoodClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_cooker_hood);
        back = (ImageView)findViewById(R.id.back);
        cookerHoodOpen = (Button)findViewById(R.id.cooker_hood_open);
        cookerHoodClose = (Button)findViewById(R.id.cooker_hood_close);
        back.setOnClickListener(this);
        cookerHoodOpen.setOnClickListener(this);
        cookerHoodClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.back)
        {
            finish();
        }else if(view.getId() == R.id.cooker_hood_open){
            Toast.makeText(this,"油烟机已开",Toast.LENGTH_SHORT).show();
        }else if(view.getId() == R.id.cooker_hood_close){
            Toast.makeText(this,"油烟机已关",Toast.LENGTH_SHORT).show();
        }
    }
}