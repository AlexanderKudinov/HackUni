package com.hackuniversity.partymaker.LiveData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hackuniversity.partymaker.Person;

import java.util.ArrayList;

public class AllPeopleLiveData {

    public static int DEFAULT_MODE = 0, PEOPLE_SUCCESS = 1, PEOPLE_FAILURE = 2;
    private static AllPeopleLiveData allPeopleLiveData;
    private MutableLiveData<Integer> liveData;

    private ArrayList<Person> listPeople, djPeople, cookerPeople, dancerPeople, animatorPeople;

    private AllPeopleLiveData() {
        liveData = new MutableLiveData<>();
    }

    public static AllPeopleLiveData getInstance() {
        if (allPeopleLiveData == null)
            allPeopleLiveData = new AllPeopleLiveData();
        return allPeopleLiveData;
    }

    public LiveData getLiveData() {
        return liveData;
    }

    public void setLiveData(int mode) {
        liveData.postValue(mode);
    }

    public ArrayList<Person> getAllPeople() {
        return listPeople;
    }

    public void setAllPeople(ArrayList<Person> people) {
        listPeople = people;
    }


    public ArrayList<Person> getDjPeople() {
        if (djPeople == null)
            return new ArrayList<>();
        return djPeople;
    }

    public void setDjPeople(ArrayList<Person> dj) {
        djPeople = dj;
    }


    public ArrayList<Person> getCookerPeople() {
        if (cookerPeople == null)
            return new ArrayList<>();
        return cookerPeople;
    }

    public void setCookerPeople(ArrayList<Person> people) {
        cookerPeople = people;
    }


    public ArrayList<Person> getDancerPeople() {
        if (dancerPeople == null)
            return new ArrayList<>();
        return dancerPeople;
    }

    public void setDancerPeople(ArrayList<Person> people) {
        dancerPeople = people;
    }



    public ArrayList<Person> getAnimatorPeople() {
        if (animatorPeople == null)
            return new ArrayList<>();
        return animatorPeople;
    }

    public void setAnimatorPeople(ArrayList<Person> people) {
        animatorPeople = people;
    }
}
