package com.example.medecine;

public class WorkSchedule {
    private int id;
    private String doctor_name;
    private String doctor_profile;
    private String date;
    private String time;


    WorkSchedule(String[] line){
        try {
            id = Integer.parseInt(line[0]);
        }
        catch (NumberFormatException e)
        {
            id = -666;
        }
        doctor_name = line[1];
        doctor_profile = line[2];
        date = line[3];
        time = line [4];
    }

    public int getId(){return id;}

    public String getDoctor_name(){return doctor_name;}

    public String getDoctor_profile(){return doctor_profile;}

    public String getDate(){return date;}

    public String getTime(){return time;}

    public String getByIndex(int index){
        switch (index){
            case 1:
                return doctor_profile;
            case 2:
                return doctor_name;
            case 3:
                return date;
            case 4:
                return time;
            default:
                return "ERROR";
        }

    }

    public Boolean contains(String item){
        return doctor_name.equals(item) ||
                doctor_profile.equals(item) ||
                date.equals(item) ||
                time.equals(item);
    }
}
