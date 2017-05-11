package com.example.goals.stethodemohu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnNetwork;


    private static final int NETWORK = 0x01;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case NETWORK:
                    Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
    }

    private void initView() {
        btnNetwork = (Button) findViewById(R.id.btn_network);
    }

    private void initListener() {
        btnNetwork.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_network:
                getFromNetwork();
                break;
            default:
                break;
        }
    }

    private void getFromNetwork() {
        //OKhttp2.x
        /*OkHttpClient client = new OkHttpClient();
        client.networkInterceptors().add(new StethoInterceptor());*/

        //okhttp3.x
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();



        Request request = new Request.Builder().url("http://www.baidu.com").build();
        Response response = null;

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String body = response.body().string();
                Message message = handler.obtainMessage();
                message.what = NETWORK;
                message.obj = body;
                handler.sendMessage(message);
            }
        });
    }
}
