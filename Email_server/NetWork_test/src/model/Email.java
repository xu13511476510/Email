package model;

import java.util.Date;

public class Email {
	 private String mSender;//������
	    private String mReceiver;//�ռ���
	    private String mDate;//�ʼ�ʱ��
	    private String mTitle;//�ʼ�����
	    private String mBody;//�ʼ�����
	    private boolean isRead;//�Ѷ�δ��

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
