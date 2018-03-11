package com.example.palmdigital.phonerepo;

import android.content.Context;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    String phoneFilename = "phonerepo";
    String saveString = "ASDASDAS";
    String representsNothing = ".";
    String representEndInData = "&#&";
    String representsStartInData = "%#%";
    String[] data;
    String[][] profileDataFiles = new String[100][1000];

    int numOfProfiles = 0;

    File dataPath = new File(Environment.getExternalStorageDirectory() + "/phonerepo/");
    File dataFile = new File(dataPath, "data.txt");
    File profilePath = new File(Environment.getExternalStorageDirectory() + "/phonerepo/profiledata");

    EditText[] inputFields = new EditText[5];

    Button saveProfile;
    Button createProfile;
    Button deleteProfile;
    Button settingsButton;
    Button editButton1;
    Button editButton2;
    Button editButton3;

    TextView[] editButton1tvs = new TextView[4];
    TextView[] editButton2tvs = new TextView[4];
    TextView[] editButton3tvs = new TextView[4];
    TextView[] tvList = new TextView[100];
    TextView listtv;

    boolean settingsOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setup();
        String[] testArray = new String[2];
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

        if(!profilePath.exists()) {
            profilePath.mkdirs();
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
        String toTV = "";
        for(int i = 0; i < list.length; i++) {

            if(list[i] == null) {list[i]=representsNothing;}
            if(list[i] == " ") {list[i]=representsNothing;}
            if(list[i] == "") {list[i]=representsNothing;}

            toTV = toTV + list[i] + "\n";
        }
        listtv.setVisibility(View.VISIBLE);
        listtv.setText(toTV);

    }

    public void setupComplete() {
        hideEverything();
        createProfile.setVisibility(View.VISIBLE);
        deleteProfile.setVisibility(View.VISIBLE);
        TextView tv = findViewById(R.id.textView71);
        tv.setVisibility(View.VISIBLE);
        changeEditVisibility(false);
    }

    public void editProfile(View v) {
        hideEverything();
        settingsButton.setVisibility(View.GONE);
        listtv.setVisibility(View.GONE);
        changeEditVisibility(true);

        String[] dataLoad = loadTextFile(dataFile);

        for(int i = 0; i < dataLoad.length-2; i++) {
            dataLoad[i+1] = dataLoad[i+2];
            if(dataLoad[i] == null || dataLoad[i] == "" || dataLoad[i] == " ") {
                dataLoad[i] = representsNothing;
            }
        }
        dataLoad[0] = representEndInData;
        dataLoad[dataLoad.length-1] = representsNothing;
        dataLoad[dataLoad.length-2] = representsNothing;



        int count = 0;
        int saves = -1;
        String[] tempDataLoad = new String[dataLoad.length];
        boolean reading = false;

        for(int i = 0; i < dataLoad.length-1; i++) {
            dataLoad[i] = dataLoad[i+1];
        }

        for(int i = 0; i < dataLoad.length; i++) {
            if(dataLoad[i].equals(representsStartInData)) {
                reading = true;
                Log.d("asd", "new start");
                for(int c = 0; c < tempDataLoad.length; c++) {
                    tempDataLoad[c] = representsNothing;
                }
            }

            if(dataLoad[i].equals(representEndInData)) {
                reading = false;
                saves++;
                Log.d("asd", "next save");
                Log.d("asd", tempDataLoad[1]);
                profileDataFiles[saves] = tempDataLoad;
                count = 0;
            }

            if(reading == true) {
                tempDataLoad[count] = dataLoad[i];
                count++;
            }

        }



        for(int i = 0; i < profileDataFiles[0].length-1; i++) {
            profileDataFiles[0][i] = profileDataFiles[0][i+1];
        }

        count=0;
        int countMini = 0;
        for(int i = 0; i < profileDataFiles.length; i++) {

            if(profileDataFiles[count][countMini] == representEndInData) {
                profileDataFiles[count][countMini] = representsNothing;
            }

            Log.d("asd", "[" + count + "]" + "[" + countMini + "]"  + profileDataFiles[count][countMini]);

            if(countMini > 10) {
                count++;
                countMini=-1;
            }

            countMini++;
        }

    }

    public void editBack(View v) {
        hideEverything();
        createProfile.setVisibility(View.VISIBLE);
        deleteProfile.setVisibility(View.VISIBLE);
        listtv.setVisibility(View.VISIBLE);
    }

    public void printList(String[] list) {
        for(int i = 0; i < list.length; i++) {
            if(list[i] == null) {
                list[i] = representsNothing;
            }
            Log.d("asd", list[i]);
        }
    }

    public void creProfile(View v) {
        hideEverything();

        Button settings = findViewById(R.id.settingsButton);
        settings.setVisibility(View.GONE);

        for(int i = 0; i < inputFields.length; i++) {
            inputFields[i].setVisibility(View.VISIBLE);
        }
        saveProfile.setVisibility(View.VISIBLE);

    }

    public void saveProfile(View v) {
        hideKeyboard();
        String[] oldData = data;
        String[] newData = new String[inputFields.length];

        Button settings = findViewById(R.id.settingsButton);
        settings.setVisibility(View.VISIBLE);

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
        createProfile.setVisibility(View.VISIBLE);
        deleteProfile.setVisibility(View.VISIBLE);

        inputFields[0].setText("");
        inputFields[1].setText("");
        inputFields[2].setText("");
        inputFields[3].setText("");
        inputFields[4].setText("");

        String[] newDataWSpc = new String[newData.length+2];
        for(int i = 0; i < newData.length; i++) {
            newDataWSpc[i+1] = newData[i];
        }
        newDataWSpc[0] = representsStartInData;
        newDataWSpc[newData.length+1] = representEndInData;

        String[] combine = new String[loadTextFile(dataFile).length+newDataWSpc.length];
        String[] dataLoad = loadTextFile(dataFile);

        for(int i = 0; i < dataLoad.length; i++) {
            combine[i] = dataLoad[i];
        }
        for(int i = 0; i < newDataWSpc.length; i++) {
            combine[dataLoad.length+i] = newDataWSpc[i];
        }

        writeToData(combine);

        dataLoad = loadTextFile(dataFile);
        displayList(dataLoad);
    }

    public void hidetvFileManager() {

    }

    public void delProfile(View v) {

    }
    public void changeEditVisibility(boolean showorhide) {
        if(showorhide == true) {
            for (int i = 0; i < 4; i++) {
                editButton3tvs[i].setVisibility(View.VISIBLE);
                editButton2tvs[i].setVisibility(View.VISIBLE);
                editButton1tvs[i].setVisibility(View.VISIBLE);
            }
            editButton3.setVisibility(View.VISIBLE);
            editButton2.setVisibility(View.VISIBLE);
            editButton1.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < 4; i++) {
                editButton3tvs[i].setVisibility(View.GONE);
                editButton2tvs[i].setVisibility(View.GONE);
                editButton1tvs[i].setVisibility(View.GONE);
            }
            editButton3.setVisibility(View.GONE);
            editButton2.setVisibility(View.GONE);
            editButton1.setVisibility(View.GONE);
        }
    }

    public void settings(View v) {
        if(settingsOpen == false) {
            settingsOpen = true;
        } else {
            settingsOpen = false;
        }

        if(settingsOpen == true) {
            hideEverything();
            Button clearSettings = findViewById(R.id.clearDataButton);
            clearSettings.setVisibility(View.VISIBLE);
        } else {
            Button clearSettings = findViewById(R.id.clearDataButton);
            clearSettings.setVisibility(View.GONE);
            setupComplete();
        }

    }

    public void clearData(View v) {
        String[] restore = new String[2];
        restore[0] = "hello";
        restore[1] = "world";
        writeToData(restore);
        hideEverything();
        Button clearSettings = findViewById(R.id.clearDataButton);
        clearSettings.setVisibility(View.GONE);
        createProfile.setVisibility(View.VISIBLE);
        deleteProfile.setVisibility(View.VISIBLE);
        displayList(loadTextFile(dataFile));
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

    public void initializeVariables() {
        //Buttons, Textviews, and Editviews
        createProfile = (Button)findViewById(R.id.createProfile);
        deleteProfile = (Button)findViewById(R.id.deleteProfile);
        saveProfile = (Button)findViewById(R.id.saveProfile);

        Button addButton = new Button(this);
        settingsButton =findViewById(R.id.settingsButton);
        editButton1 = findViewById(R.id.editButton1);
        editButton2 = findViewById(R.id.editButton2);
        editButton3 = findViewById(R.id.editButton3);

        editButton1tvs[0] = findViewById(R.id.button1tv1);
        editButton1tvs[1] = findViewById(R.id.button1tv2);
        editButton1tvs[2] = findViewById(R.id.button1tv3);
        editButton1tvs[3] = findViewById(R.id.button1tv4);

        editButton2tvs[0] = findViewById(R.id.button2tv1);
        editButton2tvs[1] = findViewById(R.id.button2tv2);
        editButton2tvs[2] = findViewById(R.id.button2tv3);
        editButton2tvs[3] = findViewById(R.id.button2tv4);

        editButton3tvs[0] = findViewById(R.id.button3tv1);
        editButton3tvs[1] = findViewById(R.id.button3tv2);
        editButton3tvs[2] = findViewById(R.id.button3tv3);
        editButton3tvs[3] = findViewById(R.id.button3tv4);

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