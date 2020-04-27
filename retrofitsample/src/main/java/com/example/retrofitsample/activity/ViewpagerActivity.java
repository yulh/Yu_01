package com.example.retrofitsample.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.retrofitsample.R;
import com.example.retrofitsample.fragment.HomeFragment;
import com.example.retrofitsample.fragment.MineFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewpagerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);

        ViewPager2 viewPager2 = findViewById(R.id.viewPager2);

        List<Fragment> list=new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new MineFragment());
        list.add(new HomeFragment());
        list.add(new MineFragment());


        MyAdpter adpter=new MyAdpter(this,list);
        viewPager2.setAdapter(adpter);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.ss);
        }
    }


    class MyAdpter extends FragmentStateAdapter{
        private List<Fragment> fragments;
        public MyAdpter(@NonNull FragmentActivity fragmentActivity,List<Fragment> list) {
            super(fragmentActivity);
            this.fragments=list;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments.get(position);
        }

        @Override
        public int getItemCount() {
            return fragments.size();
        }
    }
}
