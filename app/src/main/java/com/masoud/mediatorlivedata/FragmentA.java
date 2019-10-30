package com.masoud.mediatorlivedata;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

public class FragmentA extends Fragment {

    private SeekBar seekBar_A;
    private MutableLiveData<Integer> liv_progress;
    private Button btn_nextFragment;
    private TextView tv_title;
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
        return inflater.inflate(R.layout.fragment_a, container, false);
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

        liv_progress.setValue(seekBar_A.getProgress());
    }

    private void findView(View rootView) {

        seekBar_A = rootView.findViewById(R.id.seekBar_A);
        tv_title = rootView.findViewById(R.id.tv_title);
        btn_nextFragment = rootView.findViewById(R.id.btn_nextFragment);
    }

    private void initViews() {

        tv_title.setText("Fragment A = " + activityListener.getCurrentProgress());
        seekBar_A.setProgress(activityListener.getCurrentProgress());

    }

    private void setListeners() {

        seekBar_A.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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

        btn_nextFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new FragmentB(), "FRAGMENT_B")
                        .addToBackStack("FRAGMENT_B")
                        .commit();
            }
        });

        liv_progress.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                tv_title.setText("Fragment A = " + integer);
            }
        });


        activityListener.getMediatorLiveData().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {

                seekBar_A.setProgress(integer);
            }
        });
    }

    private void register() {

        activityListener.register(liv_progress);

    }


}
