package com.hackuniversity.partymaker.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.hackuniversity.partymaker.Adapters.RecyclerViewPeopleAdaper;
import com.hackuniversity.partymaker.LiveData.AllPeopleLiveData;
import com.hackuniversity.partymaker.Person;
import com.hackuniversity.partymaker.R;

import java.util.ArrayList;

import butterknife.BindViews;
import butterknife.OnClick;

public class SearchPeopleFragment extends Fragment implements View.OnClickListener {

    private ArrayList<Person> people;

    private boolean djChosen = false, cookerChosen = false, dancerChosen = false, animatorChosen = false;
    private RecyclerView recyclerView;
    private RecyclerViewPeopleAdaper recyclerViewPeopleAdaper;
    private TextView textViewDj, textViewCooker, textViewDancer, textViewAnimator;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_search_people, container, false);
        recyclerView = viewRoot.findViewById(R.id.recyclerView_searchPeople);

        setRoles(viewRoot);
        people = AllPeopleLiveData.getInstance().getAllPeople();
        recyclerViewPeopleAdaper = new RecyclerViewPeopleAdaper(people);
        recyclerView.setAdapter(recyclerViewPeopleAdaper);
        return viewRoot;
    }

    public void setRoles(View viewRoot) {
        textViewDj = viewRoot.findViewById(R.id.roleDj_searchPeople);
        textViewAnimator = viewRoot.findViewById(R.id.roleAnimator_searchPeople);
        textViewCooker = viewRoot.findViewById(R.id.roleCooker_searchPeople);
        textViewDancer = viewRoot.findViewById(R.id.roleDancer_searchPeople);

        textViewDj.setOnClickListener(this);
        textViewAnimator.setOnClickListener(this);
        textViewCooker.setOnClickListener(this);
        textViewDancer.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        if (!djChosen && !dancerChosen && !cookerChosen && !animatorChosen)
            people = new ArrayList<>();

        switch (view.getId()) {
            case R.id.roleAnimator_searchPeople:
                if (animatorChosen) {
                    animatorChosen = false;
                    view.setBackground(getResources().getDrawable(R.drawable.role, null));
                    ((TextView)view).setTextColor(getResources().getColor(R.color.black));
                    people.removeAll(AllPeopleLiveData.getInstance().getAnimatorPeople());
                }
                else {
                    animatorChosen = true;
                    view.setBackground(getResources().getDrawable(R.drawable.role_violet, null));
                    ((TextView)view).setTextColor(getResources().getColor(R.color.white));
                    people.addAll(AllPeopleLiveData.getInstance().getAnimatorPeople());
                }
                break;
            case R.id.roleCooker_searchPeople:
                if (cookerChosen) {
                    cookerChosen = false;
                    view.setBackground(getResources().getDrawable(R.drawable.role, null));
                    ((TextView)view).setTextColor(getResources().getColor(R.color.black));
                    people.removeAll(AllPeopleLiveData.getInstance().getCookerPeople());
                }
                else {
                    cookerChosen = true;
                    view.setBackground(getResources().getDrawable(R.drawable.role_violet, null));
                    ((TextView)view).setTextColor(getResources().getColor(R.color.white));
                    people.addAll(AllPeopleLiveData.getInstance().getCookerPeople());
                }
                break;
            case R.id.roleDancer_searchPeople:
                if (dancerChosen) {
                    dancerChosen = false;
                    view.setBackground(getResources().getDrawable(R.drawable.role, null));
                    ((TextView)view).setTextColor(getResources().getColor(R.color.black));
                    people.removeAll(AllPeopleLiveData.getInstance().getDancerPeople());
                }
                else {
                    dancerChosen = true;
                    view.setBackground(getResources().getDrawable(R.drawable.role_violet, null));
                    ((TextView)view).setTextColor(getResources().getColor(R.color.white));
                    people.addAll(AllPeopleLiveData.getInstance().getDancerPeople());
                }
                break;
            case R.id.roleDj_searchPeople:
                if (djChosen) {
                    djChosen = false;
                    view.setBackground(getResources().getDrawable(R.drawable.role, null));
                    ((TextView)view).setTextColor(getResources().getColor(R.color.black));
                    people.removeAll(AllPeopleLiveData.getInstance().getDjPeople());
                }
                else {
                    djChosen = true;
                    view.setBackground(getResources().getDrawable(R.drawable.role_violet, null));
                    ((TextView)view).setTextColor(getResources().getColor(R.color.white));
                    people.addAll(AllPeopleLiveData.getInstance().getDjPeople());
                }
                break;
        }

        if (!djChosen && !dancerChosen && !cookerChosen && !animatorChosen)
            people = AllPeopleLiveData.getInstance().getAllPeople();

        recyclerViewPeopleAdaper = new RecyclerViewPeopleAdaper(people);
        recyclerView.setAdapter(recyclerViewPeopleAdaper);
    }
}
