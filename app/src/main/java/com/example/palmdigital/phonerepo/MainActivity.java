package com.example.palmdigital.phonerepo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
    String[] data;
    File dataPath = new File(Environment.getExternalStorageDirectory() + "/phonerepo/");
    File dataFile = new File(dataPath, "data.txt");

    TextView[] tvList = new TextView[100];
    TextView listtv;

    EditText[] inputFields = new EditText[5];

    Button saveProfile;
    Button createProfile;
    Button deleteProfile;

    String representSpaceInData = "&#&";

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

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void displayList(String[] list) {
        hideKeyboard();
        String representsNothing = "/";
        String toTV = "";
        for(int i = 0; i < list.length; i++) {

            if(list[i] == null) {list[i]=representsNothing;}
            if(list[i] == " ") {list[i]=representsNothing;}
            if(list[i] == "") {list[i]=representsNothing;}

            toTV = toTV + list[i] + "\n";
        }
        Log.d("asd", toTV);
        listtv.setVisibility(View.VISIBLE);
        listtv.setText(toTV);

    }

    public void setupComplete() {
        hideEverything();
        createProfile.setVisibility(View.VISIBLE);
        deleteProfile.setVisibility(View.VISIBLE);
        showTVlist();
        TextView tv = findViewById(R.id.textView71);
        tv.setVisibility(View.VISIBLE);
    }

    public void showProfileList() {



    }

    public void creProfile(View v) {
        hideEverything();
        for(int i = 0; i < inputFields.length; i++) {
            inputFields[i].setVisibility(View.VISIBLE);
        }
        saveProfile.setVisibility(View.VISIBLE);

    }

    public void saveProfile(View v) {
        hideKeyboard();
        String[] oldData = data;
        String[] newData = new String[inputFields.length];

        int count = 0;
        for(int i = 0; i < oldData.length; i++) {
            if(oldData[i] == null) {} else {
                count++;
            }
        }

        for(int i = 0; i < inputFields.length; i++) {
            newData[i] = inputFields[i].getText().toString();
            if (newData[i].matches("")) {
                newData[i] = null;
            }
        }

        hideEverything();
        displayList(newData);
        createProfile.setVisibility(View.VISIBLE);
        deleteProfile.setVisibility(View.VISIBLE);

        Log.d("asd", "Saveing to data.txt");
        String[] newDataWSpc = new String[newData.length+1];
        for(int i = 0; i < newData.length-1; i++) {
            newDataWSpc[i+1] = newData[i];
        }
        newDataWSpc[0] = representSpaceInData;
        String[] combine = ArrayUtils.addAll(loadTextFile(dataFile), newDataWSpc);
        writeToData(combine);
    }

    public void hidetvFileManager() {

    }

    public void delProfile(View v) {

    }

    public void hideEverything() {
        hidetvFileManager();
        createProfile.setVisibility(View.GONE);
        deleteProfile.setVisibility(View.GONE);
        for(int i = 0 ; i < inputFields.length; i++) {
            inputFields[i].setVisibility(View.GONE);
        }
        saveProfile.setVisibility(View.GONE);
    }

    public void showTVlist() {
        for(int i = 0; i < tvList.length; i++) {
        }
    }

    public void initializeVariables() {
        //Buttons, Textviews, and Editviews
        createProfile = (Button)findViewById(R.id.createProfile);
        deleteProfile = (Button)findViewById(R.id.deleteProfile);
        saveProfile = (Button)findViewById(R.id.saveProfile);

        Button addButton = new Button(this);

        listtv = (TextView) findViewById(R.id.textView71);
        listtv.setVisibility(View.VISIBLE);

        inputFields[0] = findViewById(R.id.input1);
        inputFields[1] = findViewById(R.id.input2);
        inputFields[2] = findViewById(R.id.input3);
        inputFields[3] = findViewById(R.id.input4);
        inputFields[4] = findViewById(R.id.input5);

        inputFields[0].setVisibility(View.GONE);
        inputFields[1].setVisibility(View.GONE);
        inputFields[2].setVisibility(View.GONE);
        inputFields[3].setVisibility(View.GONE);
        inputFields[4].setVisibility(View.GONE);


    }
}