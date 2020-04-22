package com.example.yu_01.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.yu_01.R;
import com.example.yu_01.bean.LoginBean;
import com.example.yu_01.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLoginBean(new LoginBean("余话","111111"));
        binding.setLoginActivity(this);
    }


    public void onClick(View v){
        Toast.makeText(this,"111",Toast.LENGTH_SHORT).show();
    }
}
