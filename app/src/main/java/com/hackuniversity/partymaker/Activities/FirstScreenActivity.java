package com.hackuniversity.partymaker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hackuniversity.partymaker.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnEter_firstActivity)
    public void onClickEnter() {
        Intent intent = new Intent(this, EntranceActivity.class);
        startActivity(intent);
        finish();
    }



}
