package com.crazydudo.mobsms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initMob();
    }

    private void initMob() {
        SMSSDK.initSDK(this, "14ce7f774ef10", "04e0f08d04a22edd307247a4e947c88d");
    }

    private void initView() {
        findViewById(R.id.btn_sdkui).setOnClickListener(this);
        findViewById(R.id.btn_custom_ui).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_sdkui://使用SDK自带UI
                sdkUI();
                break;
            case R.id.btn_custom_ui://自定义UI
                startActivity(new Intent(this,CustomUI.class));

                break;
        }

    }

    private void sdkUI() {
        //打开注册页面
        RegisterPage registerPage = new RegisterPage();
        registerPage.setRegisterCallback(new EventHandler() {
            public void afterEvent(int event, int result, Object data) {
// 解析注册结果
                if (result == SMSSDK.RESULT_COMPLETE) {
                    @SuppressWarnings("unchecked")
                    HashMap<String, Object> phoneMap = (HashMap<String, Object>) data;
                    String country = (String) phoneMap.get("country");
                    String phone = (String) phoneMap.get("phone");

// 提交用户信息（此方法可以不调用）
//                    registerUser(country, phone);
                }
            }
        });
//        registerPage.show(context);
        registerPage.show(this);
    }


}
