package com.ctlee.mychat.activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ctlee.mychat.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by ctLee on 2017/8/9.
 */

public class MainActivity extends FragmentActivity {

    private SlidingMenu menu;

    // id用于区分左边还是右边的侧边滑menu
    private final static String ID = "id";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_main);

    menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
    // 左边和右边均有
//        menu.setMode(SlidingMenu.LEFT_RIGHT);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
//设置滑动模式
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//全屏滑动

        menu.setBehindOffset(R.dimen.behind_width);

    // 左边
        menu.setMenu(R.layout.left_menu);

    // 右边
        menu.setSecondaryMenu(R.layout.right_menu);

    Fragment leftFragment = TestFragment.newInstance("左边");
    Fragment rightFragment = TestFragment.newInstance("右边");

    FragmentManager fm = getSupportFragmentManager();

    FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.left, leftFragment);
        ft.commit();

    // 必须重新再次获得一个FragmentTransaction。否则报错。
    ft = fm.beginTransaction();
        ft.replace(R.id.right, rightFragment);
        ft.commit();
}

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 按下BACK + 没有重复
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 用户按返回键后，切换SlideMenu <-->主界面。
            menu.toggle(true);

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

//
// 仅仅用于生成测试的Fragment。
//
public static class TestFragment extends Fragment {

    public static Fragment newInstance(String id) {
        Fragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ID, id);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // 仅仅显示一个TextView。
        TextView tv = new TextView(getActivity());
        tv.setTextColor(Color.BLACK);
        tv.setText(this.getArguments().getString(ID) + "");
        tv.setTextSize(60.0f);
        tv.setGravity(Gravity.CENTER);

        return tv;
    }
}
}
