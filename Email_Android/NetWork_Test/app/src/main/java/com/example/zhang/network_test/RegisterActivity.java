package com.example.zhang.network_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lenovo on 2018/5/23.
 */

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.resetpwd_edit_name)
    EditText resetpwd_edit_name;
    @BindView(R.id.resetpwd_edit_pwd_old)
    EditText resetpwd_edit_pwd_old;
    @BindView(R.id.resetpwd_edit_pwd_new)
    EditText resetpwd_edit_pwd_new;
    private String url=new String();
    private OkHttpClient mOkHttpClient=new OkHttpClient();
    private Handler mHandler=new Handler();
    private User u1=new User();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.register_btn_sure)//注册
    public void Register(){
        final String pwd= String.valueOf(resetpwd_edit_pwd_old.getText());
        String  pwd_sure= String.valueOf(resetpwd_edit_pwd_new.getText());
        final String account= String.valueOf(resetpwd_edit_name.getText());
        if(!pwd.equals(pwd_sure))  //两次输入密码不正确
        {
            AlertDialog dialog = new AlertDialog.Builder(this)
                    .setTitle("警告")//设置对话框的标题
                    .setMessage("两次输入的密码不一致")//设置对话框的内容
                    //设置对话框的按钮
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).create();
            dialog.show();
        }
        else
        {
            url="http://112.74.176.171:8080/NetWork_test/Servlet/RegisterServlet?uid="+account+"&upassword="+pwd;
            Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call=mOkHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String result=response.body().string();
                    String bijiao="asdfghjkl";
                    Log.i("返回结果",result);
                    if(result.contains(bijiao)){//验证结果
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                u1.setUid(account);
                                u1.setUpassword(pwd);
                                Intent intent=new Intent(RegisterActivity.this,FinshRegisterActivity.class);
                                intent.putExtra("com.example.zhang.network_test.LoginActivity.user",u1);
                                startActivity(intent);
                            }
                        });
                    }
                    else if(result.contains("123456")){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                AlertDialog dialog = new AlertDialog.Builder(RegisterActivity.this)
                                        .setTitle("警告")//设置对话框的标题
                                        .setMessage("账号已存在")//设置对话框的内容
                                        //设置对话框的按钮
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                            }
                                        }).create();
                                dialog.show();
                            }
                        });
                    }
                }
            });
        }

    }

    @OnClick(R.id.register_btn_cancel)
    public void Cancel(){
        finish();
    }
}
