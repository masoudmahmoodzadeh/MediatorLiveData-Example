package com.masoud.mediatorlivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IActivityListener {

    private MediatorLiveData<Integer> liveDataMerger = new MediatorLiveData<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        liveDataMerger.setValue(0);

        runFragment();
    }

    private void runFragment() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, new FragmentA(), "FRAGMENT_A")
                .addToBackStack("FRAGMENT_A")
                .commit();
    }

    @Override
    public void register(final MutableLiveData<Integer> liveData) {

        liveDataMerger.addSource(liveData, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                liveDataMerger.setValue(integer);
            }
        });


    }

    @Override
    public MediatorLiveData<Integer> getMediatorLiveData() {
        return liveDataMerger;
    }

    @Override
    public int getCurrentProgress() {
        return getMediatorLiveData().getValue() != null ? getMediatorLiveData().getValue() : 0;
    }


}
