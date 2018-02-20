package com.example.palmdigital.phonerepo;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends AppCompatActivity {
    String phoneFilename = "phonerepo";
    String saveString = "ASDASDAS";
    String[] data = new String[1000000];
    File dataPath = new File(Environment.getExternalStorageDirectory() + "/phonerepo/");
    File dataFile = new File(dataPath, "data.txt");

    //Buttons, Textviews, and Editviews
    Button createProfile = (Button)findViewById(R.id.createProfile);
    Button deleteProfile = (Button)findViewById(R.id.deleteProfile);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        String[] testArray = new String[10];
        testArray[0] = "hello";
        testArray[1] = "world";
        writeToData(testArray);
        data = loadTextFile(dataFile);
        setupComplete();

    }

    public void writeToData(String[] toWriteData) {
        try {
            FileWriter writer = new FileWriter(dataFile);

            for (int i = 0; i < toWriteData.length; i++) {
                if (toWriteData[i] == null) {
                } else {
                    writer.append(toWriteData[i] + "\n");
                }
            }

            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setup() {

        String folderName = "phonerepo";
        String dataFileName = "data.txt";

        File file = new File(Environment.getExternalStorageDirectory(),
                folderName);
        if (!file.exists()) {
            file.mkdirs();
        }
        File dataFile = new File(Environment.getExternalStorageDirectory() + "/phonerepo/",
                dataFileName);
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String[] loadTextFile(File inFile) {
        FileInputStream fis;
        String[] output = new String[1000000];

        FileInputStream is;
        BufferedReader reader;
        final File textFile = new File(String.valueOf(inFile));
        String[] textFileStr = new String[1000];


        if (textFile.exists()) {
            try {
                is = new FileInputStream(textFile);
                reader = new BufferedReader(new InputStreamReader(is));
                String line = "";
                int count = 0;

                while ((line = reader.readLine()) != null) {
                    textFileStr[count] = line;
                    count++;
                }

                return textFileStr;


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return null;
    }

    public void printList(String[] list) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
            } else {
                Log.d("asd", list[i]);
            }
        }
    }

    public void setupComplete() {
        showProfileList();

    }

    public void showProfileList() {
        createProfile.setVisibility(View.INVISIBLE);
        deleteProfile.setVisibility(View.INVISIBLE);



    }
}