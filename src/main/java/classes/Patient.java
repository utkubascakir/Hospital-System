package classes;

public class Patient extends Person {
    private static final long serialVersionUID = 1L;
    private String password;

    public Patient(long national_id, String name, String password) {
        super(national_id, name);
        this.password = password;
    }

    public String getPassword() { return password; }
}
