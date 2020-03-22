package com.hackuniversity.partymaker.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.hackuniversity.partymaker.Fragments.MainFragment;
import com.hackuniversity.partymaker.LiveData.AllPeopleLiveData;
import com.hackuniversity.partymaker.LiveData.LoginLiveData;
import com.hackuniversity.partymaker.LiveData.RegistrationLiveData;
import com.hackuniversity.partymaker.R;
import com.hackuniversity.partymaker.ServerTransactions.RetrofitTransactions;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntranceActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassowrd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
        ButterKnife.bind(this);

        editTextEmail = findViewById(R.id.editText_email_entrance);
        editTextPassowrd = findViewById(R.id.editText_password_entrance);

        LoginLiveData.getInstance().getLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer mode) {
                if (mode == LoginLiveData.LOGIN_SUCCESS)
                    RetrofitTransactions.getInstance().getAllUsers();
                else if (mode == LoginLiveData.LOGIN_FAILURE)
                    Toast.makeText(EntranceActivity.this, "Не удалось зарегистрироваться!", Toast.LENGTH_SHORT).show();
                if (mode != LoginLiveData.DEFAULT_MODE)
                    LoginLiveData.getInstance().setLiveData(LoginLiveData.DEFAULT_MODE);
            }
        });

        AllPeopleLiveData.getInstance().getLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer mode) {
                if (mode == AllPeopleLiveData.PEOPLE_SUCCESS)
                    entrance();
                else if (mode == AllPeopleLiveData.PEOPLE_FAILURE)
                    Toast.makeText(EntranceActivity.this, "Не удалось зарегистрироваться!", Toast.LENGTH_SHORT).show();
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




    @OnClick(R.id.btnEntrance)
    public void onClickEntrance() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassowrd.getText().toString();

        if (email.isEmpty())
            Toast.makeText(this, "Введите email!", Toast.LENGTH_SHORT).show();
        else if (password.isEmpty())
            Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
        else
            RetrofitTransactions.getInstance().login(email, password);
    }

    @OnClick(R.id.textView_goToRegistration)
    public void onClickGoToRegistration() {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }
}
