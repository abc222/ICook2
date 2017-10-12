package com.example.wangyuchao.icook.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.wangyuchao.icook.Adapter.MessageAdapter;
import com.example.wangyuchao.icook.R;
import com.example.wangyuchao.icook.utilityClass.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYuchao on 2017/6/5.
 */

public class MessageActivity extends Activity {
    private List<Message> messagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        initMessage();//初始化
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.message_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        MessageAdapter adapter = new MessageAdapter(messagesList);
        recyclerView.setAdapter(adapter);
    }

    private void initMessage(){
        Message message1 = new Message(R.drawable.message_pitcure1);
        messagesList.add(message1);
        Message message2 = new Message(R.drawable.message_pitcure2);
        messagesList.add(message2);
        Message message3 = new Message(R.drawable.message_pitcure3);
        messagesList.add(message3);
        Message message4 = new Message(R.drawable.message_pitcure4);
        messagesList.add(message4);
        Message message5 = new Message(R.drawable.message_pitcure5);
        messagesList.add(message5);
    }
}
