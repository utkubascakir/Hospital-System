package classes;

public class Doctor extends Person {
    private final int diploma_id;
    private Schedule schedule;

    public Doctor(long national_id, String name, int diploma_id, int maxPatientPerDay) {
        super(national_id, name);
        this.diploma_id = diploma_id;
        this.schedule = new Schedule(maxPatientPerDay);
    }

    public int getDiploma_id() { return diploma_id; }
    public Schedule getSchedule() { return schedule; }

    @Override
    public String toString() {
        return "Name: " + getName() + "\nNational ID: " + getNational_id() + "\nDiploma ID: " + getDiploma_id();
    }
}
