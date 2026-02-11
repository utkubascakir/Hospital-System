package classes;

import java.io.Serializable;

public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private final long national_id;

    public Person(long national_id, String name) {
        this.national_id = national_id;
        this.name = name;
    }

    public String getName() { return name; }
    public long getNational_id() { return national_id; }

    @Override
    public String toString() {
        return "Name: " + getName() + ", National ID: " + getNational_id();
    }
}
