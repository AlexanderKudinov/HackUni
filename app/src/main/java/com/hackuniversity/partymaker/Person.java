package com.hackuniversity.partymaker;

import com.google.gson.annotations.SerializedName;

public class Person {

    public final static String ROLE_DJ = "DJ", ROLE_BARMAN = "бармен", ROLE_ANIMATOR = "аниматор",
                ROLE_LEADER = "ведущий";

    @SerializedName("description")
    private String description = "";
    @SerializedName("distance")
    private double distance = 0;
    @SerializedName("email")
    private String email = "";
    @SerializedName("id")
    private int id = 0;
    @SerializedName("password")
    private String password = "";
    @SerializedName("price")
    private int price = 0;
    @SerializedName("priority")
    private boolean priority = false;
    @SerializedName("role")
    private String role = "";
    @SerializedName("username")
    private String username = "";
    @SerializedName("avatar")
    private String urlImage;

    public Person() {}

    public String getName() {
        return username;
    }

    public String getPhoto() {
        return urlImage;
    }
}
