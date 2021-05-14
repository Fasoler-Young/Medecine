package com.example.medecine;

public class Meetings {
    private String first_name;
    private String second_name;
    private String last_name;
    private int work_id;

    Meetings(String f_name, String s_name, String l_name, int id){
        first_name = f_name;
        second_name = s_name;
        last_name = l_name;
        work_id = id;
    }

    Meetings(String[] line){
        first_name = line[0];
        second_name = line[1];
        last_name = line[2];
        try {
            work_id = Integer.parseInt(line[3]);
        }catch (NumberFormatException e)
        {
            work_id = -666;
        }
    }

    public String getFirst_name(){return first_name;}

    public String getSecond_name(){return second_name;}

    public String getLast_name(){return last_name;}

    public int getWork_id(){return work_id;}

    public String getFIO(){
        return first_name + " " + second_name + " " + last_name;
    }

}
