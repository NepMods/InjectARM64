package com.reveny.virtualinject.ui.activity;

import android.os.Bundle;

import com.reveny.virtualinject.R;
import com.reveny.virtualinject.databinding.ActivityMainBinding;
import com.reveny.virtualinject.ui.fragment.HomeFragment;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();
    }

}