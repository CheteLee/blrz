package com.ctlee.mychat.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctlee.mychat.R;

import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by ctLee on 2017/8/7.
 */

public class SlideLVAdapter extends BaseAdapter {
    private List<Map<String,Object>> data;
    private LayoutInflater layoutInflater;
    private Context context;
    public SlideLVAdapter(Context context,List<Map<String,Object>> data,LayoutInflater layoutInflater){
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
            convertView = layoutInflater.inflate(R.layout.slidelv_layout,null);//根据自定义的Item布局加载布局
            holder.imageView = (ImageView) convertView.findViewById(R.id.img_slide);
            holder.textViewl = (TextView) convertView.findViewById(R.id.tv_content_slide);
            convertView.setTag(holder);//将设置好的布局保存到缓存中，并设置在Tag中，以便后面取出Tag
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setBackgroundResource((Integer) data.get(position).get("img"));
        holder.textViewl.setText((String) data.get(position).get("tvcontent"));
        return convertView;
    }

    /**
     * 外面定义ViewHolder静态类
     */
    static class ViewHolder{
        public ImageView imageView;
        public TextView textViewl;
        public TextView textView2;
    }
}
