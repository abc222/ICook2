package com.example.wangyuchao.icook.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.wangyuchao.icook.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WangYuchao on 2017/5/23.
 */

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewPager pager = null;
    private PagerTabStrip tabStrip = null;
    private ArrayList<View> viewContainter = new ArrayList<View>();
    public String TAG = "tag";
    private EditText dishstyle;
    private RadioGroup rg;
    private Button inquire;
    private Button cook_method;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        //去掉系统自带标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
     // rg = (RadioGroup)this.findViewById(R.id.rg) ;
        dishstyle = (EditText)findViewById(R.id.dishstyle);
        inquire = (Button)findViewById(R.id.inquire);
        cook_method = (Button)findViewById(R.id.cook_method);
        pager = (ViewPager) this.findViewById(R.id.viewpager);

        inquire.setOnClickListener(this);
        cook_method.setOnClickListener(this);

        //广告轮播
        View view1 = LayoutInflater.from(this).inflate(R.layout.activity_home_viewpager_pitcure1, null);
        View view2 = LayoutInflater.from(this).inflate(R.layout.activity_home_viewpager_pitcure2, null);
        View view3 = LayoutInflater.from(this).inflate(R.layout.activity_home_viewpager_pitcure3, null);
        View view4 = LayoutInflater.from(this).inflate(R.layout.activity_home_viewpager_pitcure4, null);
        //viewpager开始添加view
        viewContainter.add(view1);
        viewContainter.add(view2);
        viewContainter.add(view3);
        viewContainter.add(view4);

        pager.setAdapter(new PagerAdapter() {

            //viewpager中的组件数量
            @Override
            public int getCount() {
                return viewContainter.size();
            }
            //滑动切换的时候销毁当前的组件
            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                ((ViewPager) container).removeView(viewContainter.get(position));
            }
            //每次滑动的时候生成的组件
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ((ViewPager) container).addView(viewContainter.get(position));
                return viewContainter.get(position);
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1;
            }

            @Override
            public int getItemPosition(Object object) {
                return super.getItemPosition(object);
            }

        });

        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int arg0) {
                Log.d(TAG, "--------changed:" + arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                Log.d(TAG, "-------scrolled arg0:" + arg0);
                Log.d(TAG, "-------scrolled arg1:" + arg1);
                Log.d(TAG, "-------scrolled arg2:" + arg2);
            }

            @Override
            public void onPageSelected(int arg0) {
                Log.d(TAG, "------selected:" + arg0);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.inquire:
                //跳转到采购食材
//                String dish = dishstyle.getText().toString();
//                RadioButton checkButton = (RadioButton)rg.findViewById(rg.getCheckedRadioButtonId());
//                String num = checkButton.getText().toString();
//                if(dish.equals("鱼香肉丝")&&num.equals("单人")){
//                    Intent intent = new Intent(HomeActivity.this, BuyFoodActivity.class);
//                    startActivity(intent);
//                }break;
                Intent intent = new Intent(HomeActivity.this, ChooseSupermarketActivity.class);
                startActivity(intent);
//                new Json();
                break;
            case R.id.cook_method:
                String dish = dishstyle.getText().toString();
                Intent intent4 = new Intent(HomeActivity.this, CookActivity.class);
                intent4.putExtra("content",dish);
                startActivity(intent4);

//                String dish = dishstyle.getText().toString();
//                if(dish.equals(null)){
//                    Toast.makeText(HomeActivity.this,"请输入菜品名称！",Toast.LENGTH_SHORT).show();
//                }else if(dish.equals("鱼香肉丝")){
//                    //引入菜谱做法操作布局
//                    LayoutInflater cookMethodInflater = LayoutInflater.from(getApplicationContext());
//                    View cookMethodLayout = cookMethodInflater.inflate(R.layout.food_yuxiangrousi, null);
//                    //弹出框，查看做法
//                    AlertDialog.Builder cookMethodDiallog = new AlertDialog.Builder(this);
//                    cookMethodDiallog.setView(cookMethodLayout);   //定义为自定义的布局
//                    cookMethodDiallog.setCancelable(true);   //设置为可取消弹出框
//                    cookMethodDiallog.create().show();    //创建并显示弹出框
//                }else if(dish.equals("鱼香肉丝")){
//                    //引入菜谱做法操作布局
//                    LayoutInflater cookMethodInflater = LayoutInflater.from(getApplicationContext());
//                    View cookMethodLayout = cookMethodInflater.inflate(R.layout.food_yuxiangrousi, null);
//                    //弹出框，查看做法
//                    AlertDialog.Builder cookMethodDiallog = new AlertDialog.Builder(this);
//                    cookMethodDiallog.setView(cookMethodLayout);   //定义为自定义的布局
//                    cookMethodDiallog.setCancelable(true);   //设置为可取消弹出框
//                    cookMethodDiallog.create().show();    //创建并显示弹出框
//                }else if(dish.equals("鱼香肉丝")){
//                    //引入菜谱做法操作布局
//                    LayoutInflater cookMethodInflater = LayoutInflater.from(getApplicationContext());
//                    View cookMethodLayout = cookMethodInflater.inflate(R.layout.food_yuxiangrousi, null);
//                    //弹出框，查看做法
//                    AlertDialog.Builder cookMethodDiallog = new AlertDialog.Builder(this);
//                    cookMethodDiallog.setView(cookMethodLayout);   //定义为自定义的布局
//                    cookMethodDiallog.setCancelable(true);   //设置为可取消弹出框
//                    cookMethodDiallog.create().show();    //创建并显示弹出框
//                }else if(dish.equals("鱼香肉丝")){
//                    //引入菜谱做法操作布局
//                    LayoutInflater cookMethodInflater = LayoutInflater.from(getApplicationContext());
//                    View cookMethodLayout = cookMethodInflater.inflate(R.layout.food_yuxiangrousi, null);
//                    //弹出框，查看做法
//                    AlertDialog.Builder cookMethodDiallog = new AlertDialog.Builder(this);
//                    cookMethodDiallog.setView(cookMethodLayout);   //定义为自定义的布局
//                    cookMethodDiallog.setCancelable(true);   //设置为可取消弹出框
//                    cookMethodDiallog.create().show();    //创建并显示弹出框
//                }break;
            default:break;
        }
    }


//    Gson gson = new Gson();
//
//    simpleGoodsArrayList = gson.fromJson(jsonData, new TypeToken<List<SimpleHotGoods>>() {
//    }.getType());    //json数据为数组使用这种方式获取，即地址有多条数据

}
