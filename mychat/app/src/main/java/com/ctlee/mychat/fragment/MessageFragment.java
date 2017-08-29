package com.ctlee.mychat.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ctlee.mychat.R;
import com.ctlee.mychat.adapter.MessageLVAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

/**
 * Created by ctLee on 2017/8/11.
 */

public class MessageFragment extends Fragment {
    private View view;
    private ListView lv_msg;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message,container,false);
//        view.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
        findViews();
        initData();
        setListeners();
        return view;
    }

    public void findViews() {
        lv_msg = (ListView) view.findViewById(R.id.lv_msg);

    }
    public void initData(){
        List<Map<String,Object>> data = new ArrayList<>();
        Map<String,Object> map;
        for(int i = 0; i < 50; i++){
            map = new HashMap<String,Object>();
            map.put("msgfrom",i+"");
            data.add(map);
        }
        Log.i("sssssssss",data.toString());
        MessageLVAdapter mlvAdapter = new MessageLVAdapter(getActivity(),data);
        lv_msg.setAdapter(mlvAdapter);
    }
    public void setListeners(){
        lv_msg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"你点击了："+position,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
