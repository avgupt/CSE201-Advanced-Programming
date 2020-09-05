import java.util.*;
import java.io.*;
import java.lang.*;

class Camp {
    private List<Patient> not_admitted_patients;
    private List<Patient> admitted_patients;
    private HashMap<String, Hospital> hospitals; 

    Camp() {
        not_admitted_patients = new ArrayList<Patient>();
        admitted_patients = new ArrayList<Patient>();
        hospitals = new HashMap<String, Hospital>();
    }

    Hospital add_hospital(String name, int oxygen_level, int body_temp, int available_beds) {
        Hospital h = new Hospital(name, body_temp, oxygen_level, available_beds);
        hospitals.put(name, h);
        display_hospital_details(h);
        admit_patients(h);
        return h;
    }

    void display_hospital_details(Hospital h) {
        System.out.println(h.get_name());
        System.out.println("Temperature should be <= " + h.get_temp_criteria());
        System.out.println("Oxygen should be >= " + h.get_oxygen_criteria());
        if (h.get_status()) System.out.println("Admission Status - OPEN");
        else System.out.println("Admission Status - CLOSED");
    }

    void admit_patients (Hospital h) {
        
        int index = 0;
        while (index < not_admitted_patients.size() && h.get_status()) {
            Patient p = not_admitted_patients.get(index);
            if (p.get_oxygen_level() >= h.get_oxygen_criteria()) {
                h.add_patient(p);
                admitted_patients.add(p);
                not_admitted_patients.remove(index);
            }
            else index++;
        }

        index = 0;
        while (index < not_admitted_patients.size() && h.get_status()) {
            Patient p = not_admitted_patients.get(index);
            if (p.get_body_temp() <= h.get_temp_criteria()) {
                h.add_patient(p);
                admitted_patients.add(p);
                not_admitted_patients.remove(index);
            }
            else index++;
        }
    }

    void remove_admitted_patients() {
        admitted_patients.clear();
        for (Hospital h : hospitals.values()) {
            h.remove_patients();
        }
    }
}

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

    int get_recovery_days() {
        return recovery_days;
    }

    int get_id() {
        return id;
    }

    String get_name() {
        return name;
    }

    int get_age() {
        return age;
    }

    float get_oxygen_level() {
        return oxygen_level;
    }

    float get_body_temp() {
        return body_temp;
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

        if (available_beds > 0) status = true;
        else status = false;
    }

    float get_oxygen_criteria() {
        return this.oxygen_criteria;
    }

    float get_temp_criteria() {
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
        p.hospitalize(this);
        occupy_bed();
    }

    void remove_patients() {
        patients.clear();
    }

    void display_patient_info() {
        for (Patient p : patients) {
            System.out.println(p.name + ", recovery time is " + p.get_recovery_days() + " days");
        }
    }

}


public class assignment1 {


    public static void main(String[] args) {
        
    }
}