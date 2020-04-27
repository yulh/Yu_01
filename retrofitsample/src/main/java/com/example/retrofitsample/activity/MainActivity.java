package com.example.retrofitsample.activity;

import android.os.Bundle;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.retrofitsample.R;
import com.example.retrofitsample.databinding.ActivityMainBinding;
import com.example.retrofitsample.fragment.HomeFragment;
import com.example.retrofitsample.fragment.MineFragment;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private ActivityMainBinding binding;
    private Fragment fg1, fg2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setMainActivity(this);


        binding.bottomRadioGroup.setOnCheckedChangeListener(this);
        binding.homeRadioButton.setChecked(true);
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (fg1 != null) {
            transaction.hide(fg1);
        }
        if (fg2 != null) {
            transaction.hide(fg2);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);

        switch (checkedId) {
            case R.id.home_radio_button:
                if (fg1 == null) {
                    fg1 = new HomeFragment();
                    transaction.add(R.id.fragmeLayout, fg1);
                } else {
                    transaction.show(fg1);
                }
                break;
            case R.id.mine_radio_button:
                if (fg2 == null) {
                    fg2 = new MineFragment();
                    transaction.add(R.id.fragmeLayout, fg2);
                } else {
                    transaction.show(fg2);
                }
                break;
        }

        transaction.commit();
    }
}
