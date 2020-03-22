package com.hackuniversity.partymaker.LiveData;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class RegistrationLiveData {

    public static int DEFAULT_MODE = 0, REGISTRATION_SUCCESS = 1, REGISTRATION_FAILURE = 2;
    private static RegistrationLiveData registrationLiveData;
    private MutableLiveData<Integer> liveData;

    private RegistrationLiveData() {
        liveData = new MutableLiveData<>();
    }

    public static RegistrationLiveData getInstance() {
        if (registrationLiveData == null)
            registrationLiveData = new RegistrationLiveData();
        return registrationLiveData;
    }

    public LiveData getLiveData() {
        return liveData;
    }

    public void setLiveData(int mode) {
        liveData.postValue(mode);
    }
}
