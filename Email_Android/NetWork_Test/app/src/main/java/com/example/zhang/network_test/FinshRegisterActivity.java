package com.example.zhang.network_test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lenovo on 2018/5/23.
 */

public class FinshRegisterActivity extends AppCompatActivity{

    private User u1=new User();
    private Handler mHandler=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finishregister);
        Intent intent=getIntent();
        u1= (User) intent.getSerializableExtra("com.example.zhang.network_test.LoginActivity.user");
        final Intent intent1=new Intent(FinshRegisterActivity.this,ShouyeActivity.class);
        intent1.putExtra("com.example.zhang.network_test.LoginActivity.user",u1);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent1);
            }
        },3000);

    }
}
