import java.util.*;

public class Camp {
    private HashMap<Integer, Patient> patients;
    private HashMap<Integer, Patient> admitted_patients;
    private static HashMap<String, Hospital> hospitals; // other camps may add hospitals and the total hospitals remain same for every camp
    private int patient_num;

    Camp() {
        patients = new HashMap<Integer, Patient>();
        admitted_patients = new HashMap<Integer, Patient>();
        hospitals = new HashMap<String, Hospital>();
    }

    void add_patient(Patient p) {
        patients.put(p.get_id(), p);
    }

    Hospital add_hospital(Hospital h, String name) {
        hospitals.put(name, h);
        hospitalise_patients(h);
        return h;
    }


    void hospitalise_patients(Hospital h) {

        List<Integer> ids = new ArrayList<Integer>(); 
        for (Patient p : patients.values()) {
            if (!h.get_status()) break;
            if (p.get_oxygen_level() >= h.get_oxygen_criteria()) {
                h.add_patient(p);
                admitted_patients.put(Integer.valueOf(p.get_id()), p);
                ids.add(p.get_id());
            }
        }

        for (Patient p : patients.values()) {
            if (!h.get_status()) break;
            if (p.get_body_temp() <= h.get_temp_criteria() && !p.is_admitted) {
                h.add_patient(p);
                admitted_patients.put(Integer.valueOf(p.get_id()), p);
                ids.add(p.get_id());
            }
        }

        for (Integer i : ids) {
            patients.remove(i);
        }
    }

    List<Patient> remove_admitted_patients() {
        List<Patient> output = new ArrayList<Patient>();
        admitted_patients.clear();
        for (Hospital h : hospitals.values()) {
            output.addAll(h.get_patients());
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

    Hospital get_hospital(String name) {
        return hospitals.get(name);
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
        if (patients.containsKey(Integer.valueOf(id))) return patients.get(Integer.valueOf(id));
        return admitted_patients.get(Integer.valueOf(id));
    }

}