package com.hackuniversity.partymaker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hackuniversity.partymaker.Adapters.ViewPagerAdapter;
import com.hackuniversity.partymaker.Listeners.NavigationListener;
import com.hackuniversity.partymaker.Listeners.ViewPagerListener;
import com.hackuniversity.partymaker.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private NavigationListener navigationListener;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setTabs();
    }

    public void findViews() {
        viewPager = findViewById(R.id.viewPager);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    public void setTabs() {
        navigationListener = new NavigationListener(viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPagerListener(bottomNavigationView));
        viewPager.setOffscreenPageLimit(3);


        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);
        bottomNavigationView.setSelectedItemId(R.id.menu_events);
    }
}
