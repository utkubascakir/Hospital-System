package classes;

import gui.CLI;
import gui.GUI;

import java.io.File;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    public static String fullPath = "tables.dat";

    public static void main(String[] args) {
        CRS crs = new CRS();
        CLI cli = new CLI(crs);
        crs.loadTablesToDisk(fullPath);
        initializeDefaultData(crs, fullPath);

        System.out.println("1-Run Program in Console");
        System.out.println("2-Open GUI");
        System.out.print("Enter your choice: ");
        int bootChoice = scanner.nextInt();
        scanner.nextLine();

        switch (bootChoice) {
            case 1:
                cli.openCLI();
                crs.saveTablesToDisk(fullPath);
                break;
            case 2:
                new GUI(crs);
                crs.saveTablesToDisk(fullPath);
                break;
            default:
                System.out.println("Invalid choice.");
                break;
        }
    }

    public static void initializeDefaultData(CRS crs, String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("Data file already exists. Skipping initialization.");
            return;
        }

        System.out.println("Data file not found. Initializing default hospitals, sections, and doctors...");

        Hospital devletHastanesi = new Hospital(34000, "State Hospital");
        Section neurology = new Section(34001, "Neurology");
        Section cardiology = new Section(34002, "Cardiology");
        devletHastanesi.addSection(neurology);
        devletHastanesi.addSection(cardiology);

        Doctor dr1 = new Doctor(123123123, "Türker Kılıç", 111, 6);
        Doctor dr2 = new Doctor(456456456, "Ayşe Kılıç", 222, 10);
        neurology.addDoctor(dr1);
        cardiology.addDoctor(dr2);

        crs.getHospitals().put(devletHastanesi.getId(), devletHastanesi);

        Hospital universityHospital = new Hospital(34010, "University Hospital");
        Section pediatrics = new Section(34011, "Pediatrics");
        Section orthopedics = new Section(34012, "Orthopedics");
        universityHospital.addSection(pediatrics);
        universityHospital.addSection(orthopedics);

        Doctor dr3 = new Doctor(789789789, "Ali Vural", 333, 8);
        Doctor dr4 = new Doctor(987987987, "Ece Arslan", 444, 12);
        pediatrics.addDoctor(dr3);
        orthopedics.addDoctor(dr4);

        crs.getHospitals().put(universityHospital.getId(), universityHospital);

        Hospital privateHospital = new Hospital(34020, "Private Hospital");
        Section dermatology = new Section(34021, "Dermatology");
        Section radiology = new Section(34022, "Radiology");
        privateHospital.addSection(dermatology);
        privateHospital.addSection(radiology);

        Doctor dr5 = new Doctor(741741741, "Melis Yılmaz", 555, 7);
        Doctor dr6 = new Doctor(852852852, "Mehmet Yıldız", 666, 9);
        dermatology.addDoctor(dr5);
        radiology.addDoctor(dr6);

        crs.getHospitals().put(privateHospital.getId(), privateHospital);

        crs.saveTablesToDisk(filePath);
        System.out.println("Default data initialized and saved.");
    }

}
