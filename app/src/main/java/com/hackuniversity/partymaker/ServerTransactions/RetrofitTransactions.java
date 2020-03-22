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
        if (amountTransactions == 5)
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
                    Log.d("retrofittt","success");
                    checkTransactionsDone();
                    AllPeopleLiveData.getInstance().setAllPeople(response.body());
                }
                else {
                    try {
                        AllPeopleLiveData.getInstance().setLiveData(AllPeopleLiveData.PEOPLE_FAILURE);
                        Log.d("retrofittt", response.errorBody().string());
                        Log.d("retrofittt", response.message());
                        Log.d("retrofittt", response.code()+"");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                Log.d("retrofittt", t.getMessage());
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
                    Log.d("retrofittt","success");
                }
                else {
                    try {
                        Log.d("retrofittt", response.errorBody().string());
                        Log.d("retrofittt", response.message());
                        Log.d("retrofittt", response.code()+"");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Person>> call, Throwable t) {
                Log.d("retrofittt", t.getMessage());
            }
        });
    }

    public void login(String email, String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApi serverApi = retrofit.create(ServerApi.class);

        Call<ResponseBody> callGatAll = serverApi.login(password, email);
        callGatAll.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("retrofittt","success");
                    LoginLiveData.getInstance().setLiveData(LoginLiveData.LOGIN_SUCCESS);
                }
                else {
                    LoginLiveData.getInstance().setLiveData(LoginLiveData.LOGIN_FAILURE);
                    try {
                        Log.d("retrofittt", response.errorBody().string());
                        Log.d("retrofittt", response.message());
                        Log.d("retrofittt", response.code()+"");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("retrofittt", t.getMessage());
            }
        });
    }

    public void registrate(HashMap params) {
        Log.d("ttttt", params.get("username").toString());
        Log.d("ttttt", params.get("password").toString());
        Log.d("ttttt", params.get("role").toString());
        Log.d("ttttt", params.get("distance").toString());
        Log.d("ttttt", params.get("description").toString());
        Log.d("ttttt", params.get("price").toString());
        Log.d("ttttt", params.get("email").toString());
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLs.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ServerApi serverApi = retrofit.create(ServerApi.class);

        Log.d("tttttttttttttt", new Gson().toJson(params));
        Call<ResponseBody> callRegistrate = serverApi.registrate(params.get("username").toString(),
                params.get("password").toString(),
                params.get("role").toString(), Double.valueOf(params.get("distance").toString()),
                params.get("description").toString(), Integer.valueOf(params.get("price").toString()),
                params.get("email").toString());
        callRegistrate.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("retrofittt", "success");
                    RegistrationLiveData.getInstance().setLiveData(RegistrationLiveData.REGISTRATION_SUCCESS);
                } else {
                    RegistrationLiveData.getInstance().setLiveData(RegistrationLiveData.REGISTRATION_FAILURE);
                    try {
                        Log.d("retrofittt", response.errorBody().string());
                        Log.d("retrofittt", response.message());
                        Log.d("retrofittt", response.code() + "");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("retrofittt", t.getMessage());
            }
        });
    }

}

