import java.util.*;

public class application {

    static Scanner in = new Scanner(System.in);

    static void query_1 (Camp c) {
        String name = in.next();
        
        System.out.println("Temperature Criteria - ");
        float temp = in.nextFloat();

        System.out.println("Oxygen Levels -");
        int oxygen = in.nextInt();

        System.out.println("Number of Available beds - ");
        int available_beds = in.nextInt();

        Hospital h = new Hospital(name, temp, oxygen, available_beds);
        display_hospital_details(h);
        h = c.add_hospital(h, name);
        for (Patient p : h.get_patients()) {
            System.out.println("Recovery days for admitted patient ID " + p.get_id());
            int recovery_days = in.nextInt();
            p.set_recovery_days(recovery_days);
        }
        
    }

    static void query_2(Camp c) {
        List<Patient> patients = c.remove_admitted_patients();
        System.out.println("Account ID removed of admitted patients");
        for (Patient p : patients) {
            System.out.println(p.get_id());
        }
    }

    static void query_3(Camp c) {
        List<Hospital> list = c.remove_closed_hospitals();
        System.out.println("Accounts removed of Institute whose admission is closed");
        for (Hospital h : list) System.out.println(h.get_name());
    }

    static void query_4(Camp c) {
        Collection<Patient> patients = c.get_patients();
        System.out.println(patients.size() + " patient(s)");
    }

    static void query_5(Camp c) {
        List<Hospital> hospitals = c.get_open_hospitals();
        System.out.println(hospitals.size() + " intitute(s) is/are admitting patients currently");
    }

    static void query_6(Camp c) {
        String name  = in.next(); // assuming institute name is of one word
        display_hospital_details(c.get_hospital(name));
    }

    static void query_7(Camp c) {
        int id = in.nextInt();
        Patient p = c.get_patient_by_id(id);
        System.out.println(p.get_name());
        System.out.println("Temperature is " + p.get_body_temp());
        System.out.println("Oxygen level is " + p.get_oxygen_level());
        
        String admission_status;
        if (p.is_admitted) admission_status = "Admitted";
        else admission_status = "Not Admitted";
        System.out.println("Admission Status - " + admission_status);

        if (p.is_admitted) System.out.println("Admitting Institute - " + p.get_hospital().get_name());
    }

    static void query_8(Camp c) {
        for (Patient p : c.get_patients()) System.out.println(p.get_id() + " " + p.get_name());
        for (Patient p : c.get_admitted_patients()) System.out.println(p.get_id() + " " + p.get_name());
    }

    static void query_9(Camp c) {
        String name = in.next();
        Hospital h = c.get_hospital(name);
        for (Patient p : h.get_patients()) {
            System.out.println(p.name + ", recovery time is " + p.get_recovery_days() + " days");
        }
    }

    static void display_hospital_details(Hospital h) {
        System.out.println(h.get_name());
        System.out.println("Temperature should be <= " + h.get_temp_criteria());
        System.out.println("Oxygen should be >= " + h.get_oxygen_criteria());
        System.out.println("Number of beds available - " + h.get_available_beds());
        if (h.get_status()) System.out.println("Admission Status - OPEN");
        else System.out.println("Admission Status - CLOSED");
    }
    public static void main(String[] args) {
        
        Camp c = new Camp();
        
        int patient_num = in.nextInt();
        for (int i = 0; i < patient_num; i++) {
            String name = in.next(); // assuming that name is of one word
            float temp = in.nextFloat();
            int oxygen = in.nextInt(); 
            int age = in.nextInt();
            Patient p = new Patient(name, age, oxygen, temp, c.get_patient_id());
            c.add_patient(p);
        }

        while (c.get_patients().size() > 0) {
            int query = in.nextInt();
            switch(query) {
                case 1:
                    query_1(c);
                    break;
                case 2:
                    query_2(c);
                    break;
                case 3:
                    query_3(c);
                    break;
                case 4:
                    query_4(c);
                    break;
                case 5:
                    query_5(c);
                    break;
                case 6:
                    query_6(c);
                    break;
                case 7:
                    query_7(c);
                    break;
                case 8:
                    query_8(c);
                    break;
                case 9:
                    query_9(c);
                    break;
            }
        }
        
    }
}