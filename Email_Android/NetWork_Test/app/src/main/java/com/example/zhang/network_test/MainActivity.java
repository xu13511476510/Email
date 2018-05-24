package com.example.zhang.network_test;

        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Handler;
        import android.support.v7.app.AlertDialog;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.io.IOException;
        import java.io.OutputStreamWriter;
        import java.io.PrintStream;
        import java.io.PrintWriter;
        import java.net.Socket;
        import java.text.SimpleDateFormat;
        import java.util.Date;

        import butterknife.BindView;
        import butterknife.ButterKnife;
        import butterknife.OnClick;

        import static android.R.attr.button;
        import static com.example.zhang.network_test.ShouyeActivity.RECEIVER;
        import static com.example.zhang.network_test.ShouyeActivity.SENDER;

public class MainActivity extends AppCompatActivity {

    private Socket client;
    private PrintStream ps;
    @BindView(R.id.receiver)EditText receiver;
    @BindView(R.id.subtitle)EditText subject;
    @BindView(R.id.context)EditText context;
    @BindView(R.id.sender)TextView sender;
    private String uid=new String();
    private String sid=new String();
    private Handler mHandler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        uid= (String) intent.getSerializableExtra(SENDER);
        sid= (String) intent.getSerializableExtra(RECEIVER);
        final Handler handler=new Handler();
        sender.setText(uid);
        receiver.setText(sid);
    }

    @OnClick(R.id.send_button)
    public void Onclick()
    {
        new Thread(new MyThread()).start();
    }
    @OnClick(R.id.back)
    public void OnBack(){
        finish();
    }

    class MyThread implements Runnable {
        @Override
        public void run() {
            try {
                Log.d("MainActivity", "进入方法");
                client = new Socket("112.74.176.171", 8877);
                Log.d("MainActivity", "连接成功");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// HH:mm:ss//获取当前时间
                Date date = new Date(System.currentTimeMillis());
                String str=simpleDateFormat.format(date);
                PrintWriter output = new PrintWriter(new OutputStreamWriter(client.getOutputStream(),"UTF-8"),true);
                output.println("receiver:"+receiver.getText().toString()+"sender:"+sender.getText()+"subject:"+subject.getText()+"context:"+context.getText()+"date:"+str+"flag:1");
                output.flush();
                output.close();
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("恭喜")//设置对话框的标题
                                .setMessage("邮件发送成功")//设置对话框的内容
                                //设置对话框的按钮
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                       finish();
                                    }
                                }).create();
                        dialog.show();

                    }
                });
            } catch (IOException e) {
                Log.d("MainActivity", "捕获异常");
                e.printStackTrace();
            }
        }
    }


}

