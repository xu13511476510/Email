package model;

import java.util.Date;

public class Email {
	 private String mSender;//发件人
	    private String mReceiver;//收件人
	    private String mDate;//邮件时间
	    private String mTitle;//邮件标题
	    private String mBody;//邮件正文
	    private boolean isRead;//已读未读

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

	    public void setDate(String date){
	    	mDate=date;
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
