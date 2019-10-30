package com.masoud.mediatorlivedata;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runFragment();
    }

    private void runFragment(){

        FragmentTransaction transaction  = getSupportFragmentManager().beginTransaction();
        transaction.add(new FragmentA() , "FRAGMENT_A").commit();
    }


}
