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
    @FormUrlEncoded
    @POST("login")
    public Call<ResponseBody> login(@Field("password") String password, @Field("email") String email);
    @FormUrlEncoded
    @POST("register")
    public Call<ResponseBody> registrate(@Field("username") String username, @Field("password") String password,
                                         @Field("role") String role, @Field("distance") double distance,
                                         @Field("description") String description, @Field("price") int price,
                                         @Field("email") String email);
}
