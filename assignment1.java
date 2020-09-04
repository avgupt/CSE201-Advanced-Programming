import java.util.*;
import java.io.*;
import java.lang.*;

class Patient {

    String name;
    private int age, id, recovery_days;
    private float oxygen_level, body_temp;
    private boolean is_admitted; // admitted or not
    private Hospital hospital; // hospital in which patient is admitted

    Patient(String name, int age, float oxygen_level, float body_temp, int id) {
        
        this.name = name;
        this.age = age;
        this.oxygen_level = oxygen_level;
        this.body_temp = body_temp;
        this.id = id;
        
        is_admitted = false; // not admitted
    }

    void hospitalize(Hospital hospital) {
        this.hospital = hospital;
        is_admitted = true;
    }

    void set_recovery_days(int recovery_days) {
        this.recovery_days = recovery_days;
    }

    void display_details() {
        System.out.println(name);
        System.out.println("Temperature is " + body_temp);
        System.out.println("Oxygen level is " + oxygen_level);
        
        String admission_status;
        if (is_admitted) admission_status = "Admitted";
        else admission_status = "Not Admitted";
        System.out.println("Admission Status - " + admission_status);

        if (is_admitted) System.out.println("Admitting Institute - " + hospital.get_name());
    }


}

class Hospital {
    
    private String name;
    private float oxygen_criteria, temp_criteria;
    private boolean status; // true if open false if closed
    private int available_beds;
    private List<Patient> patients;

    Hospital(String name, float temp_criteria, float oxygen_criteria, int available_beds) {
        
        this.name = name;
        this.temp_criteria = temp_criteria;
        this.oxygen_criteria = oxygen_criteria;
        this.available_beds = available_beds;

        patients = new ArrayList<Patient>();
        status = true;
    }

    int get_oxygen_criteria() {
        return this.oxygen_criteria;
    }

    int get_temp_criteria() {
        return this.temp_criteria;
    }

    String get_name() {
        return name;
    }

    boolean get_status() {
        return status;
    }

    void occupy_bed() {
        this.available_beds--;
        if (available_beds == 0) status = false;
    }

    void add_patient(Patient p) {
        this.patients.add(p);
        occupy_bed();
    }

}


public class assignment1 {


    public static void main(String[] args) {
        
    }
}