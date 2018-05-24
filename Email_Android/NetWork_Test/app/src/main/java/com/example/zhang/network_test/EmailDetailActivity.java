package com.example.zhang.network_test;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.zhang.network_test.SendHolder.isResend;
import static com.example.zhang.network_test.ShouyeActivity.RECEIVER;
import static com.example.zhang.network_test.ShouyeActivity.SENDER;

/**
 * 收件箱邮件详情
 * Created by Lenovo on 2018/5/21.
 */

public class EmailDetailActivity extends AppCompatActivity {
    @BindView(R.id.email_body)TextView mBody;
    @BindView(R.id.email_title)TextView mTitle;
    @BindView(R.id.email_sender)TextView mSender;
    @BindView(R.id.btn_resend)Button btn_resend;
    @BindView(R.id.btn_delete)Button btn_delete;
    private Email mEmail;
    private String url=new String();
    private OkHttpClient client=new OkHttpClient();
    private Handler mHandler=new Handler() ;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_detail);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        mEmail= (Email) intent.getSerializableExtra("com.example.zhang.network_test.EmailHolder");
        int isresend= (int) intent.getSerializableExtra(isResend);
        if(isresend==0)
        {
            btn_resend.setVisibility(View.GONE);
        }
        else if(isresend==1)
        {
            btn_resend.setVisibility(View.VISIBLE);
        }
        else
        {
            btn_delete.setVisibility(View.GONE);
        }
        mBody.setText(mEmail.getBody());
        mTitle.setText(mEmail.getTitle());
        mSender.setText(mEmail.getSender()+"@pikaqiu.com");
    }

    @OnClick(R.id.btn_delete)
    public void OnDelete()
    {
        Log.i("日期",mEmail.getDate());
        url="http://112.74.176.171:8080/NetWork_test/Servlet/DeleteEmailServlet?sender="+mEmail.getSender()+"&receiver="+mEmail.getReceiver()+"&date="+mEmail.getDate();

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("警告")//设置对话框的标题
                .setMessage("是否确认删除该邮件")//设置对话框的内容
                //设置对话框的按钮
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
                    }
                }).create();
        dialog.show();
    }
    private void delete()//删除一封邮件
    {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        OnClickback();
                    }
                });
            }
        });
    }
    @OnClick(R.id.btn_resend)
    public void OnResend(){
        Intent intent=new Intent(EmailDetailActivity.this,MainActivity.class);
        intent.putExtra(SENDER,mEmail.getReceiver());
        intent.putExtra(RECEIVER,mEmail.getSender());
        startActivity(intent);
    }

    @OnClick(R.id.back)
    public void OnClickback()//返回按钮退回上一层
    {
        finish();
    }
}
