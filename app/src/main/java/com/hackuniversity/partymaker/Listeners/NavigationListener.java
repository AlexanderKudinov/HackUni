package com.hackuniversity.partymaker.Listeners;

import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.hackuniversity.partymaker.Fragments.MainFragment;
import com.hackuniversity.partymaker.Fragments.MenuFragment;
import com.hackuniversity.partymaker.Fragments.SearchFragment;
import com.hackuniversity.partymaker.R;

public class NavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private ViewPager viewPager;
    private final static String MENU_FRAGMENT = "MENU_FRAGMENT", MAIN_FRAGMENT = "MENU_FRAGMENT", SEARCH_FRAGMENT = "MENU_FRAGMENT";

    public NavigationListener(ViewPager viewPager) {
        this.fragmentManager = fragmentManager;
        this.viewPager = viewPager;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("navigation", "selected " + item.getItemId());
        switch (item.getItemId()) {
            case R.id.menu_search:
                viewPager.setCurrentItem(0);
                return true;
            case R.id.menu_events:
                viewPager.setCurrentItem(1);
                return true;
            case R.id.menu_menu:
                viewPager.setCurrentItem(2);
                return true;
        }

        return false;
    }

    public void loadFragment(int menuId) {
        switch (menuId) {
            case R.id.menu_search:
                if (fragmentManager.findFragmentByTag(MAIN_FRAGMENT) != null)
                    fragmentManager.popBackStack(0, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                else {
                    fragmentManager.beginTransaction()
                            .add(new MenuFragment(), MAIN_FRAGMENT)
                            .commitAllowingStateLoss();
                }
                break;
            case R.id.menu_events:

                break;
            case R.id.menu_menu:

                break;
        }

    }
}
