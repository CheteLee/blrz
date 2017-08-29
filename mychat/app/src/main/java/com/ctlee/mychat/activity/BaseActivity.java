package com.ctlee.mychat.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ctlee.mychat.R;

/**
 * Created by ctLee on 2017/8/4.
 */

public class BaseActivity extends Activity implements View.OnClickListener {
    private Button bt_back;
    private Button bt_menu;
    private TextView tv_title;
    private FrameLayout mContentLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViews();   //加载 activity_title 布局 ，并获取标题及两侧按钮
    }


    private void findViews(){
        super.setContentView(R.layout.activity_title);//调用父类的方法加载布局
        bt_back = (Button) findViewById(R.id.bt_back);
        bt_menu = (Button) findViewById(R.id.bt_menu);
        tv_title = (TextView) findViewById(R.id.tv_title);
        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
        bt_back.setOnClickListener(this);
        bt_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bt_back:
                setBt_back();
                break;
            case R.id.bt_menu:
                setBt_menu();
                break;
            case R.id.tv_title:
                setTv_title();
                break;
            default:
                break;
        }
    }
    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        tv_title.setText(titleId);
    }

    /**
     * 设置标题内容
     * @param title
     */
    @Override
    public void setTitle(CharSequence title) {
        tv_title.setText(title);
//        tv_title.setTextSize(24);
    }

    @Override
    public void setTitleColor(int textColor) {
//        textColor = 0xff3423;
        tv_title.setTextColor(textColor);
    }

    public void setTitleColorStr(String textColor) {

//        tv_title.setTextColor(android.graphics.Color.parseColor("#ff3323"));
        tv_title.setTextColor(android.graphics.Color.parseColor(textColor));
    }

    //取出FrameLayout并调用父类removeAllViews()方法
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }
    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }

    /**
     * 设置是否显示返回按钮
     */
    protected void showBackView(int btBackId,boolean showView){
        if(null != bt_back){
            if(showView){
                bt_back.setText(btBackId);
                bt_back.setVisibility(View.VISIBLE);
            }else{
                bt_back.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 设置是否显示菜单按钮
     */
    protected void showMenuView(int menuId,boolean showView){
        if(null != bt_menu){
            if(showView){
                bt_menu.setText(menuId);
                bt_menu.setVisibility(View.VISIBLE);
            }else{
                bt_menu.setVisibility(View.INVISIBLE);
            }
        }
    }
    public void setBt_back(){
        Toast.makeText(this,"click back",Toast.LENGTH_SHORT).show();
    }

    public void setBt_menu(){
        Toast.makeText(this,"click menu",Toast.LENGTH_SHORT).show();
    }

    public void setTv_title(){
        Toast.makeText(this,"click title",Toast.LENGTH_SHORT).show();
    }
}
