package com.hackuniversity.partymaker.ServerTransactions;

import com.hackuniversity.partymaker.Person;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerApi {

    @POST("get_all")
    public Call<ArrayList<Person>> getAll();
    @POST("get_specific_role")
    public Call<ArrayList<Person>> getSpecificRole(@Body HashMap<String, String> role);
    @POST("login")
    public Call<ResponseBody> login(@Body HashMap hashMap);
    @POST("register")
    public Call<ResponseBody> registrate(@Body HashMap hashMap);
}
