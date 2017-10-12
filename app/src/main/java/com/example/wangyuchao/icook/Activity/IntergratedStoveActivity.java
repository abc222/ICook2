package com.example.wangyuchao.icook.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.wangyuchao.icook.R;
import com.example.wangyuchao.icook.net.SocketDemo;


/**
 * Created by WangYuchao on 2017/6/8.
 */

public class IntergratedStoveActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private Button intergratedStoveOpen;
    private Button intergratedStoveClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_integrated_stove);
        back = (ImageView)findViewById(R.id.back);
        intergratedStoveOpen = (Button)findViewById(R.id.integrated_stove_open);
        intergratedStoveClose = (Button)findViewById(R.id.integrated_stove_close);
        intergratedStoveOpen.setOnClickListener(this);
        intergratedStoveClose.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.back)
        {
            finish();
        }else if(view.getId() == R.id.integrated_stove_open){
//            Toast.makeText(this,"集成灶已开",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(IntergratedStoveActivity.this, SocketDemo.class);
            startActivity(intent);
//            try {
//                ClientCode clientCode = new ClientCode();
//            }catch (IOException e){
//                e.printStackTrace();
//            }
        }else if(view.getId() == R.id.integrated_stove_close){
            Toast.makeText(this,"集成灶已关",Toast.LENGTH_SHORT).show();
        }
    }
}
