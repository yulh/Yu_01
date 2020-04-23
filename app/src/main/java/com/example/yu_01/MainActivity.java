package com.example.yu_01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yu_01.activity.DbActivity;
import com.example.yu_01.activity.GetAndPostActivity;
import com.example.yu_01.activity.LoginActivity;
import com.example.yu_01.databinding.ActivityMainBinding;
import com.example.yu_01.fragment.MainFragment;
import com.example.yu_01.fragment.MineFragment;

import org.xutils.common.Callback;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragments;
    private int lastIndex;
    private int state;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.setMainActivity(this);
        initBottomBar();


        fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new MineFragment());

        setFragmentPosition(state);

        loadImage();
    }

    /**
     * 初始化底部选择栏
     */
    private void initBottomBar() {
        binding.bottomBar.setFirstChecked(state);
        binding.bottomBar.setContainer(R.id.main_frameLayout)
                .setTitleBeforeAndAfterColor("#CCCCCC", "#FFC107")
                .addItem(MainFragment.class,
                        "首页",
                        R.mipmap.home0,
                        R.mipmap.home)
                .addItem(MineFragment.class,
                        "订单",
                        R.mipmap.hq0,
                        R.mipmap.hq)
                .build();
    }

    /**
     * 展示的顶部Fragment
     * @param position
     */
    private void setFragmentPosition(int position) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragments.get(position);
        Fragment lastFragment = fragments.get(lastIndex);
        lastIndex = position;
        ft.replace(R.id.main_frameLayout,currentFragment);
//        if (!currentFragment.isAdded()) {
//            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
//            ft.add(R.id.main_frameLayout, currentFragment);
//        }
//        ft.show(currentFragment);
//        ft.commitAllowingStateLoss();
    }

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
            case R.id.button_03:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    /**
     * 加载图片
     */
    private void loadImage(){
        final ImageOptions imageOptions = new ImageOptions.Builder()
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                .setCircular(true)
                .build();

        x.image().loadFile("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1587368343&di=7a02e26fc3d5f40438a55c3c1fb8db97&src=http://bbs.jooyoo.net/attachment/Mon_0905/24_65548_2835f8eaa933ff6.jpg", imageOptions, new Callback.CacheCallback<File>() {
            @Override
            public boolean onCache(File result) {
                Log.e("yulh-onCache", "缓冲成功" + result.getName() + "路径为" + result.getPath());
                x.image().bind(binding.imageView01, result.getPath(), imageOptions);
                return true;
            }

            @Override
            public void onSuccess(File result) {
                Log.e("yulh-onSuccess", "缓冲成功" + result.getName() + "路径为" + result.getPath());
                x.image().bind(binding.imageView01, result.getPath(), imageOptions);
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
}







