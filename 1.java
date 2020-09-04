import java.util.*;
import java.io.*;
import java.lang.*;

class Patient {

}

class Hospital {
    
    private string name;
    private float oxygen_criteria, temp_criteria;
    private bool status; // true if open false if closed
    private int available_beds;
    private List<Patient> patients;

    Hospital (string name, float temp_criteria, float oxygen_criteria, int available_beds) {
        
        this.name = name;
        this.temp_criteria = temp_criteria;
        this.oxygen_criteria = oxygen_criteria;
        this.available_beds = available_beds;

        patients = new ArrayList<Patient>();
    }

}


public class 1 {


    public static void main(String[] args) {
        
    }
}