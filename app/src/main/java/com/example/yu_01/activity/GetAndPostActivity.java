package com.example.yu_01.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yu_01.R;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class GetAndPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);

        getAndPostRequest();
    }

    private void getAndPostRequest() {
        RequestParams params = new RequestParams("https://www.baidu.com/");
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.e("yulh-onSuccess", result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Log.e("yulh-onError",ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Log.e("yulh-onError",cex.getMessage());
            }

            @Override
            public void onFinished() {
                Log.e("yulh-onFinished","onFinished");
            }
        });
    }
}
