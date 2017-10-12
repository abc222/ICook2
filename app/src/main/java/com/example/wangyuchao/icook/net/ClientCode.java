package com.example.wangyuchao.icook.net;
import android.util.Log;

import java.io.BufferedReader;

import java.io.BufferedWriter;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;

import java.io.PrintWriter;

import java.net.InetAddress;

import java.net.Socket;

/**
 * Created by WangYuchao on 2017/6/27.
 */

public class ClientCode

{
    public ClientCode()throws IOException{

        System.out.println("Dollar is construct!");
        //要对应服务器端的12222端口号

        Socket socket = new Socket("115.28.52.206", 12222);


        try
        {

            System.out.println("socket=" + socket);
            Log.d("ABC","socket = " + socket);

            // 设置IO句柄

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String str = in.readLine();


//            System.out.println(str);
//
//            String info = null;

            if(str == null){

                System.out.println("没有收到信息");

            }else{

                System.out.println("服务器说："+str);

            }

            PrintWriter out = new PrintWriter(new BufferedWriter(

                    new OutputStreamWriter(socket.getOutputStream())), true);

            out.println("icookicook\n");

        }

        finally

        {

            System.out.println("the Client socket hsa established.");

            //    socket.close();

        }
    }



}
