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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {

    private Context mContext;
    private FragmentGameBinding binding;
    private static final String []tabTitles = {"首页", "新游", "预约", "排行"};
    private List<Fragment> fragmentList = new ArrayList<>();
    private TabLayout.OnTabSelectedListener tabSelectedListener;

    public GameFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
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
        initGameFragmentList();
        setupViewPagerWithTabs();
    }

    private void setupViewPagerWithTabs() {
        GamePagerAdapter adapter = new GamePagerAdapter(this, fragmentList);
        binding.viewpager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabLayout, binding.viewpager, (tab, position) -> {
            tab.setText(tabTitles[position]);
        }).attach();

        for (int i = 0; i < binding.tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = binding.tabLayout.getTabAt(i);
            if (tab != null) {
                if (tab.getCustomView() == null || !(tab.getCustomView() instanceof TextView)) {
                    TextView textView = new TextView(mContext);
                    textView.setText(tabTitles[i]);
                    textView.setTextAppearance(tab.isSelected() ? R.style.TabLayoutTextSelected : R.style.TabLayoutTextUnSelected);
                    tab.setCustomView(textView);
                }
            }
        }

        tabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               updateTabStyle(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
               updateTabStyle(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };

        binding.tabLayout.addOnTabSelectedListener(tabSelectedListener);
    }

    private void updateTabStyle(TabLayout.Tab tab) {
        TextView textView = (TextView) tab.getCustomView();
        textView.setTextAppearance(tab.isSelected() ? R.style.TabLayoutTextSelected : R.style.TabLayoutTextUnSelected);
    }


    private void initGameFragmentList() {
        fragmentList.add(new HomeSubFragment());
        fragmentList.add(new NewGameSubFragment());
        fragmentList.add(new BookingSubFragment());
        fragmentList.add(new RankingSubFragment());
    }

    private static class GamePagerAdapter extends FragmentStateAdapter {
        List<Fragment> fragmentList;

        public GamePagerAdapter(@NonNull Fragment fragment, List<Fragment> fragmentList) {
            super(fragment);
            this.fragmentList = fragmentList;
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
        if (binding != null) {
            binding.tabLayout.removeOnTabSelectedListener(tabSelectedListener);
            binding = null;
        }
    }
}
