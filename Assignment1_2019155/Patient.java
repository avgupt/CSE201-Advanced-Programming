public class Patient {

    String name;
    private final int age, id, oxygen_level;
    private final float body_temp;
    boolean is_admitted; // admitted or not
    private Hospital hospital; // hospital in which patient is admitted
    int recovery_days;

    Patient(String name, int age, int oxygen_level, float body_temp, int id) {
        
        this.name = name;
        this.age = age;
        this.oxygen_level = oxygen_level;
        this.body_temp = body_temp;
        this.id = id;   // id would be asssigned by the camp in which patient is admitted
        
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

    int get_oxygen_level() {
        return oxygen_level;
    }

    float get_body_temp() {
        return body_temp;
    }

    Hospital get_hospital() {
        return hospital;
    }


}