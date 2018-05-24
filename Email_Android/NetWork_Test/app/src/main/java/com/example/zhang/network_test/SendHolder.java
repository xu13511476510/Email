package com.example.zhang.network_test;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 收件箱列表数据源
 * Created by Lenovo on 2018/5/20.
 */

public class SendHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private Email mEmail;
    private Context mContext;
    @BindView(R.id.email_sender) TextView mSender;
    @BindView(R.id.email_body) TextView mBody;
    @BindView(R.id.email_date) TextView mDate;
    @BindView(R.id.email_title) TextView mTitle;
    @BindView(R.id.get)ImageView get;
    public static final String Extra_Email1="com.example.zhang.network_test.EmailHolder";
    public static final String isResend="con.example.zhang.network_test.isResend";
    public SendHolder(LayoutInflater inflater, ViewGroup parent)
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
        mSender.setText(mEmail.getReceiver()+"@pikaqiu.com");
        mBody.setText(mEmail.getBody());
        mDate.setText(  mEmail.getDate().substring(6,16));
        mTitle.setText(mEmail.getTitle());
        if(email.isRead())
        {
            get.setVisibility(View.VISIBLE);
        }
        else
        {
            get.setVisibility(View.GONE);
        }
    }

    //点击item的方法
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(mContext,EmailDetailActivity.class);
        intent.putExtra(Extra_Email1,mEmail);
        intent.putExtra(isResend,0);
        mContext.startActivity(intent);
    }
}
