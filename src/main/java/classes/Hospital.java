package classes;

import exceptions.DuplicateInfoException;

import java.io.Serializable;
import java.util.LinkedList;

public class Hospital implements Serializable {
    private static final long serialVersionUID = 4L;
    private final int id;
    private String name;
    private LinkedList<Section> sections = new LinkedList<>();

    public Hospital(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public LinkedList<Section> getSections() { return sections; }

    public void listSections() {
        for (Section section : sections) {
            System.out.println(section);
        }
    }

    public Section getSection(int id) {
        Section tmp_section = null;
        for (Section section : sections) {
            if (section.getId() == id) {
                tmp_section = section;
            }
        }
        if (tmp_section == null) {
            System.out.println("This section does not exist.");
        }
        return tmp_section;
    }

    public Section getSection(String name) {
        Section tmp_section = null;
        for (Section section : sections) {
            if (section.getName().equals(name)) {
                tmp_section = section;
            }
        }
        if (tmp_section == null) {
            System.out.println("This section does not exist.");
        }
        return tmp_section;
    }

    public synchronized void addSection(Section section) throws DuplicateInfoException {
        for (Section existing_section : sections) {
            if (existing_section.equals(section)) {
                throw new DuplicateInfoException("This section already exist.");
            }
        }
        sections.add(section);
    }
}
