package classes;

import javax.swing.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

public class Schedule implements Serializable {
    private static final long serialVersionUID = 3L;
    private int maxPatientPerDay;
    private LinkedList<Rendezvous> sessions = new LinkedList<>();

    public Schedule(int maxPatientPerDay) {
        this.maxPatientPerDay = maxPatientPerDay;
    }

    public LinkedList<Rendezvous> getSessions() { return sessions; }
    public int getMaxPatientPerDay() { return maxPatientPerDay; }

    public void listSchedule() {
        for (Rendezvous rendezvous : sessions) {
            System.out.println(rendezvous);
        }
    }

    public void writeSchedule(JTextArea textArea) {
        for (Rendezvous rendezvous : sessions) {
            textArea.append(String.valueOf(rendezvous));
            textArea.append("\n");
        }
    }

    public Rendezvous getRendezvous(Patient p, Date desired) {
        Rendezvous tmp_rendezvous = null;
        for (Rendezvous rendezvous : sessions) {
            if (rendezvous.getDateTime().equals(desired) && rendezvous.getPatient().equals(p)) {
                tmp_rendezvous = rendezvous;
            }
        }
        if (tmp_rendezvous == null) {
            System.out.println("This rendezvous did not found.");
        }
        return tmp_rendezvous;
    }

    public synchronized boolean addRendezvous(Patient p, Date desired) {
        Calendar desiredDate = Calendar.getInstance();
        desiredDate.setTime(desired);

        int rendezvousCountThatDay = 0;
        for (Rendezvous rendezvous : sessions) {
            Calendar existingDate = Calendar.getInstance();
            existingDate.setTime(rendezvous.getDateTime());

            if (existingDate.get(Calendar.YEAR) == desiredDate.get(Calendar.YEAR) &&
                    existingDate.get(Calendar.DAY_OF_YEAR) == desiredDate.get(Calendar.DAY_OF_YEAR)) {
                rendezvousCountThatDay++;
                if (existingDate.get(Calendar.HOUR_OF_DAY) == desiredDate.get(Calendar.HOUR_OF_DAY) &&
                        existingDate.get(Calendar.MINUTE) == desiredDate.get(Calendar.MINUTE)) {
                    System.out.println("The doctor is unavailable at the desired time.");
                    return false;
                }
            }
        }

        if (rendezvousCountThatDay >= maxPatientPerDay) {
            System.out.println("The doctor has reached the maximum number of appointments for the day.");
            return false;
        }

        Rendezvous newRendezvous = new Rendezvous(desired, p);
        sessions.add(newRendezvous);
        return true;
    }
}
