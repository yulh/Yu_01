package com.example.yu_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.yu_01.activity.DbActivity;
import com.example.yu_01.activity.GetAndPostActivity;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageView imageView_01;
    public Button buttonDb;
    public Button buttonGetAndPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView_01 = (ImageView) findViewById(R.id.imageView_01);
        buttonDb = (Button) findViewById(R.id.button_01);
        buttonDb.setOnClickListener(this);
        buttonGetAndPost = (Button) findViewById(R.id.button_02);
        buttonGetAndPost.setOnClickListener(this);

        final ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(true)
                .build();

        x.image().loadFile("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1587368343&di=7a02e26fc3d5f40438a55c3c1fb8db97&src=http://bbs.jooyoo.net/attachment/Mon_0905/24_65548_2835f8eaa933ff6.jpg", imageOptions, new Callback.CacheCallback<File>() {
            @Override
            public boolean onCache(File result) {
                Log.e("yulh-onCache", "缓冲成功" + result.getName() + "路径为" + result.getPath());
                x.image().bind(imageView_01, result.getPath(), imageOptions);
                return true;
            }

            @Override
            public void onSuccess(File result) {
                Log.e("yulh-onSuccess", "缓冲成功" + result.getName() + "路径为" + result.getPath());
                x.image().bind(imageView_01, result.getPath(), imageOptions);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.button_01:
                intent = new Intent(MainActivity.this, DbActivity.class);
                startActivity(intent);
                break;
            case R.id.button_02:
                intent = new Intent(MainActivity.this, GetAndPostActivity.class);
                startActivity(intent);
                break;
        }
    }
}
