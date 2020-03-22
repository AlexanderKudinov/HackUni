package com.hackuniversity.partymaker.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hackuniversity.partymaker.R;

import info.hoang8f.android.segmented.SegmentedGroup;

public class SearchFragment extends Fragment implements RadioButton.OnCheckedChangeListener {

    private SearchPeopleFragment searchPeopleFragment;
    private SearchEventsFragment searchEventsFragment;
    private SegmentedGroup segmentedGroup;
    private RadioButton radioButton1, radioButton2;
    private int currentSegmentBtn = R.id.btnSegmentedGroup1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_nav_search, container, false);
        segmentedGroup = viewRoot.findViewById(R.id.segmentedGroup);
        radioButton1 = viewRoot.findViewById(R.id.btnSegmentedGroup1);
        radioButton2 = viewRoot.findViewById(R.id.btnSegmentedGroup2);

        searchEventsFragment = new SearchEventsFragment();
        searchPeopleFragment = new SearchPeopleFragment();
        decorateSegmentedButtons();
        setListeners();

        getChildFragmentManager().beginTransaction()
                .add(R.id.fragmentContainerView_search, searchPeopleFragment)
                .commitAllowingStateLoss();
        return viewRoot;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putInt("currentSegmentBtn", currentSegmentBtn);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        /*if (savedInstanceState != null) {
            currentSegmentBtn = savedInstanceState.getInt("currentSegmentBtn");
        }*/
    }






    public void setListeners() {
        radioButton1.setOnCheckedChangeListener(this);
        radioButton2.setOnCheckedChangeListener(this);
    }

    public void decorateSegmentedButtons() {
        segmentedGroup.setTintColor(getResources().getColor(R.color.violet));
        if (currentSegmentBtn == R.id.btnSegmentedGroup1) {
            radioButton1.setChecked(true);
            radioButton2.setChecked(false);

        }
        else if (currentSegmentBtn == R.id.btnSegmentedGroup2) {
            radioButton1.setChecked(false);
            radioButton2.setChecked(true);

        }
    }








    public void changeFragment() {
        if (currentSegmentBtn == R.id.btnSegmentedGroup1)
            getChildFragmentManager().popBackStack();
        else if (currentSegmentBtn == R.id.btnSegmentedGroup2) {
            getChildFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainerView_search, searchEventsFragment)
                    .addToBackStack(null)
                    .commitAllowingStateLoss();
        }

    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean click) {
        if (click) {
            currentSegmentBtn = compoundButton.getId();
            changeFragment();
        }
    }
}
