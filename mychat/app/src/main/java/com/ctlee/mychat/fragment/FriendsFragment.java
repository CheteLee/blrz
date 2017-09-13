package com.ctlee.mychat.fragment;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
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

import java.util.ArrayList;
import java.util.List;

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
    private List<Fragment> mFragments = new ArrayList<Fragment>();
    private FragmentPagerAdapter pageAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friends, container, false);
//        return super.onCreateView(inflater, container, savedInstanceState);
        findviews();
        initViewPager();
//        setListener();
        return view;
    }
    public void findviews(){
//        rg_child_top = (RadioGroup) view.findViewById(R.id.rg_child_bottom);
//        rb_child_friends = (RadioButton) view.findViewById(R.id.rb_child_friends);
//        rb_child_devices = (RadioButton) view.findViewById(R.id.rb_child_devices);
//        rb_child_groups = (RadioButton) view.findViewById(R.id.rb_child_groups);

        vp_child_container = (ViewPager) view.findViewById(R.id.vp_child_container);
        sv_indicator = (TabStrip) view.findViewById(R.id.sv_indicator);
//        pageAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
        pageAdapter=new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };
        vp_child_container.setAdapter(pageAdapter);
        sv_indicator.setViewPager(vp_child_container);
    }

    public void initViewPager(){
        ChildDevicesFragment cdevicesf = new ChildDevicesFragment();
        ChildFriendsFragment cFriendsf = new ChildFriendsFragment();
        mFragments.add(cdevicesf);
        mFragments.add(cFriendsf);
        vp_child_container.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

//    public void setListener(){
//        rg_child_top.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                if(rb_child_friends.getId() == checkedId){
//                    ChildFriendsFragment cff = new ChildFriendsFragment();
//                    FragmentManager fm = getChildFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
//                    ft.replace(R.id.rl_child_container,cff);
//                    ft.commit();
//                }
//                if (rb_child_devices.getId() == checkedId){
//                    ChildDevicesFragment cdf = new ChildDevicesFragment();
//                    FragmentManager fm = getChildFragmentManager();
//                    FragmentTransaction ft = fm.beginTransaction();
//                    ft.replace(R.id.rl_child_container,cdf);
//                    ft.commit();
//                }
//                if(rb_child_groups.getId() == checkedId){
//                    Toast.makeText(getActivity(),"group功能正在开发中...",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }

}
