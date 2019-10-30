package com.masoud.mediatorlivedata;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

public interface IActivityListener {

    void register(final MutableLiveData<Integer> liveData);

    MediatorLiveData<Integer> getMediatorLiveData();

    int getCurrentProgress();
}
