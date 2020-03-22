package com.hackuniversity.partymaker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.hackuniversity.partymaker.LiveData.AllPeopleLiveData;
import com.hackuniversity.partymaker.LiveData.LoginLiveData;
import com.hackuniversity.partymaker.LiveData.RegistrationLiveData;
import com.hackuniversity.partymaker.R;
import com.hackuniversity.partymaker.ServerTransactions.RetrofitTransactions;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);

        RegistrationLiveData.getInstance().getLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer mode) {
                if (mode == RegistrationLiveData.REGISTRATION_SUCCESS)
                    RetrofitTransactions.getInstance().getAllUsers();
                else if (mode == RegistrationLiveData.REGISTRATION_FAILURE)
                    Toast.makeText(RegistrationActivity.this, "Не удалось зарегистрироваться!", Toast.LENGTH_SHORT).show();
                if (mode != RegistrationLiveData.DEFAULT_MODE) {
                    RegistrationLiveData.getInstance().setLiveData(RegistrationLiveData.DEFAULT_MODE);
                    RetrofitTransactions.getInstance().getAllUsers();
                    RetrofitTransactions.getInstance().getSpecificRole("DJ");
                    RetrofitTransactions.getInstance().getSpecificRole("Animator");
                    RetrofitTransactions.getInstance().getSpecificRole("Cooker");
                    RetrofitTransactions.getInstance().getSpecificRole("Dancer");
                }
                }
        });

        AllPeopleLiveData.getInstance().getLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer mode) {
                if (mode == AllPeopleLiveData.PEOPLE_SUCCESS)
                    entrance();
                else if (mode == AllPeopleLiveData.PEOPLE_FAILURE)
                    Toast.makeText(RegistrationActivity.this, "Не удалось зарегистрироваться!", Toast.LENGTH_SHORT).show();
                if (mode != AllPeopleLiveData.DEFAULT_MODE)
                    AllPeopleLiveData.getInstance().setLiveData(AllPeopleLiveData.DEFAULT_MODE);
            }
        });
    }




    public void entrance() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }




    @OnClick(R.id.btnRegistration)
    public void onClickRegistrate() {
        String email = ((EditText) findViewById(R.id.editText_email_registration)).getText().toString();
        String password = ((EditText) findViewById(R.id.editText_password_registration)).getText().toString();
        String role = ((EditText) findViewById(R.id.editText_role_registration)).getText().toString();
        String username = ((EditText) findViewById(R.id.editText_username_registration)).getText().toString();
        int price = Integer.valueOf(((EditText) findViewById(R.id.editText_price_registration)).getText().toString());
        String description = ((EditText) findViewById(R.id.editText_desription_registration)).getText().toString();
        double distance = 4000;

        if (email.isEmpty())
            Toast.makeText(this, "Введите email!", Toast.LENGTH_SHORT);
        else if (password.isEmpty())
            Toast.makeText(this, "Введите пароль!", Toast.LENGTH_SHORT);
        else if (role.isEmpty())
            Toast.makeText(this, "Введите свою роль!", Toast.LENGTH_SHORT);
        else if (username.isEmpty())
            Toast.makeText(this, "Введите имя!", Toast.LENGTH_SHORT);
        else if (price == 0)
            Toast.makeText(this, "Введите расценки!", Toast.LENGTH_SHORT);

        else {
            HashMap params = new HashMap();
            params.put("username", username);
            params.put("password", password);
            params.put("role", role);
            params.put("distance", distance);
            params.put("description", description);
            params.put("price", price);
            params.put("email", email);
            RetrofitTransactions.getInstance().registrate(params);
        }

    }


    @OnClick(R.id.textView_goToEntrance)
    public void onClickTurnBack() {
        finish();
    }
}
