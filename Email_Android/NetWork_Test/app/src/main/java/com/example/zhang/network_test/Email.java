package com.example.zhang.network_test;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 邮件实体
 * Created by Lenovo on 2018/5/20.
 */


public class Email implements Serializable{
    private String mSender;//发件人
    private String mReceiver;//收件人

    public void setDate(String date) {
        mDate = date;
    }

    private String mDate;//邮件时间
    private String mTitle;//邮件标题
    private String mBody;//邮件正文
    private boolean isRead;
    public Email()
    {
        isRead=false;
    }

    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        mSender = sender;
    }

    public String getReceiver() {
        return mReceiver;
    }

    public void setReceiver(String receiver) {
        mReceiver = receiver;
    }

    public String getDate() {
        return mDate;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getBody() {
        return mBody;
    }

    public void setBody(String body) {
        mBody = body;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }


}
