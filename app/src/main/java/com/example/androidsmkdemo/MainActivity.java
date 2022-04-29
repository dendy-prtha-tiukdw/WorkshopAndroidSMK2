package com.example.androidsmkdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("on create di eksekusi pertama");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_insert_siswa,
                            InsertSiswaFragment.class, null)
                    .commit();
        }
    }

    private void prepareFragment() {
        //using view pager
    }
}