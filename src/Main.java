import model.Client;
import model.Clinica;
import model.Medic;
import model.Programare;
import service.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        //sunt create clinicile


        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spital", "root", "habarnam2511");
            //adaugam medicii in lista corespunzatoare clinicii la care lucreaza


            Client StefanAndrei = new Client("Stefan Andrei", 1, 16, 'M', "Endocrinologie");

            Client VaduvaMaria = new Client("Vaduva Maria", 2, 22, 'F', "Ginecologie");

            Client OpreaVasile = new Client("Oprea Vasile", 3, 67, 'M', "Cardiologie");

            Client MarinescuAlina = new Client("Marinescu Alina", 4, 42, 'F', "Oftalmologie");


            ClientService clientService = new ClientServiceImp();
            ClinicaService clinicaService = new ClinicaServiceImp();
            MedicService medicService = new MedicServiceImp();
            ProgramareService programareService = new ProgramareServiceImp();

            clinicaService.create("Strada Drumul Taberei nr. 107", 1);
            clinicaService.create("Soseaua Berceni nr.12", 2);
            clinicaService.create("Splaiul Unirii nr 220", 3);
            Clinica jk = new Clinica("Str Ion Barbu", 4);


            medicService.getAll();


            medicService.getAll();
            medicService.create("Andrei Suta",7,"oftalmologie",'M', 23, jk);
            programareService.create(1, 1, 1, "20.05.2020", 13.00);
            boolean meniu = true;
            int optiune;
            int navigare;
            int p;
            System.out.println(clinicaService.all());

            do {
                System.out.println("Buna ziua! Sunteti client sau administrator?");
                System.out.println("1.Client");
                System.out.println("2.Administrator");
                System.out.println("3.Iesire");
                optiune = scanner.nextInt();


                System.out.println("\n");

                switch (optiune) {
                    case 1:
                        optiune = 1;
                        System.out.println("Alegeti o actiune: \n");
                        System.out.println("1.Faceti o programare");
                        System.out.println("2.Modificati o programare");
                        System.out.println("3.Anulati o programare");
                        System.out.println("4.Prima data cand va programati? Introduceti datele dumneavoastra");
                        System.out.println("5.Iesire");
                        navigare = scanner.nextInt();

                        switch (navigare) {
                            case 1 -> {
                                System.out.println("Introduceti ID-ul dumneavoastra \n");
                                int id = scanner.nextInt();
                                scanner.nextLine();
                                medicService.getAll();
                                System.out.println();
                                System.out.println("Introduceti ID-ul medicului la care doriti sa va programati");
                                int id_medic = scanner.nextInt();
                                scanner.nextLine();
                                Set<Medic> medici = medicService.all();
                                boolean ok = false;
                                for (Medic medic : medici) {
                                    if (medic.getId() == id_medic) {
                                        ok = true;
                                        break;
                                    }
                                }

                                if (ok) {
                                    System.out.println("Introduceti data");
                                    String data = scanner.nextLine();
                                    System.out.println("Introduceti ora");
                                    double ora = scanner.nextDouble();
                                    boolean x = true;
                                    int maxid = 0;
                                    Set<Programare> programari = programareService.all();
                                    for (Programare programare : programari) {
                                        if (programare.getId() > maxid) {
                                            maxid = programare.getId();
                                        }
                                        if (programare.getData().equals(data) && programare.getMedic() == id_medic) {
                                            if (programare.getOra() - 1.30 < ora || ora - 1.30 < programare.getOra()) {
                                                x = false;
                                            }
                                        }
                                    }
                                    if (x) {
                                        programareService.create(maxid + 1, id_medic, id, data, ora);
                                        System.out.println("Programarea a fost creata cu succes");
                                        System.out.println("Va multumim!");
                                        break;
                                    }
                                    if (!x) {
                                        System.out.println("Incercati alte date pentru programare!");
                                    }
                                }
                                if (!ok) {
                                    System.out.println("Nu exista medic cu ID-ul introdus, incercati din nou");
                                }

                            }

                            case 2 -> {
                                System.out.println("Introduceti ID-ul programarii");
                                int id_programare = scanner.nextInt();
                                Set<Programare> programari = programareService.all();
                                int medic_programare = 0;
                                String data_programare = " ";
                                for (Programare programare : programari) {
                                    if (id_programare == programare.getId()) {
                                        medic_programare = programare.getMedic();
                                        data_programare = programare.getData();
                                    }

                                }
                                System.out.println("Introduceti ora:");
                                double ora_programare = scanner.nextDouble();
                                boolean x = true;
                                for (Programare programare : programari) {
                                    if (programare.getMedic() == medic_programare && programare.getData().equals(data_programare) && programare.getId() != id_programare)
                                        if (programare.getOra() - 1.30 < ora_programare || ora_programare - 1.30 < programare.getOra()) {
                                            x = false;
                                        }
                                }
                                if (x) {
                                    programareService.update_id(id_programare, ora_programare);
                                    System.out.println("Programarea a fost modificata cu succes!");
                                }
                                if (!x) {
                                    System.out.println("Nu putem face modificarea ceruta! Incercati o alta ora");
                                }

                            }

                            case 3 -> {
                                System.out.println("Introduceti ID-ul");
                                int id_anulare = scanner.nextInt();
                                programareService.delete_id(id_anulare);
                                System.out.println("Programarea a fost anulata cu succes!");
                            }
                            case 4 -> {
                                Set<Client> clienti = clientService.all();
                                int maxid_client = 0;
                                for (Client client : clienti) {
                                    if (client.getId() > maxid_client) {
                                        maxid_client = client.getId();
                                    }
                                }
                                scanner.nextLine();
                                System.out.println("Introduceti numele");
                                String nume = scanner.nextLine();
                                System.out.println("Introduceti varsta");
                                int varsta = scanner.nextInt();
                                System.out.println("Introdugeti genul");
                                char sex = scanner.next().charAt(0);
                                scanner.nextLine();
                                System.out.println("Introduceti sepcializarea de care aveti nevoie");
                                String spec = scanner.nextLine();
                                clientService.create(nume, maxid_client + 1, varsta, sex, spec);
                                System.out.println("Contul dumneavoastra a fost creeat");

                            }
                            case 5 -> {
                                System.out.println("O zi buna!");
                                System.exit(0);
                            }
                        }
                        break;
                    case 2:

                        System.out.println("Alegeti o actiune \n");
                        System.out.println("1.Creare");
                        System.out.println("2.Afisare");
                        System.out.println("3.Modificare");
                        System.out.println("4.Stergere");
                        System.out.println("5.Iesire");
                        p = scanner.nextInt();
                        switch (p) {
                            case 1:
                                System.out.println("1.Client");
                                System.out.println("2.Medic");
                                System.out.println("3.Programare");
                                System.out.println("4.Iesire");
                                int y = scanner.nextInt();
                                scanner.nextLine();
                                switch (y) {
                                    case 1 -> {
                                        Set<Client> clienti = clientService.all();
                                        int maxid_client = 0;
                                        for (Client client : clienti) {
                                            if (client.getId() > maxid_client) {
                                                maxid_client = client.getId();
                                            }
                                        }
                                        System.out.println("Introduceti numele");
                                        String nume = scanner.nextLine();
                                        System.out.println("Introduceti varsta");
                                        int varsta = scanner.nextInt();
                                        System.out.println("Introdugeti genul");
                                        char sex = scanner.next().charAt(0);
                                        scanner.nextLine();
                                        System.out.println("Introduceti sepcializarea de care aveti nevoie");
                                        String spec = scanner.nextLine();
                                        clientService.create(nume, maxid_client + 1, varsta, sex, spec);
                                        System.out.println("Clientul a fost adaugat!");
                                    }
                                    case 2 -> {
                                        Set<Medic> medici = medicService.all();
                                        int maxid_medic = 0;
                                        for (Medic medic : medici) {
                                            if (medic.getId() > maxid_medic) {
                                                maxid_medic = medic.getId();
                                            }
                                        }
                                        System.out.println("Introduceti numele:");
                                        String nume_medic = scanner.nextLine();
                                        System.out.println("Introduceti specializarea:");
                                        String specializare = scanner.nextLine();
                                        System.out.println("Introduceti genul");
                                        char sex_medic = scanner.next().charAt(0);
                                        System.out.println("Introduceti anii de experienta");
                                        int ani = scanner.nextInt();
                                        System.out.println("Introduceti ID-ul clinicii");
                                        int clinica = scanner.nextInt();
                                        ArrayList<Clinica> clinici = clinicaService.all();
                                        for (Clinica clinicaa : clinici) {
                                            if (clinicaa.getId() == clinica) {
                                                medicService.create(nume_medic, maxid_medic + 1, specializare, sex_medic, ani, clinicaa);
                                                System.out.println("Medicul a fost adaugat cu succes!");
                                            }
                                        }
                                    }
                                    case 3 -> {
                                        System.out.println("Introduceti ID-ul clientului");
                                        int id_c = scanner.nextInt();
                                        System.out.println("Introduceti ID-ul medicului");
                                        int id_m = scanner.nextInt();
                                        scanner.nextLine();
                                        System.out.println("Introduceti data");
                                        String data = scanner.nextLine();
                                        System.out.println("Introduceti ora");
                                        double ora = scanner.nextDouble();
                                        boolean x = true;
                                        int maxid = 0;
                                        Set<Programare> programari = programareService.all();
                                        for (Programare programare : programari) {
                                            if (programare.getId() > maxid) {
                                                maxid = programare.getId();
                                            }
                                            if (programare.getData().equals(data) && programare.getMedic() == id_m) {
                                                if (programare.getOra() - 1.30 < ora || ora - 1.30 < programare.getOra()) {
                                                    x = false;
                                                }
                                            }
                                        }
                                        if (x) {
                                            programareService.create(maxid + 1, id_m, id_c, data, ora);
                                            System.out.println("Programarea a fost creata cu succes");
                                        }
                                        if (!x) {
                                            System.out.println("Incercati alte date pentru programare!");
                                        }
                                    }

                                }
                                break;
                            case 2:
                                System.out.println("1.Client");
                                System.out.println("2.Medic");
                                System.out.println("3.Programare");
                                int c = scanner.nextInt();
                                switch (c) {
                                    case 1 -> clientService.getAll();
                                    case 2 -> medicService.getAll();
                                    case 3 -> programareService.getAll();
                                }
                                break;
                            case 3:
                                System.out.println("1.Client");
                                System.out.println("2.Medic");
                                System.out.println("3.Programare");
                            case 4:
                                System.out.println("1.Client");
                                System.out.println("2.Medic");
                                System.out.println("3.Programare");
                                int o = scanner.nextInt();
                                switch (o) {
                                    case 1 -> {
                                        System.out.println("Introduceti ID-ul clientului pe care doriti sa il stergeti");
                                        int id = scanner.nextInt();
                                        clientService.delete_id(id);
                                        System.out.println("Clientul a fost sters");
                                    }
                                    case 2 -> {
                                        System.out.println("Introduceti ID-ul medicului pe care doriti sa il stergeti");
                                        int id_medic = scanner.nextInt();
                                        medicService.delete_id(id_medic);
                                        System.out.println("Medicul a fost sters");
                                    }
                                    case 3 -> {
                                        System.out.println("Introduceti ID-ul programarii pe care doriti sa o stergeti");
                                        int id_p = scanner.nextInt();
                                        programareService.delete_id(id_p);
                                        System.out.println("Programarea a fost stearsa");

                                    }

                                }
                                break;
                            case 5:
                                System.out.println("La revedere!");
                                System.exit(0);
                        }

                    case 3:
                        System.out.println("O zi buna!");
                        break;

                }
            } while (optiune != 3);

        conn.close();

        } catch (SQLException e) {
            System.out.println("Nu azi bro");
        }


    }
}
