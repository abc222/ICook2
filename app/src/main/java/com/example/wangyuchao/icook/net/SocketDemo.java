package com.example.wangyuchao.icook.net;

import android.app.Activity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wangyuchao.icook.R;
import com.example.wangyuchao.icook.utilityClass.ShowInformation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by WangYuchao on 2017/6/27.
 */

public class SocketDemo extends Activity implements Runnable {

    private static final String TAG = "SocketDemo";
    private TextView tv_msg = null;
    private EditText ed_msg = null;
    private Button btn_send = null;

    private static final String HOST = "115.28.52.206";
    private static final int PORT = 14444;

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String content = "";
    public static String temp;
    public static String hum;
    public static String powr;

    public Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            String str = "{'temp':'22','hum':'221','powr'：'111'}";

            Log.e(TAG , "content = " + msg.obj);
            tv_msg.setText(tv_msg.getText().toString() + content);

            Gson gson = new Gson();

            ArrayList<ShowInformation> showInformation = gson.fromJson(msg.obj.toString(), new TypeToken<List<ShowInformation>>(){}.getType());
            //json数据为数组使用这种方式获取，即地址有多条数据

            temp = showInformation.get(0).getTemp();
            hum = showInformation.get(0).getHum();
            powr = showInformation.get(0).getPowr();

            Log.e(TAG,temp);
            Log.e(TAG,hum);
            Log.e(TAG,powr);


        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sockrtdemo);

        tv_msg = (TextView) findViewById(R.id.TextView);
        ed_msg = (EditText) findViewById(R.id.EditText);
        btn_send = (Button) findViewById(R.id.Button);

        btn_send.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View v) {

                Message m = new Message();
                mHandler.sendMessage(m);
                // TODO Auto-generated method stub
                //只要与控件有关的都是UI操作。放在主线程。
                final String msg = ed_msg.getText().toString();

                if (socket.isConnected()) {
                    if (!socket.isOutputShutdown()) {

                        //网络操作，放在子线程
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG , "发送数据子线程");

                                out.println(msg);
                            }
                        }).start();

                        Log.e(TAG,"button");
                    }
                }
            }
        });



        //启动线程，接收服务器发送过来的数据
        new Thread(SocketDemo.this).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {



                    if(in != null) {
                        Log.e(TAG, "准备接收数据 = ");
                        //这里运行不进去，到这里就有错了
                        try {
                            Log.e(TAG, "返回的数据 = " + in.readLine().length());


                            while ((content = in.readLine()) != null) {
                                content += "\n";
                                Log.e(TAG, "这是接收的数据");
                                Message msg = new Message();
                                msg.obj = content;
                                mHandler.sendMessage(msg);
                            }



                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }

        }).start();
    }

    /**
     * 如果连接出现异常，弹出AlertDialog！
     */
    public void ShowDialog(String msg) {
        new AlertDialog.Builder(this).setTitle("notification").setMessage(msg)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
    }

    /**
     * 读取服务器发来的信息，并通过Handler发给UI线程
     */
    public void run() {
        try {
            socket = new Socket(HOST, PORT);
            Boolean str = socket.isConnected();
            String s = str.toString();
            in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);
            if(s == "true"){
                Log.e(TAG , "连接成功 = " + s);
            }else{
                Log.e(TAG , "连接失败");
            }

 /*
                        if (!socket.isClosed()) {
                            if (socket.isConnected()) {
                                if (!socket.isInputShutdown()) {

                                    while ((content = in.readLine()) != null) {
                                        content += "\n";
                                        Log.e(TAG , "这是接收的数据");
                                        Message msg = new Message();
                                        msg.obj = content;
                                        mHandler.sendMessage(msg);
                                    }

                                }
                            }
                        }*/

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }


}
