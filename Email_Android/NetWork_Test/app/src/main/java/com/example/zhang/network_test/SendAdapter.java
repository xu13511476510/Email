package com.example.zhang.network_test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 收件箱列表适配器
 * Created by Lenovo on 2018/5/20.
 */

public class SendAdapter extends RecyclerView.Adapter<SendHolder> {
    private ArrayList<Email>mEmails=new ArrayList<>();
    private Context mContext;

    public SendAdapter(ArrayList<Email> emails, Context context)
    {
        mEmails=emails;
        mContext=context;
    }
    @Override
    public SendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        return new SendHolder(layoutInflater,parent);
    }

    @Override
    public void onBindViewHolder(SendHolder holder, int position) {
        Email email=mEmails.get(position);
        holder.bind(email,mContext);
    }

    @Override
    public int getItemCount() {
        return mEmails.size();
    }
}
