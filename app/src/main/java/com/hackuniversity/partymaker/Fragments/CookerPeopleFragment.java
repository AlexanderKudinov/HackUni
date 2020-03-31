package com.hackuniversity.partymaker.Fragments;

import android.os.Bundle;
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
import com.hackuniversity.partymaker.R;

public class CookerPeopleFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.people_for_events, container, false);
        TextView textView = viewRoot.findViewById(R.id.textView_rolePeople_events);
        textView.setText("Повары");
        RecyclerView recyclerView = viewRoot.findViewById(R.id.recyclerView_addPeopleOnEvent);
        RecyclerViewPeopleAdaper adapter = new RecyclerViewPeopleAdaper(AllPeopleLiveData.getInstance().getCookerPeople());
        recyclerView.setAdapter(adapter);
        return viewRoot;
    }
}
