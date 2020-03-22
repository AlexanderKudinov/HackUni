package com.hackuniversity.partymaker.LiveData;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginLiveData {

    public static int DEFAULT_MODE = 0, LOGIN_SUCCESS = 1, LOGIN_FAILURE = 2;
    private static LoginLiveData loginLiveData;
    private MutableLiveData<Integer> liveData;

    private LoginLiveData() {
        liveData = new MutableLiveData<>();
    }

    public static LoginLiveData getInstance() {
        if (loginLiveData == null)
            loginLiveData = new LoginLiveData();
        return loginLiveData;
    }

    public LiveData getLiveData() {
        return liveData;
    }

    public void setLiveData(int mode) {
        liveData.postValue(mode);
    }
}
