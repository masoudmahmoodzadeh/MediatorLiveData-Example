package com.masoud.mediatorlivedata;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

public class FragmentB extends Fragment {

    private SeekBar seekBar_B;
    private MutableLiveData<Integer> liv_progress;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        liv_progress = new MutableLiveData<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_b , container , false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findView(view);
    }

    @Override
    public void onStop() {
        super.onStop();

        liv_progress.setValue(seekBar_B.getProgress());
    }

    private void findView(View rootView){

        seekBar_B = rootView.findViewById(R.id.seekBar_B);
    }

    public MutableLiveData getLiveData(){

        return liv_progress;
    }
    
}
