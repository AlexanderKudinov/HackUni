package com.hackuniversity.partymaker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

import com.hackuniversity.partymaker.Adapters.ViewPagerEventAdapter;
import com.hackuniversity.partymaker.Fragments.AllPeopleFragment;
import com.hackuniversity.partymaker.Fragments.AnimatorPeopleFragment;
import com.hackuniversity.partymaker.Fragments.CookerPeopleFragment;
import com.hackuniversity.partymaker.Fragments.DancerPeopleFragment;
import com.hackuniversity.partymaker.Fragments.DjPeopleFragment;
import com.hackuniversity.partymaker.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerEventAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        ButterKnife.bind(this);

        adapter = new ViewPagerEventAdapter(getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new AllPeopleFragment());
        adapter.addFragment(new DjPeopleFragment());
        adapter.addFragment(new AnimatorPeopleFragment());
        adapter.addFragment(new CookerPeopleFragment());
        adapter.addFragment(new DancerPeopleFragment());
        viewPager = new ViewPager(this);
        viewPager.setAdapter(adapter);
    }

    @OnClick(R.id.btnCreateEvent)
    public void onClickCreateEvent() {

    }


    @OnClick(R.id.btnCancelCreatingEvent)
    public void onClickCancelCreating() {
        finish();
    }
}
