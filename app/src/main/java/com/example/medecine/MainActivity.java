package com.example.medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<WorkSchedule> workSchedules = new ArrayList<>();
    private ArrayList<Meetings> meetings = new ArrayList<>();
    private String works_schedules_file_name = "work_schedules.csv";
    private String meetings_file_name = "meetings.csv";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        write_data();
        
        read_data();

        Button add_meet = findViewById(R.id.add_meet_btn);
        add_meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddMeet.class);
                startActivity(intent);
            }
        });
        Toast.makeText(this, "Ok!", Toast.LENGTH_LONG).show();


    }

    private void write_data() {
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(meetings_file_name, MODE_PRIVATE)));
            bw.write("f_name;s_name;l_name;id;\n");

            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(works_schedules_file_name, MODE_PRIVATE)));
            bw.write(   "1;name;work;date;time;\n" +
                            "2;Василий;Хирург;19.03.2021;12:30;\n"+
                            "3;Stephan;Dentist;19.03.2021;19:00;\n"+
                            "4;Василий;Хирург;19.03.2021;13:30;\n" +
                            "5;Василий;Хирург;20.03.2021;13:30;\n");

            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void read_data() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(meetings_file_name)));
            String line;
            try {
                while ((line = br.readLine()) != null){
                    meetings.add(new Meetings(line.split(";")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(works_schedules_file_name)));
            String line;
            try {
                while ((line = br.readLine()) != null){
                    workSchedules.add(new WorkSchedule(line.split(";")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
