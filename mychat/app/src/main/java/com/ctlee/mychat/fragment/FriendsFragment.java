package com.ctlee.mychat.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ctlee.mychat.R;
import com.ctlee.mychat.indicator.TabStrip;

/**
 * Created by ctLee on 2017/8/11.
 */

public class FriendsFragment extends Fragment {

    private View view;
    private RadioGroup rg_child_top;
    private RadioButton rb_child_friends;
    private RadioButton rb_child_devices;
    private RadioButton rb_child_groups;

    private ViewPager vp_child_container;
    private TabStrip sv_indicator;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friends, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
        findviews();
        setListener();
        return view;
    }
    public void findviews(){
//        rg_child_top = (RadioGroup) view.findViewById(R.id.rg_child_bottom);
//        rb_child_friends = (RadioButton) view.findViewById(R.id.rb_child_friends);
//        rb_child_devices = (RadioButton) view.findViewById(R.id.rb_child_devices);
//        rb_child_groups = (RadioButton) view.findViewById(R.id.rb_child_groups);

        vp_child_container = (ViewPager) view.findViewById(R.id.vp_child_container);
        sv_indicator = (TabStrip) view.findViewById(R.id.sv_indicator);
        pageAdapter=newFragmentPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);
        tabStrip.setViewPager(viewPager);
    }

    public void setListener(){
        rg_child_top.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(rb_child_friends.getId() == checkedId){
                    ChildFriendsFragment cff = new ChildFriendsFragment();
                    FragmentManager fm = getChildFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.rl_child_container,cff);
                    ft.commit();
                }
                if (rb_child_devices.getId() == checkedId){
                    ChildDevicesFragment cdf = new ChildDevicesFragment();
                    FragmentManager fm = getChildFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.rl_child_container,cdf);
                    ft.commit();
                }
                if(rb_child_groups.getId() == checkedId){
                    Toast.makeText(getActivity(),"group功能正在开发中...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
