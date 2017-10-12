package com.example.wangyuchao.icook.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.media.session.IMediaControllerCallback;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.wangyuchao.icook.Adapter.SupermarketAdapter;
import com.example.wangyuchao.icook.R;
import com.example.wangyuchao.icook.utilityClass.Supermarket;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by WangYuchao on 2017/7/2.
 */

public class ChooseSupermarketActivity extends Activity implements View.OnClickListener{


    private ProgressDialog progressDialog;
    private List<Supermarket> supermarkets = new ArrayList<>();
    private ImageView back;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_supermarket);
        initSupermarkets();//初始化超市数据
        SupermarketAdapter adapter = new SupermarketAdapter
                (ChooseSupermarketActivity.this,R.layout.supermarket_item,supermarkets);
        ListView listView = (ListView)findViewById(R.id.supermarket_list_view);
        listView.setAdapter(adapter);
        back = (ImageView)findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    private void initSupermarkets(){

//        this.showProgressBar();//显示加载框

        for(int i = 0;i < 2;i ++){
            Supermarket woerma = new Supermarket("沃尔玛",R.drawable.woerma);
            supermarkets.add(woerma);
            Supermarket darunfa = new Supermarket("大润发",R.drawable.darunfa);
            supermarkets.add(darunfa);
            Supermarket jialefu = new Supermarket("家乐福",R.drawable.jialefu);
            supermarkets.add(jialefu);
        }
    }


    private void showProgressBar() {

        progressDialog = new ProgressDialog(ChooseSupermarketActivity.this , 1);//后面的参数是风格，1比较好看
        progressDialog.setMessage("数据正在加载中......");
        progressDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();break;
            default:break;
        }
    }
}
