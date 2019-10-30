package com.masoud.mediatorlivedata;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class FragmentB extends Fragment {

    private SeekBar seekBar_B;
    private TextView tv_title;
    private MutableLiveData<Integer> liv_progress;
    private IActivityListener activityListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        liv_progress = new MutableLiveData<>();

        activityListener = (IActivityListener) context;

        register();

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

        initViews();

        setListeners();

    }

    @Override
    public void onStop() {
        super.onStop();

        liv_progress.setValue(seekBar_B.getProgress());
    }

    private void findView(View rootView){

        seekBar_B = rootView.findViewById(R.id.seekBar_B);
        tv_title = rootView.findViewById(R.id.tv_title);
    }
    
    private void initViews(){

        tv_title.setText("Fragment B = " + activityListener.getCurrentProgress());
        seekBar_B.setProgress(activityListener.getCurrentProgress());

    }

    private void setListeners() {

        seekBar_B.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                liv_progress.setValue(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        liv_progress.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                tv_title.setText("Fragment B = " + integer);
            }
        });

        activityListener.getMediatorLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                seekBar_B.setProgress(integer);
            }
        });
    }

    private void register(){

        activityListener.register(liv_progress);

    }


}
