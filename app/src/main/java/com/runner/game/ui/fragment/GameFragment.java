package com.runner.game.ui.fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.runner.game.R;
import com.runner.game.databinding.FragmentGameBinding;

import java.util.List;

public class GameFragment extends Fragment {

    private Context mContext;
    private FragmentGameBinding binding;
    private static final String []tabTitles = {"首页", "新游", "预约", "排行"};
    private static List<Fragment> fragmentList;


    public GameFragment(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewPagerWithTabs();
    }

    private void setupViewPagerWithTabs() {
        GamePagerAdapter adapter = new GamePagerAdapter(this);
        binding.viewpager.setAdapter(adapter);

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabStyle(tab,R.style.TabLayoutTextSelected);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabStyle(tab,R.style.TabLayoutTextUnSelected);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        new TabLayoutMediator(binding.tabLayout, binding.viewpager, (tab, position) -> {
            tab.setText(tabTitles[position]);
        }).attach();



    }

    private void updateTabStyle(TabLayout.Tab tab, @StyleRes int styleRes) {
        if (tab.getCustomView() == null) {
            tab.setCustomView(R.layout.tab_item);
        }
        TextView textView = (TextView) tab.getCustomView().findViewById(R.id.tab_text);
        if (textView != null) {
            textView.setTextAppearance(mContext,styleRes);
        }
    }

    private static class GamePagerAdapter extends FragmentStateAdapter {
        public GamePagerAdapter(@NonNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return tabTitles.length;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
