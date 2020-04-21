package com.example.yu_01.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yu_01.R;

public class CopyActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy);

        ClipboardManager cm= (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
    }

    @Override
    public void onClick(View v) {

    }
}
