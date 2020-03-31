package com.hackuniversity.partymaker.ServerTransactions;

import android.util.Log;

import com.google.gson.Gson;
import com.hackuniversity.partymaker.LiveData.AllPeopleLiveData;
import com.hackuniversity.partymaker.LiveData.LoginLiveData;
import com.hackuniversity.partymaker.LiveData.RegistrationLiveData;
import com.hackuniversity.partymaker.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;

import static android.os.Build.HOST;

public class RetrofitTransactions {


    private static RetrofitTransactions retrofitTransactions;


    private RetrofitTransactions() {

    }

    public static RetrofitTransactions getInstance() {
        if (retrofitTransactions == null)
            retrofitTransactions = new RetrofitTransactions();
        return retrofitTransactions;
    }

    private int amountTransactions = 0;

    public void checkTransactionsDone() {
        amountTransactions++;
        if (amountTransactions % 5 == 0)
            AllPeopleLiveData.getInstance().setLiveData(AllPeopleLiveData.PEOPLE_SUCCESS);
    }


    public void getAllUsers() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApi serverApi = retrofit.create(ServerApi.class);

        Call<ArrayList<Person>> callGatAll = serverApi.getAll();
        callGatAll.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                if (response.isSuccessful()) {
                    Log.d("retrofit","success");
                    checkTransactionsDone();
                    AllPeopleLiveData.getInstance().setAllPeople(response.body());
                }
                else {
                    try {
                        AllPeopleLiveData.getInstance().setLiveData(AllPeopleLiveData.PEOPLE_FAILURE);
                        Log.d("retrofit", response.errorBody().string());
                        Log.d("retrofit", response.message());
                        Log.d("retrofit", response.code()+"");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
            }
        });
    }

    public void getSpecificRole(String role) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApi serverApi = retrofit.create(ServerApi.class);

        HashMap params = new HashMap();
        params.put("role", role);

        Call<ArrayList<Person>> callGatSpecificRole = serverApi.getSpecificRole(params);
        callGatSpecificRole.enqueue(new Callback<ArrayList<Person>>() {
            @Override
            public void onResponse(Call<ArrayList<Person>> call, Response<ArrayList<Person>> response) {
                if (response.isSuccessful()) {
                    checkTransactionsDone();
                    if (role.equals("DJ"))
                        AllPeopleLiveData.getInstance().setDjPeople(response.body());
                    else if (role.equals("Animator"))
                        AllPeopleLiveData.getInstance().setAnimatorPeople(response.body());
                    else if (role.equals("Cooker"))
                        AllPeopleLiveData.getInstance().setCookerPeople(response.body());
                    else if (role.equals("Dancer"))
                        AllPeopleLiveData.getInstance().setAnimatorPeople(response.body());
                    Log.d("retrofit","success");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
            }
        });
    }

    public void login(String email, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApi serverApi = retrofit.create(ServerApi.class);

        HashMap hashMap = new HashMap();
        hashMap.put("password", password);
        hashMap.put("email", email);

        Call<ResponseBody> callGatAll = serverApi.login(hashMap);
        callGatAll.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("retrofit","success");
                    LoginLiveData.getInstance().setLiveData(LoginLiveData.LOGIN_SUCCESS);
                }
                else {
                    LoginLiveData.getInstance().setLiveData(LoginLiveData.LOGIN_FAILURE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LoginLiveData.getInstance().setLiveData(LoginLiveData.LOGIN_FAILURE);
            }
        });
    }

    public void registrate(HashMap params) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApi serverApi = retrofit.create(ServerApi.class);

        Call<ResponseBody> callRegistrate = serverApi.registrate(params);
        callRegistrate.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                    RegistrationLiveData.getInstance().setLiveData(RegistrationLiveData.REGISTRATION_SUCCESS);
                else
                    RegistrationLiveData.getInstance().setLiveData(RegistrationLiveData.REGISTRATION_FAILURE);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

}

