package com.example.palmdigital.phonerepo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    File root = new File(Environment.getExternalStorageDirectory(), "/phonerepo/");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void setup() {
        if(!root.exists()) {
            root.mkdirs();
        }
        
    }
}
