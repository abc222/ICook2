package com.example.wangyuchao.icook.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangyuchao.icook.R;
import com.example.wangyuchao.icook.utilityClass.ShowInformation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYuchao on 2017/6/5.
 */

public class DeviceActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "DeviceActivity";
    private static final String HOST = "115.28.52.206";
    private static final int PORT = 14444;

    private ImageView user;
    private Button integrated_stove;
    private Button cooker_hood;
    private Button light;
    private Button window;
    private Button soybean_milk_machine;
    private Button rice_cooker;
    private Button faucet;
    private LinearLayout more;

    private TextView temp;
    private TextView hum;
    private TextView powr;
    private TextView smoke;
    private Button update;


    private final int what = 1;

    private StringBuilder response;

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            String str = msg.obj + "";

            str = str.replace("[","");
            str = str.replace("]" ,"");
            String[] str1 = str.split(",");
            Log.e("DeviceActivity",str);
            Log.e("DeviceActivity",str1[0] + "  " + str1[1] +  "  " + str1[2]);

            if(str1[0] != "" || str1[1] != ""|| str1[2] != ""|| str1[3] != ""){
                temp.setText(str1[1]);
                hum.setText(str1[2]);
                smoke.setText(str1[3]);
                powr.setText(str1[0]);
            }
//            switch (msg.what) {
//                case what:
//                    Gson gson = new Gson();
//
//                    ArrayList<ShowInformation> showInformation = gson.fromJson(msg.obj.toString(), new TypeToken<List<ShowInformation>>(){}.getType());
//                    //json数据为数组使用这种方式获取，即地址有多条数据
//
//                    String temp1 = showInformation.get(0).getTemp();
//                    String hum1 = showInformation.get(0).getHum();
//                    String powr1 = showInformation.get(0).getPowr();
//
//                    temp.setText(temp1);
//                    hum.setText(hum1);
//                    powr.setText(powr1);
//                    break;
//            }

           // String str = "[{'temp':'22','hum':'221','powr':'111'} ]";
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {

        //去掉系统自带标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);

        integrated_stove = (Button) findViewById(R.id.integrated_stove);
        cooker_hood = (Button) findViewById(R.id.cooker_hood);
        light = (Button)findViewById(R.id.light);
        window = (Button)findViewById(R.id.window);
        soybean_milk_machine = (Button)findViewById(R.id.soybean_milk_machine);
        rice_cooker = (Button)findViewById(R.id.rice_cooker);
        faucet = (Button)findViewById(R.id.faucet);
        more = (LinearLayout)findViewById(R.id.more);

        temp = (TextView)findViewById(R.id.show_temp);
        hum = (TextView)findViewById(R.id.show_hum);
        powr = (TextView)findViewById(R.id.show_powr);
        smoke = (TextView)findViewById(R.id.smoke);
        update = (Button)findViewById(R.id.update_information);

        integrated_stove.setOnClickListener(this);
        cooker_hood.setOnClickListener(this);
        light.setOnClickListener(this);
        window.setOnClickListener(this);
        soybean_milk_machine.setOnClickListener(this);
        rice_cooker.setOnClickListener(this);
        faucet.setOnClickListener(this);
        more.setOnClickListener(this);
        update.setOnClickListener(this);


        //启动线程，接收服务器发送过来的数据
//        new Thread(DeviceActivity.this).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//
//                    if(in != null) {
//                        Log.e(TAG, "准备接收数据 = ");
//                        //这里运行不进去，到这里就有错了
//                        try {
//                            Log.e(TAG, "返回的数据 = " + in.readLine().length());
//                            while ((content = in.readLine()) != null) {
//                                content += "\n";
//                                Log.e(TAG, "这是接收的数据");
//                                Message msg = new Message();
//                                msg.obj = content;
//                                mHandler.sendMessage(msg);
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//
//            }
//
//        }).start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.update_information:
                //Log.e(TAG , "adfavda中序");
//                Message msg = new Message();
//                mHandler.sendMessage(msg);
 //               getInfo();

                //sendHttpRequest("http://115.28.52.206/iCook/test.txt");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("http://115.28.52.206/iCook/test.txt");
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            conn.connect();
                            InputStream is = conn.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                            String str = reader.readLine();
                            Log.e("DeviceActivity",str);

                            Message msg =  new Message();
                            msg.obj = str;
                            mHandler.sendMessage(msg);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();

                break;
            case R.id.integrated_stove:
                Intent intent1 = new Intent(DeviceActivity.this, IntergratedStoveActivity.class);
                startActivity(intent1);
                break;
            case R.id.cooker_hood:
                Intent intent2 = new Intent(DeviceActivity.this, CookerHoodActivity.class);
                startActivity(intent2);
                break;

            case R.id.rice_cooker:
                //引入操作布局
                LayoutInflater riceCookerInflater = LayoutInflater.from(getApplicationContext());
                View riceCookerLayout = riceCookerInflater.inflate(R.layout.activity_device_operate_others_dialog, null);
                //弹出框，控制电饭煲
                AlertDialog.Builder riceCookerDiallog = new AlertDialog.Builder(this);
                riceCookerDiallog.setView(riceCookerLayout);   //定义为自定义的布局
                riceCookerDiallog.setCancelable(true);   //设置为可取消弹出框
                riceCookerDiallog.create().show();    //创建并显示弹出框
                //对开关电饭煲设置监听事件
                Button open = (Button) riceCookerLayout.findViewById(R.id.open);   //开
                open.setOnClickListener(this);
                Button close = (Button) riceCookerLayout.findViewById(R.id.close);  //关
                close.setOnClickListener(this);
                break;
            case R.id.open:
                Toast.makeText(this,"电饭煲已开",Toast.LENGTH_SHORT).show();
                break;
            case R.id.close:
                Toast.makeText(this,"电饭煲已关",Toast.LENGTH_SHORT).show();
                break;
            case R.id.soybean_milk_machine:
                //引入操作布局
                LayoutInflater soybean_milk_machineInflater = LayoutInflater.from(getApplicationContext());
                View soybean_milk_machineLayout = soybean_milk_machineInflater.inflate(R.layout.activity_device_operate_others_dialog, null);
                //弹出框，控制电饭煲
                AlertDialog.Builder soybean_milk_machineDiallog = new AlertDialog.Builder(this);
                soybean_milk_machineDiallog.setView(soybean_milk_machineLayout);   //定义为自定义的布局
                soybean_milk_machineDiallog.setCancelable(true);   //设置为可取消弹出框
                soybean_milk_machineDiallog.create().show();    //创建并显示弹出框
                break;
            case R.id.window:
                //引入窗户操作布局
                LayoutInflater windowInflater = LayoutInflater.from(getApplicationContext());
                View windowLayout = windowInflater.inflate(R.layout.activity_device_operate_others_dialog, null);
                //弹出框，控制窗户。
                AlertDialog.Builder windowOperateWindowDiallog = new AlertDialog.Builder(this);
                windowOperateWindowDiallog.setView(windowLayout);   //定义为自定义的布局
                windowOperateWindowDiallog.setCancelable(true);   //设置为可取消弹出框
                windowOperateWindowDiallog.create().show();    //创建并显示弹出框
                break;
//                //对开关窗户图片设置监听事件
//                ImageView openWindow = (ImageView) windowLayout.findViewById(R.id.open_window_iv);   //开窗
//                openWindow.setOnClickListener(this);
//                ImageView closeWindow = (ImageView) windowLayout.findViewById(R.id.close_window_iv);  //关窗
//                closeWindow.setOnClickListener(this);
//            case R.id.open_window_iv:
//                Toast.makeText(this,"窗已开",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.close_window_iv:
//                Toast.makeText(this,"窗已关",Toast.LENGTH_SHORT).show();
//                break;

            case R.id.light:
                //引入节能灯操作布局
                LayoutInflater lightInflater = LayoutInflater.from(getApplicationContext());
                View lightLayout = lightInflater.inflate(R.layout.activity_device_operate_others_dialog, null);
                //弹出框，控制灯。
                AlertDialog.Builder lightOperateWindowDiallog = new AlertDialog.Builder(this);
                lightOperateWindowDiallog.setView(lightLayout);   //定义为自定义的布局
                lightOperateWindowDiallog.setCancelable(true);   //设置为可取消弹出框
                lightOperateWindowDiallog.create().show();    //创建并显示弹出框
                break;
//                //对开关窗户图片设置监听事件
//                Button openLight = (Button) lightLayout.findViewById(R.id.open_light);   //开灯
//                openLight.setOnClickListener(this);
//                Button closeLight = (Button) lightLayout.findViewById(R.id.close_light);   //关灯
//                closeLight.setOnClickListener(this);
//            case R.id.open_light:
//                Toast.makeText(this,"灯已开",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.close_light:
//                Toast.makeText(this,"灯已关",Toast.LENGTH_SHORT).show();
//                break;

            case R.id.faucet:
                //引入水龙头操作布局
                LayoutInflater faucetInflater = LayoutInflater.from(getApplicationContext());
                View faucetLayout = faucetInflater.inflate(R.layout.activity_device_operate_others_dialog, null);
                //弹出框，控制灯。
                AlertDialog.Builder faucetOperateWindowDiallog = new AlertDialog.Builder(this);
                faucetOperateWindowDiallog.setView(faucetLayout);   //定义为自定义的布局
                faucetOperateWindowDiallog.setCancelable(true);   //设置为可取消弹出框
                faucetOperateWindowDiallog.create().show();    //创建并显示弹出框
//                //对开关窗户图片设置监听事件
//                Button openFaucet = (Button) faucetLayout.findViewById(R.id.open_faucet);   //开灯
//                openFaucet.setOnClickListener(this);
//                Button closeFaucet = (Button) faucetLayout.findViewById(R.id.close_faucet);   //关灯
//                closeFaucet.setOnClickListener(this);
                break;
//            case R.id.open_faucet:
//                Toast.makeText(this,"水龙头已开",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.close_faucet:
//                Toast.makeText(this,"水龙头已关",Toast.LENGTH_SHORT).show();
//                break;

      //      case R.id.more:break;

            default:break;

        }
    }

//    private Socket socket = null;
//    private BufferedReader in = null;
//    private PrintWriter out = null;
//    private String content = "";
//
//    private void getInfo() {
//
//        //只要与控件有关的都是UI操作。放在主线程。
//        final String msg = "gg";
//
//        if (socket.isConnected()) {
//            if (!socket.isOutputShutdown()) {
//
//                //网络操作，放在子线程
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Log.e(TAG , "发送数据子线程");
//
//                        out.println(msg);
//                    }
//                }).start();
//
//                Log.e(TAG,"button");
//            }
//        }
//    }

    /**
     * 读取服务器发来的信息，并通过Handler发给UI线程
     */
//    public void run() {
//        try {
//            socket = new Socket(HOST, PORT);
//            Boolean str = socket.isConnected();
//            String s = str.toString();
//            in = new BufferedReader(new InputStreamReader(socket
//                    .getInputStream()));
//            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
//                    socket.getOutputStream())), true);
//            Log.e(TAG , "连接成功1 = " + s);
//
//
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//    }




    // address为数据请求地址
    // 像联网进行数据请求这种耗时操作，最好都是放到子线程中进行，以避免阻塞主线程
//    public void sendHttpRequest(final String address) {
//        new Thread(new Runnable() {
//            public void run() {
//                HttpURLConnection connection = null;
//                try {
//                    URL url = new URL(address);
//                    connection = (HttpURLConnection) url.openConnection();
//                    // 请求方式
//                    connection.setRequestMethod("GET");
//                    // 连接超时
//                    connection.setConnectTimeout(8000);
//                    // 读取超时
//                    connection.setReadTimeout(8000);
//                    connection.connect();
//                    // 获取输入流
//                    InputStream in = connection.getInputStream();
//                    BufferedReader reader = new BufferedReader(
//                            new InputStreamReader(in, "UTF-8"));
//                    response = new StringBuilder();
//                    String line;
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//                    Message message = new Message();
//                    message.what = 1;
//                    mHandler.sendMessage(message);
//                } catch (Exception e) {
//
//                } finally {
//                    if (connection != null) {
//                        connection.disconnect();
//                    }
//                }
//            }
//        }).start();
//    }
}