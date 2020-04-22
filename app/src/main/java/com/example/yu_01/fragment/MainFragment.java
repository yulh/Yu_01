package com.example.yu_01.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yu_01.R;
import com.example.yu_01.adapter.MainFragmentRecyclerAdapter;
import com.example.yu_01.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {
    private FragmentMainBinding binding;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);


        View view = binding.getRoot();
        initView(view);
        return view;
    }

    private void initView(View view) {
        //设置固定大小
        binding.recyclerView.setHasFixedSize(true);
        //创建线性布局
        linearLayoutManager = new LinearLayoutManager(getActivity());
        //垂直方向
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        //设置布局管理器
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        List<String> mList = new ArrayList<String>();
        mList.clear();
        for (int i = 0; i < 10; i++) {
            mList.add("清风" + i);
        }

        MainFragmentRecyclerAdapter adapter = new MainFragmentRecyclerAdapter(getActivity(), mList);
        binding.recyclerView.setAdapter(adapter);
    }
}
