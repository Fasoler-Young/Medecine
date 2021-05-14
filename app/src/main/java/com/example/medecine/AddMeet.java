package com.example.medecine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AddMeet extends AppCompatActivity {

    private ArrayList<WorkSchedule> workSchedules = new ArrayList<>();
    private ArrayList<Meetings> meetings = new ArrayList<>();
    private String works_schedules_file_name = "work_schedules.csv";
    private String meetings_file_name = "meetings.csv";
    private String separator = ";";

    private String cur_prof, cur_doc, cur_date, cur_time;

    private EditText f_name, s_name, l_name;
    private Spinner prof_sp, name_sp, date_sp, time_sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meet);


        read_data();


        findView();



        Button save_meet = findViewById(R.id.save_meet_btn);
        save_meet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_data()){
                    add_meet();
                }else {
                    //Toast.makeText(AddMeet.this, prof_sp.getSourceLayoutResId(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void add_meet() {

        String line = f_name.getText() + separator + s_name.getText() + separator + l_name.getText() + '\n';

        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput(meetings_file_name, MODE_APPEND)));
            bw.write(line);

            bw.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        Intent intent = new Intent(AddMeet.this, MainActivity.class);
        startActivity(intent);
    }

    private void findView() {
        f_name = findViewById(R.id.first_name_txt);
        s_name = findViewById(R.id.second_name_txt);
        l_name = findViewById((R.id.last_name_txt));
        prof_sp = findViewById(R.id.prof_sp);

        name_sp = findViewById(R.id.name_sp);
        date_sp = findViewById(R.id.date_sp);
        time_sp = findViewById((R.id.time_sp));

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getColumn(workSchedules, 1) );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        prof_sp.setAdapter(adapter);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getColumn(workSchedules, 2) );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        name_sp.setAdapter(adapter2);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getColumn(workSchedules, 3) );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        date_sp.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getColumn(workSchedules, 4) );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        time_sp.setAdapter(adapter4);


        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Получаем выбранный объект
                String item = (String)parent.getItemAtPosition(position);
                //f_name.setText(Integer.toString(parent.getId()));
                //l_name.setText(item);
                //s_name.setText(Integer.toString(prof_sp.getId()));
                //refresh_adapters((String)parent.getItemAtPosition(position), parent.getId());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        time_sp.setOnItemSelectedListener(itemSelectedListener);
        prof_sp.setOnItemSelectedListener(itemSelectedListener);
        date_sp.setOnItemSelectedListener(itemSelectedListener);
        name_sp.setOnItemSelectedListener(itemSelectedListener);
    }

    private void refresh_adapters(String itemAtPosition, int id) {
        if(id != prof_sp.getId()){
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getColumn(1, itemAtPosition) );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            prof_sp.setAdapter(adapter);
            //TODO to others
        }
    }


    private ArrayList<String> getColumn(ArrayList<WorkSchedule> workSchedules, int i) {
        ArrayList<String> result = new ArrayList<>();
        result.add("Не выбрано");
        for(WorkSchedule workSchedule : workSchedules){
            if(result.indexOf(workSchedule.getByIndex(i)) < 0)
                result.add(workSchedule.getByIndex(i));
        }
        return result;
    }

    private ArrayList<String> getColumn(int i, String item){
        ArrayList<String> result = new ArrayList<>();
        result.add("Не выбрано");
        for(WorkSchedule workSchedule : workSchedules){
            if(result.indexOf(workSchedule.getByIndex(i)) < 0 && workSchedule.getByIndex(i).contains(item))
                result.add(workSchedule.getByIndex(i));
        }
        return result;
    }

    private boolean check_data() {
        return  f_name.getText().length() != 0 &&
                s_name.getText().length() != 0 &&
                l_name.getText().length() != 0;

    }

    private void read_data() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    openFileInput(works_schedules_file_name)));
            String line;
            try {
                while ((line = br.readLine()) != null){
                    workSchedules.add(new WorkSchedule(line.split(separator)));

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
