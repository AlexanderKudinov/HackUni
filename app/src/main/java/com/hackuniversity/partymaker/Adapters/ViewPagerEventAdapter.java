package com.hackuniversity.partymaker.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.hackuniversity.partymaker.Fragments.MainFragment;
import com.hackuniversity.partymaker.Fragments.MenuFragment;
import com.hackuniversity.partymaker.Fragments.SearchFragment;

import java.util.ArrayList;

public class ViewPagerEventAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragments = new ArrayList<>();

    public ViewPagerEventAdapter(@NonNull FragmentManager fm, int behaviour) {
        super(fm, behaviour);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 5;
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }
}
