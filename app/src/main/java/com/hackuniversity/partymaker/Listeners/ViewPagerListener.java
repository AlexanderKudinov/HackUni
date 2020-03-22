package com.hackuniversity.partymaker.Listeners;

import android.view.MenuItem;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class ViewPagerListener implements ViewPager.OnPageChangeListener {

    private BottomNavigationView bottomNavigationView;
    private MenuItem prevMenuItem;

    public ViewPagerListener(BottomNavigationView bottomNavigationView) {
        this.bottomNavigationView = bottomNavigationView;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null)
            prevMenuItem.setChecked(false);
        else
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = bottomNavigationView.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
