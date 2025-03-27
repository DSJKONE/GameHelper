package com.runner.game;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.runner.game.databinding.ActivityMainBinding;
import com.runner.game.ui.BaseActivity;
import com.runner.game.ui.fragment.ChatFragment;
import com.runner.game.ui.fragment.GameFragment;
import com.runner.game.ui.fragment.ProfileFragment;
import com.runner.game.ui.fragment.TradeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        setContentView(binding.getRoot());

        setupBottomNavigation();
        loadInitialFragment();
    }

    private void setupBottomNavigation() {
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.gameFragment) {
                replaceFragment(new GameFragment());
            } else if (itemId == R.id.chatFragment) {
                replaceFragment(new ChatFragment());
            } else if (itemId == R.id.tradeFragment) {
                replaceFragment(new TradeFragment());
            } else if (itemId == R.id.profileFragment) {
                replaceFragment(new ProfileFragment());
            }
            return true;
        });
    }

    private void loadInitialFragment() {
        replaceFragment(new GameFragment());

    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (binding != null) {
            binding = null;
        }
    }
}
