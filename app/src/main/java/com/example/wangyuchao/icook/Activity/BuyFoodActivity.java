package com.example.wangyuchao.icook.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.wangyuchao.icook.R;

/**
 * Created by WangYuchao on 2017/6/10.
 */

public class BuyFoodActivity extends Activity implements View.OnClickListener{

    private Button cookmaker;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_buy_food);

        cookmaker = (Button)findViewById(R.id.cook_method);
        cookmaker.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cook_method:
                //引入菜谱做法操作布局
                LayoutInflater cookMethodInflater = LayoutInflater.from(getApplicationContext());
                View cookMethodLayout = cookMethodInflater.inflate(R.layout.cook_method_dialog, null);
                //弹出框，查看做法
                AlertDialog.Builder cookMethodDiallog = new AlertDialog.Builder(this);
                cookMethodDiallog.setView(cookMethodLayout);   //定义为自定义的布局
                cookMethodDiallog.setCancelable(true);   //设置为可取消弹出框
                cookMethodDiallog.create().show();    //创建并显示弹出框
                break;
            default:break;
        }
    }
}
