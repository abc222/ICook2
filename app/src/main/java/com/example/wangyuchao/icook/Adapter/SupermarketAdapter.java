package com.example.wangyuchao.icook.Adapter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wangyuchao.icook.R;
import com.example.wangyuchao.icook.utilityClass.Supermarket;

import java.util.List;

/**
 * Created by WangYuchao on 2017/7/2.
 */

public class SupermarketAdapter extends ArrayAdapter <Supermarket>{

    private int resourcedId;

    public SupermarketAdapter(Context context, int textViewResourceId, List<Supermarket> objects){
        super(context,textViewResourceId,objects);
        resourcedId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Supermarket supermarket = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourcedId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.supermarketImage = (ImageView)view.findViewById(R.id.supermarker_image);
            viewHolder.supermarketName = (TextView)view.findViewById(R.id.supermarker_name);
            view.setTag(viewHolder);
        }else{
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.supermarketImage.setImageResource(supermarket.getImageId());
        viewHolder.supermarketName.setText(supermarket.getName());
        return view;
    }

    class ViewHolder{
        ImageView supermarketImage;
        TextView supermarketName;
    }

}
