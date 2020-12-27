package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class MainActivity extends AppCompatActivity {

    private TextView out;
    private Button clickMe;
    private File file;

    private void initHistoryFile() {
        file = new File(getFilesDir().toString() + "/history.dat");
        try {
            if (!file.exists()) {
                boolean created = this.file.createNewFile();
            }
        } catch (Exception e) {
            Log.e("mike", "cannot crate history file");
        }
    }

    private int getHistoryValue() {
        DataInputStream is = null;
        int value = 0;
        try {
            is = new DataInputStream(new FileInputStream(file));
            value = is.readInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    private void putHistoryValue(int value) {
        try {
            DataOutputStream os = new DataOutputStream(new FileOutputStream(file));
            os.writeInt(value);
            os.flush();
        }
        catch (Exception e) {
            Log.e("mike", "File not found!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("mike", "Activity created");
        initHistoryFile();
        out = findViewById(R.id.out);
        clickMe = findViewById(R.id.clickMe);
        updateNumValue(getHistoryValue());
        clickMe.setOnClickListener(v -> updateNumValue(getNumValue() + 1));
    }

    @Override
    protected void onStop() {
        super.onStop();
        putHistoryValue(getNumValue());
        Log.d("mike", "Activity stopped");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("mike", "Activity paused");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("mike", "Activity resumed");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("mike", "Activity destroyed");
    }

    private void updateNumValue(int value) {
        String res = String.valueOf(value);
        out.setText(res);
        Log.d("mike", "Value updated to " + res);
    }

    private int getNumValue() {
        int value = 0;
        try {
            value = Integer.parseInt(out.getText().toString());
        } catch (Exception ignored) {
            Log.e("mike", "Cannot parse out field! Should be digit");
        }
        return value;
    }
}
