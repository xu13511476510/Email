package com.example.zhang.network_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lenovo on 2018/5/22.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_edit_account)EditText login_edit_account;
    @BindView(R.id.login_edit_pwd)EditText login_edit_pwd;
    @BindView(R.id.login_text_change_pwd)TextView login_text_change_pwd;
    @BindView(R.id.login_btn_login)Button login_btn_login;
    @BindView(R.id.Login_Remember)CheckBox Login_Remember;
    private String url=new String();
    private Handler mHandler=new Handler();
    private OkHttpClient mOkHttpClient=new OkHttpClient();
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember=pref.getBoolean("remember_password",false);
        if(isRemember) {
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            login_edit_account.setText(account);
            login_edit_pwd.setText(password);
            Login_Remember.setChecked(true);

        }
    }

    @OnClick(R.id.login_btn_login) //登陆按钮点击事件
    public void Login(){
        final String uid= String.valueOf(login_edit_account.getText());
        final String upassword= String.valueOf(login_edit_pwd.getText());
        final User u1=new User();
        u1.setUid(uid);
        u1.setUpassword(upassword);
        url="http://112.74.176.171:8080/NetWork_test/Servlet/LoginServlet?uid="+uid+"&upassword="+upassword;
        final Request request = new Request.Builder()
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
                    String bijiao="qwertyuiop";
                    Log.i("返回结果",result);
                    if(result.contains(bijiao)){//登陆验证结果
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {//登陆成功，记住密码
                                editor=pref.edit();
                                editor=pref.edit();
                                if(Login_Remember.isChecked()){
                                    editor.putBoolean("remember_password",true);
                                    editor.putString("account",uid);
                                    editor.putString("password",upassword);
                                }else {
                                    editor.clear();
                                }
                                editor.apply();
                                Intent intent=new Intent(LoginActivity.this,ShouyeActivity.class);
                                intent.putExtra("com.example.zhang.network_test.LoginActivity.user",u1);
                                startActivity(intent);
                            }
                        });
                    }
                    else{
                            Log.i("账号或密码错误",bijiao);
                        login_btn_login.setText("登陆失败");
                    }
            }
        });
    }

    @OnClick(R.id.login_btn_register)//注册按钮点击事件
    public void Register(){
        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.login_text_change_pwd)//修改密码
    public void Changepwd(){
        Intent intent=new Intent(LoginActivity.this,ResetpwdActivity.class);
        startActivity(intent);
    }
}
