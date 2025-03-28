package com.runner.game.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.tabs.TabLayout;
import com.runner.game.R;
import com.runner.game.databinding.FragmentHomeGameBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeSubFragment extends Fragment {

    private Context mContext;
    private FragmentHomeGameBinding binding;
    private String [] gameTypeLists = {"推荐", "免费款", "动漫", "三国", "开宝箱", "二次元"};
    private List<Fragment> fragmentList;
    private TabLayout.OnTabSelectedListener onTabSelectedListener;
    private FragmentManager fragmentManager;


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
        binding = FragmentHomeGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initFragmentLists();
        initTabs();
        setupFragmentsWithTabs();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binding != null) {
            binding.gameTagLayout.removeOnTabSelectedListener(onTabSelectedListener);
            binding = null;
        }
    }

    private void initTabs() {
        binding.gameTagLayout.removeAllTabs();
        for (int i = 0; i < gameTypeLists.length; i++) {
            TabLayout.Tab tab = binding.gameTagLayout.newTab();
            TextView textView = new TextView(mContext);
            textView.setText(gameTypeLists[i]);
            textView.setSingleLine(true);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            textView.setTextColor(i == 0 ? Color.WHITE : Color.parseColor("#666666"));

            int paddingH = dpToPx(8);
            int paddingV = dpToPx(4);
            textView.setPadding(paddingH, paddingV, paddingH, paddingV);
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(i == 0 ?
                    R.drawable.bg_tab_game_type_selescted_rounded :
                    R.drawable.bg_tab_game_type_rounded);

            tab.setCustomView(textView);
            binding.gameTagLayout.addTab(tab);
        }

        fragmentManager = getChildFragmentManager();
        replaceFragment(fragmentList.get(0));
    }

    private void setupFragmentsWithTabs() {
        onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                updateTabStyle(tab);
                replaceFragment(fragmentList.get(tab.getPosition()));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                updateTabStyle(tab);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };

        binding.gameTagLayout.addOnTabSelectedListener(onTabSelectedListener);
    }

    private void updateTabStyle(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        if (customView instanceof TextView) {
            TextView textView = (TextView) customView;
            textView.setBackgroundResource(tab.isSelected() ?
                    R.drawable.bg_tab_game_type_selescted_rounded :
                    R.drawable.bg_tab_game_type_rounded);
            textView.setTextColor(tab.isSelected() ? Color.WHITE : Color.parseColor("#666666"));
        }
    }

    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics()
        );
    }

    private void initFragmentLists() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new RecommendFragment());
        fragmentList.add(new FreeGameFragment());
        fragmentList.add(new AnimeFragment());
        fragmentList.add(new ThreeCountiesFragment());
        fragmentList.add(new BoxGameFragment());
        fragmentList.add(new TwoDFragment());
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager.beginTransaction().replace(R.id.game_container, fragment).commit();
    }

}
