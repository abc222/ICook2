package com.example.wangyuchao.icook.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wangyuchao.icook.utilityClass.Message;
import com.example.wangyuchao.icook.R;

import java.util.List;

/**
 * Created by WangYuchao on 2017/6/7.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>{

    private List<Message> mMessage;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView pitcureImage;
        public ViewHolder(View view){
            super(view);
            pitcureImage = (ImageView)view.findViewById(R.id.pitcure);
        }
    }

    public MessageAdapter(List<Message> myList){
        mMessage = myList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message message = mMessage.get(position);
        holder.pitcureImage.setImageResource(message.getImageId1());
    }

    @Override
    public int getItemCount() {
        return mMessage.size();
    }
}
