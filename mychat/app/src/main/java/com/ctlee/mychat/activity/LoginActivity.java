package com.ctlee.mychat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ctlee.mychat.R;

/**
 * Created by ctLee on 2017/8/3.
 */

public class LoginActivity extends BaseActivity {
    private Handler handler = new Handler();

    private Button bt_login;
    private EditText et_user;
    private EditText et_password;
    private TextView tv_forgetpwd;
    private TextView tv_fresh_regist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
        setTitleColorStr("#ffffff");
        showBackView(R.string.title_bt_back,false);
        showMenuView(R.string.title_bt_menu,false);
        findViews();
    }

    @Override
    public void setBt_back() {//重写父类返回键
//        super.setBt_back();
        Toast.makeText(LoginActivity.this,"hi",Toast.LENGTH_SHORT).show();
    }

    public void findViews(){
        bt_login = (Button) findViewById(R.id.bt_login);
        et_user = (EditText) findViewById(R.id.et_user);
        et_password = (EditText) findViewById(R.id.et_password);
        tv_forgetpwd = (TextView) findViewById(R.id.tv_forgetpwd);
        tv_fresh_regist = (TextView) findViewById(R.id.tv_fresh_regist);

        bt_login.setOnClickListener(this);
        tv_forgetpwd.setOnClickListener(this);
        tv_fresh_regist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch(v.getId()){
            case R.id.bt_login:
                String user = et_user.getText().toString().replace(" ","").trim();
                String pwd = et_password.getText().toString().replace(" ","").trim();
                if(user.isEmpty()){
                    Toast.makeText(LoginActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pwd.isEmpty()){
                    Toast.makeText(LoginActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                    Toast.makeText(LoginActivity.this,"正在登录……",Toast.LENGTH_SHORT).show();
                    handler.postDelayed(new Runnable() {//2s后跳转到MainActivity
                        @Override
                        public void run() {
                            Intent intent = new Intent();
                            intent.setClass(LoginActivity.this,DrawerLayoutMainActivity.class);
                            startActivity(intent);
                            LoginActivity.this.finish();
                        }
                    },2000);
                break;
            case R.id.tv_forgetpwd:
                Toast.makeText(LoginActivity.this,"(忘记密码)该功能正在开发中……",Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_fresh_regist:
                Toast.makeText(LoginActivity.this,"新用户注册功能还在开发中……",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;

        }
    }
}
