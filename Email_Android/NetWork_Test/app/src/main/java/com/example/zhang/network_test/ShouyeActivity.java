package com.example.zhang.network_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2018/5/22.
 */

public class ShouyeActivity extends AppCompatActivity{
    @BindView(R.id.text_title)TextView text_title;
    private User u1=new User();
    public static final String SENDER="com.example.zhang.network_test.sender";
    public static final String RECEIVER="com.example.zhang.network_test.receiver";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shouye);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        u1= (User) intent.getSerializableExtra("com.example.zhang.network_test.LoginActivity.user");
        text_title.setText(u1.getUid()+"@pikaqiu.com");
    }

    @OnClick(R.id.back)
    public void OnClickback()//返回按钮退回上一层
    {
        finish();
    }

    @OnClick(R.id.receive_email)
    public void Receive(){ //点击收件箱，进入该页面
        Intent intent =new Intent(ShouyeActivity.this,ReceiveActivity.class);
        intent.putExtra("com.example.zhang.network_test.LoginActivity.user",u1);
        startActivity(intent);
    }

    @OnClick(R.id.send_email)
    public void Send(){//点击进入已发送页面
        Intent intent =new Intent(ShouyeActivity.this,SendActivity.class);
        intent.putExtra("com.example.zhang.network_test.LoginActivity.user",u1);
        startActivity(intent);
    }

    @OnClick(R.id.laji_email)
    public void Laji(){ //点击进入垃圾箱页面
        Intent intent =new Intent(ShouyeActivity.this,LajiActivity.class);
        intent.putExtra("com.example.zhang.network_test.LoginActivity.user",u1);
        startActivity(intent);
    }

    @OnClick(R.id.add_email)
    public void AddEmail(){//点击进入写邮件页面
        Intent intent=new Intent(ShouyeActivity.this,MainActivity.class);
        intent.putExtra(SENDER,u1.getUid());
        intent.putExtra(RECEIVER,"");
        startActivity(intent);
    }
}
