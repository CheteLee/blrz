package com.ctlee.mychat.activity;

import android.content.Context;
import android.content.Intent;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ctlee.mychat.R;
import com.ctlee.mychat.adapter.SlideLVAdapter;
import com.ctlee.mychat.fragment.FriendsFragment;
import com.ctlee.mychat.fragment.MessageFragment;
import com.ctlee.mychat.fragment.ZoneFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawerLayoutMainActivity extends FragmentActivity implements View.OnClickListener {
    private DrawerLayout drawlayout;
//    private FrameLayout framelayout;
    private ListView listView;
    private Button bt_go;

    private RadioGroup rg_bottom;
    private RadioButton rb_msg;
    private RadioButton rb_friends;
    private RadioButton rb_zone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
//        initFragment();

        listenner();
    }


    //    @Override
//    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
//        return super.onCreateView(parent, name, context, attrs);
//    }

//    @Override
//    public View onCreateView(String name, Context context, AttributeSet attrs) {
//        return super.onCreateView(name, context, attrs);
//    }

    public void findViews(){
        drawlayout = (DrawerLayout) findViewById(R.id.drawlayout);
//        framelayout = (FrameLayout) findViewById(R.id.framelayout);
//        framelayout = (FrameLayout) findViewById(R.id.fl_container);
        listView = (ListView) findViewById(R.id.lv_slide);
//        bt_go = (Button) findViewById(R.id.bt_go);

        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom);
        rb_msg = (RadioButton) findViewById(R.id.rb_msg);
        rb_friends = (RadioButton) findViewById(R.id.rb_friends);
        rb_zone = (RadioButton) findViewById(R.id.rb_zone);

        SlideLVAdapter adapter = new SlideLVAdapter(this,getListData(),LayoutInflater.from(this));
        listView.setAdapter(adapter);
    }

    public void initFragment(){
        FriendsFragment ff = new FriendsFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fl_container,ff);
        ft.commit();
    }

    /**
     * 初始化侧滑菜单的listview中的数据
     * @return
     */
    private List<Map<String,Object>> getListData(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map;
        for(int i = 0; i < 20; i++){
            map = new HashMap<String, Object>();
            map.put("img",R.mipmap.ic_launcher_round);
            map.put("tvcontent","黄金叶_"+i);
            list.add(map);
        }
        return list;
    }

    /**
     * 点击事件监听
     */
    public void listenner(){
        drawlayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DrawerLayoutMainActivity.this,"你点击了:"+position,Toast.LENGTH_SHORT).show();
            }
        });
//        bt_go.setOnClickListener(this);

        rg_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
               if(rb_friends.getId() == checkedId){
                   //选中friends
                   FriendsFragment ff = new FriendsFragment();
                   FragmentManager fm = getSupportFragmentManager();
                   FragmentTransaction ft = fm.beginTransaction();
                   ft.replace(R.id.fl_container,ff);
                   ft.commit();
               }
               if(rb_msg.getId() == checkedId){
                   //选中msg
                   MessageFragment mf = new MessageFragment();
                   FragmentManager fm = getSupportFragmentManager();
                   FragmentTransaction ft = fm.beginTransaction();
                   ft.replace(R.id.fl_container,mf);
                   ft.commit();
               }
               if(rb_zone.getId() == checkedId){
                   //选中zone
                   ZoneFragment zf = new ZoneFragment();
                   FragmentManager fm = getSupportFragmentManager();
                   FragmentTransaction ft = fm.beginTransaction();
                   ft.replace(R.id.fl_container,zf);
                   ft.commit();
               }
            }
        });
        rb_msg.setChecked(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.bt_go:
//                Intent intent = new Intent();
//                intent.setClass(DrawerLayoutMainActivity.this,MainActivity.class);
//                startActivity(intent);
//                break;
        }
    }


}
