package com.hackuniversity.partymaker.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.hackuniversity.partymaker.Fragments.MainFragment;
import com.hackuniversity.partymaker.Fragments.MenuFragment;
import com.hackuniversity.partymaker.Fragments.SearchFragment;

public class ViewPagerAdapter extends androidx.fragment.app.FragmentPagerAdapter  {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behaviour) {
        super(fm, behaviour);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new SearchFragment();
                break;
            case 1:
                fragment = new MainFragment();
                break;
            case 2:
                fragment = new MenuFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
