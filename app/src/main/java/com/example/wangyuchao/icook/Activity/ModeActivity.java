package com.example.wangyuchao.icook.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.wangyuchao.icook.R;

/**
 * Created by WangYuchao on 2017/6/5.
 */

public class ModeActivity extends AppCompatActivity implements View.OnClickListener{

    private ProgressBar progressBar;
    private Button safeTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //去掉系统自带标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);

        progressBar = (ProgressBar)findViewById(R.id.progressbar_safe);
        safeTest = (Button)findViewById(R.id.progressbar_safe_test);
        safeTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.progressbar_safe_test:
                //引入操作布局
                LayoutInflater safeTestInflater = LayoutInflater.from(getApplicationContext());
                View safeTestLayout = safeTestInflater.inflate(R.layout.activity_mode_safe_alert_dialog, null);
                AlertDialog.Builder safeTestDiallog = new AlertDialog.Builder(this);
                safeTestDiallog.setView(safeTestLayout);   //定义为自定义的布局
                safeTestDiallog.setCancelable(true);   //设置为可取消弹出框
                safeTestDiallog.create().show();    //创建并显示弹出框
                break;
            default:break;
        }
    }

}
