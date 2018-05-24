package com.example.zhang.network_test;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.zhang.network_test.SendHolder.isResend;

/**
 * 收件箱列表数据源
 * Created by Lenovo on 2018/5/20.
 */

public class EmailHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Email mEmail;
    private Context mContext;
    private String url=new String();
    @BindView(R.id.email_sender) TextView mSender;
    @BindView(R.id.email_body) TextView mBody;
    @BindView(R.id.email_date) TextView mDate;
    @BindView(R.id.email_title) TextView mTitle;
    @BindView(R.id.get)ImageView get;
    public static final String Extra_Email1="com.example.zhang.network_test.EmailHolder";
    private OkHttpClient client=new OkHttpClient();
    private Handler mHandler=new Handler() ;

    public EmailHolder(LayoutInflater inflater, ViewGroup parent)
    {
        super(inflater.inflate(R.layout.list_item,parent,false));
        ButterKnife.bind(this,itemView);
        itemView.setOnClickListener(this);
    }

    //数据绑定
    public void bind(Email email,Context context)
    {
        mEmail=email;
        mContext=context;
        mSender.setText(mEmail.getSender()+"@pikaqiu.com");
        mBody.setText(mEmail.getBody());
        mDate.setText(  mEmail.getDate().substring(6,16));
        mTitle.setText(mEmail.getTitle());
        if(email.isRead())
        {
            get.setVisibility(View.VISIBLE);
        }
    }

    //点击item的方法
    @Override
    public void onClick(View v) {
        url="http://112.74.176.171:8080/NetWork_test/Servlet/ReadEmailServlet?sender="+mEmail.getSender()+"&receiver="+mEmail.getReceiver()+"&date="+mEmail.getDate();
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
            }
        });

        Intent intent=new Intent(mContext,EmailDetailActivity.class);
        intent.putExtra(Extra_Email1,mEmail);
        intent.putExtra(isResend,1);
        mContext.startActivity(intent);
    }
}
