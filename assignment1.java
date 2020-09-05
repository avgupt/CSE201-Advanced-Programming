import java.util.*;
import java.io.*;
import java.lang.*;

class Camp {
    private HashMap<Integer, Patient> patients;
    // private List<Patient> admitted_patients;
    private static HashMap<String, Hospital> hospitals; // other camps may add hospitals and the total hospitals remain same for every camp
    private int patient_num;

    Camp() {
        patients = new HashMap<Integer, Patient>();
        // admitted_patients = new ArrayList<Patient>();
        hospitals = new HashMap<String, Hospital>();
    }

    Hospital add_hospital(String name, float oxygen_level, float body_temp, int available_beds) {
        Hospital h = new Hospital(name, body_temp, oxygen_level, available_beds);
        hospitals.put(name, h);
        display_hospital_details(h);
        hospitalise_patients(h);
        return h;
    }

    void display_hospital_details(Hospital h) {
        System.out.println(h.get_name());
        System.out.println("Temperature should be <= " + h.get_temp_criteria());
        System.out.println("Oxygen should be >= " + h.get_oxygen_criteria());
        if (h.get_status()) System.out.println("Admission Status - OPEN");
        else System.out.println("Admission Status - CLOSED");
    }

    void hospitalise_patients(Hospital h) {

        List<Integer> ids = new ArrayList<Integer>(); 
        for (Patient p : patients.values()) {
            if (!h.get_status()) break;
            if (p.get_oxygen_level() >= h.get_oxygen_criteria()) {
                h.add_patient(p);
                // admitted_patients.add(p);
                ids.add(p.get_id());
            }
        }

        for (Patient p : patients.values()) {
            if (!h.get_status()) break;
            if (p.get_body_temp() <= h.get_temp_criteria() && !p.is_admitted) {
                h.add_patient(p);
                // admitted_patients.add(p);
                ids.add(p.get_id());
            }
        }

        for (Integer i : ids) {
            patients.remove(i);
        }
    }

    List<Patient> remove_admitted_patients() {
        List<Patient> output = new ArrayList<Patient>();
        // admitted_patients.clear();
        for (Hospital h : hospitals.values()) {
            output.addAll(h.get_patients());
            h.remove_patients();
        }
        return output;
    }

    List<Hospital> remove_closed_hospitals() {
        List<Hospital> closedHospitals = new ArrayList<Hospital>();

        for (Hospital h : hospitals.values()) {
            if (!h.get_status()) closedHospitals.add(h);
        }

        for (Hospital h : closedHospitals) {
            hospitals.remove(h.get_name());
        }

        return closedHospitals;
    }

    List<Hospital> get_open_hospitals() {
        List<Hospital> open = new ArrayList<Hospital>();

        for (Hospital h : hospitals.values()) {
            if (h.get_status()) open.add(h);
        }

        return open;
    }

    int get_patient_id() {
        patient_num++;
        return patient_num;
    }

    Collection<Patient> get_patients() {
        return patients.values();
    }

    Collection<Hospital> get_hospitals() {
        return hospitals.values();
    }

    Patient get_patient_by_id(int id) {
        return patients.get(Integer.valueOf(id));
    }

}

class Patient {

    String name;
    private final int age, id;
    private final float oxygen_level, body_temp;
    boolean is_admitted; // admitted or not
    private Hospital hospital; // hospital in which patient is admitted
    int recovery_days;

    Patient(String name, int age, float oxygen_level, float body_temp, int id) {
        
        this.name = name;
        this.age = age;
        this.oxygen_level = oxygen_level;
        this.body_temp = body_temp;
        this.id = id;
        
        is_admitted = false; // not admitted
    }

    void hospitalize(Hospital hospital) {
        if(is_admitted) return;
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

    List<Patient> get_patients() {
        return patients;
    }

    void display_patient_info() {
        for (Patient p : patients) {
            System.out.println(p.name + ", recovery time is " + p.get_recovery_days() + " days");
        }
    }

}


public class assignment1 {

    Scanner in = new Scanner(System.in);

    void query_1 (Camp c) {
        String name = in.next();
        
        System.out.println("Temperature Criteria - ");
        float temp = in.nextFloat();

        System.out.println("Oxygen Levels -");
        float oxygen = in.nextFloat();

        System.out.println("Number of Available beds - ");
        int available_beds = in.nextInt();

        Hospital h = c.add_hospital(name, oxygen, temp, available_beds);
        for (Patient p : h.get_patients()) {
            System.out.println("Recovery days for admitted patient ID " + p.get_id());
            int recovery_days = in.nextInt();
            p.set_recovery_days(recovery_days);
        }
        
    }

    void query_2(Camp c) {
        List<Patient> patients = c.remove_admitted_patients();
        System.out.println("Account ID removed of admitted patients");
        for (Patient p : patients) {
            System.out.println(p.get_id());
        }
    }
    public static void main(String[] args) {
        
    }
}