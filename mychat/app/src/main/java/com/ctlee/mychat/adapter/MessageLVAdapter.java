package com.ctlee.mychat.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ctlee.mychat.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ctLee on 2017/8/12.
 */

public class MessageLVAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;
    public MessageLVAdapter(Context context, List<Map<String,Object>> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public MessageLVAdapter(Context context, List<Map<String,Object>> data, LayoutInflater layoutInflater){
        this.context = context;
        this.data = data;
        this.layoutInflater = layoutInflater;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(null == convertView){//如果缓存convertView为空，则需要创建View
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.msg_layout,null);//根据自定义布局item加载布局
            holder.textView = (TextView) convertView.findViewById(R.id.tv_msgfrom);
            convertView.setTag(holder);//将设置好的布局保存在缓存中，并设置在Tag中，方便后面取出Tag

        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Log.i("sssssssss1",data.toString());
        Log.i("ss,data.get(position):",data.get(position).toString());
        holder.textView.setText((String)data.get(position).get("msgfrom"));
        return convertView;
    }

    static class ViewHolder{
        TextView textView;
    }
}
