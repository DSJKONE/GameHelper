package com.runner.game;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.runner.game.databinding.ActivityMainBinding;
import com.runner.game.ui.BaseActivity;
import com.runner.game.ui.fragment.BookingSubFragment;
import com.runner.game.ui.fragment.ChatFragment;
import com.runner.game.ui.fragment.GameFragment;
import com.runner.game.ui.fragment.HomeSubFragment;
import com.runner.game.ui.fragment.NewGameSubFragment;
import com.runner.game.ui.fragment.ProfileFragment;
import com.runner.game.ui.fragment.RankingSubFragment;
import com.runner.game.ui.fragment.TradeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private List<Fragment> gameFragmentList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initGameFragmentList();
        setupBottomNavigation();
        loadInitialFragment();
    }

    private void setupBottomNavigation() {
        binding.bottomNav.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.gameFragment) {
                replaceFragment(new GameFragment(gameFragmentList));
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
        replaceFragment(new GameFragment(gameFragmentList));

    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void initGameFragmentList() {
        gameFragmentList.add(new HomeSubFragment());
        gameFragmentList.add(new NewGameSubFragment());
        gameFragmentList.add(new BookingSubFragment());
        gameFragmentList.add(new RankingSubFragment());
    }
}
