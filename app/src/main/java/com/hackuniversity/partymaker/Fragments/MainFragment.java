package com.hackuniversity.partymaker.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hackuniversity.partymaker.Activities.EventActivity;
import com.hackuniversity.partymaker.R;
import com.hackuniversity.partymaker.ServerTransactions.RetrofitTransactions;

import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

public class MainFragment extends Fragment {

    private final static int REQUEST_EVENT_ACTIVITY = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewRoot = inflater.inflate(R.layout.fragment_nav_main, container, false);
        ButterKnife.bind(this, viewRoot);
        return viewRoot;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }







    @OnClick(R.id.floatingButton_addEvent)
    public void onCLickEvent() {
        if (getContext() != null) {
            Intent intent = new Intent(getContext(), EventActivity.class);
            startActivity(intent);
        }
    }
}
