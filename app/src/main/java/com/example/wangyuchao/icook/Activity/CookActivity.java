package com.example.wangyuchao.icook.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangyuchao.icook.R;

import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WangYuchao on 2017/7/3.
 */

public class CookActivity extends Activity implements View.OnClickListener{

    private TextView name;
    private TextView material;
    public static TextView method;
    private ImageView back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook);

        name = (TextView)findViewById(R.id.name);
        material = (TextView)findViewById(R.id.material);
        method = (TextView)findViewById(R.id.method);
        back = (ImageView)findViewById(R.id.back);

        back.setOnClickListener(this);

        getRequest1();

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        name.setText(content);
        if(content.equals("鱼香肉丝")){
            material.setText("猪里脊肉300克,绿尖椒一根,胡萝卜1/4根,冬笋1/2根,黑木耳六朵");
            method.setText("1.猪肉切丝用黑胡椒、淀粉、生抽腌制一刻钟"+"\n\n"+
                    "2.所有配料切成丝备用"+"\n\n"+
                    "3.锅里倒油加热放入蒜末、老干妈豆豉煸炒出香味"+"\n\n"+
                    "4.先将浆好的肉丝倒入锅中煸炒然后倒入其他配料一起煸炒，放入适量的盐，加入白糖和香醋煸炒一会就可以出锅了");
        }else if(content.equals("宫保鸡丁")){
            material.setText("鸡腿2根,去衣花生仁适量,番茄酱适量,干辣椒适量,淀粉适量，葱姜适量");
            method.setText("1.将鸡腿去骨，鸡腿肉切成2厘米左右的丁，用生抽、料酒、姜末、盐，搅拌均匀，加入淀粉搅拌均匀，腌渍"+"\n\n"+
                    "2.干辣椒、葱切短节、蒜切片，碗内放生抽、白糖、醋、汤、水淀粉、盐，调好调料汁待用"+"\n\n"+
                    "3.锅内放油烧至六成热时，放入鸡丁，炒散，炒熟，盛出"+"\n\n"+
                    "4.放入炒熟的鸡丁、蒜片、葱节等，炒均\n\n"+
                    "5.最后加入去衣盐炒花生仁，即可");
        }else if(content.equals("热干面")||content.equals("武汉热干面")){
            material.setText("手工面条300克，" +
                    "黄瓜半根，" +
                    "香菜少许，" +
                    "八宝菜1小袋，" +
                    "葱1小棵");
            method.setText("1.买的面条\n" +
                    "2.芝麻酱用凉白开水调匀，或者是香油调匀！！\n" +
                    "3.八宝菜切小块\n" +
                    "4.黄瓜切丝，香菜切断，葱花\n" +
                    "5.调汁（醋，盐，鸡精，胡椒粉，生抽，糖）" +
                    "油烧热倒入小碗备用！！\n" +
                    "6.锅里放水，水开，面条煮7分熟，\n" +
                    "7.面条捞出来放在案板上，用小碗的油拌匀\n" +
                    "8.拌匀的面条，再次在锅里煮软和。\n" +
                    "9.盛在碗里，放入调好的汁，加入黄瓜丝，葱花（前几天做的辣椒油），八宝菜，芝麻酱，香菜！！\n" +
                    "10.拌好的面条，美味极了！" );
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                finish();break;
            default:break;
        }
    }









    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY ="4314117217b37d9b743c741820d64476";

    //1.菜谱大全
    public static void getRequest1(){
        String result =null;
        String url ="http://apis.juhe.cn/cook/query.php";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("menu","鱼香肉丝");//需要查询的菜谱名
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","json");//返回数据的格式,xml或json，默认json
        params.put("pn","");//数据返回起始下标
        params.put("rn","1");//数据返回条数，最大30
        params.put("albums","");//albums字段类型，1字符串，默认数组

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                method.setText(object.get("result").toString());
                Log.e("CookActivity","haha"+object.get("result"));
            }else{
                //method.setText("error_code")+":"+object.get("reason").toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


}
