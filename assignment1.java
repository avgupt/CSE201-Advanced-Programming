import java.util.*;
import java.io.*;
import java.lang.*;

class Patient {

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
    }

    int get_oxygen_criteria() {
        return this.oxygen_criteria;
    }

    int get_temp_criteria() {
        return this.temp_criteria;
    }

    void occupy_bed() {
        this.available_beds--;
    }

    void add_patient(Patient p) {
        this.patients.add(p);
    }

}


public class assignment1 {


    public static void main(String[] args) {
        
    }
}