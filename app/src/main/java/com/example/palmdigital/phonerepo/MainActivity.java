package com.example.palmdigital.phonerepo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    TextView[] tvFileViewer = new TextView[27];

    Button createProfile;
    Button deleteProfile;

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

    @SuppressLint("ResourceType")




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

        initializeVariables();

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

    public void displayList(String[] list) {
        for (int i = 0; i < 25; i++) {
            if (list[i] == null) {
            } else {
                tvFileViewer[i].setText(list[i]);
            }
        }
    }

    public void setupComplete() {

    }

    public void showProfileList() {



    }

    public void creProfile(View v) {
        hideEverything();
        String[] oldData = new String[100000];
        oldData = loadTextFile(dataFile);



    }

    public void hidetvFileManager() {
        for(int i = 0; i < tvFileViewer.length; i++) {
            tvFileViewer[i].setVisibility(View.INVISIBLE);
        }
    }

    public void delProfile(View v) {

    }

    public void hideEverything() {

        createProfile.setVisibility(View.INVISIBLE);
        deleteProfile.setVisibility(View.INVISIBLE);
    }

    public void initializeVariables() {
        //Buttons, Textviews, and Editviews
        createProfile = (Button)findViewById(R.id.createProfile);
        deleteProfile = (Button)findViewById(R.id.deleteProfile);

        LinearLayout listLayout = (LinearLayout) findViewById(R.id.list_layout);
        Button addButton = new Button(this);

        tvFileViewer[0] = (TextView) findViewById(R.id.textLine25);
        tvFileViewer[1] = (TextView) findViewById(R.id.textLine24);
        tvFileViewer[2] = (TextView) findViewById(R.id.textLine23);
        tvFileViewer[3] = (TextView) findViewById(R.id.textLine22);
        tvFileViewer[4] = (TextView) findViewById(R.id.textLine21);
        tvFileViewer[5] = (TextView) findViewById(R.id.textLine20);
        tvFileViewer[6] = (TextView) findViewById(R.id.textLine19);
        tvFileViewer[7] = (TextView) findViewById(R.id.textLine18);
        tvFileViewer[8] = (TextView) findViewById(R.id.textLine17);
        tvFileViewer[9] = (TextView) findViewById(R.id.textLine16);
        tvFileViewer[10] = (TextView) findViewById(R.id.textLine15);
        tvFileViewer[11] = (TextView) findViewById(R.id.textLine14);
        tvFileViewer[12] = (TextView) findViewById(R.id.textLine13);
        tvFileViewer[13] = (TextView) findViewById(R.id.textLine12);
        tvFileViewer[14] = (TextView) findViewById(R.id.textLine11);
        tvFileViewer[15] = (TextView) findViewById(R.id.textLine10);
        tvFileViewer[16] = (TextView) findViewById(R.id.textLine9);
        tvFileViewer[17] = (TextView) findViewById(R.id.textLine8);
        tvFileViewer[18] = (TextView) findViewById(R.id.textLine7);
        tvFileViewer[19] = (TextView) findViewById(R.id.textLine6);
        tvFileViewer[20] = (TextView) findViewById(R.id.textLine5);
        tvFileViewer[21] = (TextView) findViewById(R.id.textLine4);
        tvFileViewer[22] = (TextView) findViewById(R.id.textLine3);
        tvFileViewer[23] = (TextView) findViewById(R.id.textLine2);
        tvFileViewer[24] = (TextView) findViewById(R.id.textLine1);
        tvFileViewer[25] = (TextView) findViewById(R.id.textLine);

    }
}