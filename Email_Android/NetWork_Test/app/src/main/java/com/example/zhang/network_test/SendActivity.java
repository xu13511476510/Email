package com.example.zhang.network_test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lenovo on 2018/5/20.
 */

public class SendActivity extends AppCompatActivity {
    private Handler mHandler =new Handler(); //当前线程处理消息更新列表
    private OkHttpClient client=new OkHttpClient();
    private String url =new String();
    private ArrayList<Email>mEmails =new ArrayList<>();//数据源
    private ArrayList<Email>emails =new ArrayList<>();
    @BindView(R.id.email_list) RecyclerView mRecyclerView;  //邮件列表
    @BindView(R.id.recycle_swip)SwipeRefreshLayout mSwipeRefreshLayout;//刷新
    @BindView(R.id.text_title)TextView text_title;
    private boolean isRefresh=false;
    private SendAdapter Adapter;
    private SwipeRefreshLayout.OnRefreshListener listener;
    private User u1=new User();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_list);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        u1= (User) intent.getSerializableExtra("com.example.zhang.network_test.LoginActivity.user");
        url="http://112.74.176.171:8080/NetWork_test/Servlet/SendServlet?uid="+u1.getUid();
        text_title.setText("已发送");
        mEmails = new ArrayList<>();
        Adapter = new SendAdapter(mEmails, this);
        mRecyclerView.setAdapter(Adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listener = new SwipeRefreshLayout.OnRefreshListener() { //刷新
            @Override
            public void onRefresh() {
                if (!isRefresh) {
                    isRefresh = true;
                    updateUI();
                }
            }
        };
        mSwipeRefreshLayout.setOnRefreshListener(listener);
        mSwipeRefreshLayout.post(new Runnable(){  //进入页面即刷新
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        listener.onRefresh();
    }

    //从邮件详情退回时刷新列表
    @Override
    protected void onRestart() {
        super.onRestart();
        mSwipeRefreshLayout.post(new Runnable(){  //进入页面即刷新
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        listener.onRefresh();
    }

    private void updateUI()
    {
        Log.i("进入update","qweqweqweqweqwe");
        init();
        Log.i("init完成","qweqweqweqweqweqwe");
    }
    private void init(  )//数据更新 在这里调用网络方法
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
                String json=response.body().string();
                Log.i("进入okhttp",json);
                Gson gson=new Gson();
                Type type=new TypeToken<ArrayList<Email>>(){}.getType();
                emails=gson.fromJson(json,type);//解析完毕
                mHandler.post(new Runnable() {//句柄在主线程中更新UI
                    @Override
                    public void run() {
                        mEmails.clear();
                        for(Email e:emails)
                        {
                            mEmails.add(e);
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                        Adapter.notifyDataSetChanged();
                        isRefresh = false;
                    }
                });
            }
        });
    }

    @OnClick(R.id.back)
    public void OnClickback(View view)//返回按钮退回上一层
    {
        finish();
    }

}
