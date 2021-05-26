package service;

import model.Clinica;
import model.DirectorClinica;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ClinicaServiceImp implements ClinicaService {

    protected ArrayList<Clinica> clinici = new ArrayList<>();


    @Override
    public void getAll() {
        for(Clinica clinica:clinici){
            System.out.println(clinica.toString());
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Select_All_From_Clinica", timestamp);
    }

    @Override
    public Clinica save(Clinica obiect) {
        clinici.add(obiect);
        return obiect;
    }

    @Override
    public void delete(Clinica obiect) {
        clinici.remove(obiect);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Delete_Clinica", timestamp);
    }

    @Override
    public void delete_id(int id) {
        for(Clinica clinica:clinici){
            if(clinica.getId() == id){
                delete(clinica);
            }
        }
    }

    @Override
    public void create(String locatie, int id) {
        save(new Clinica(locatie, id));
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Create_Clinica", timestamp);
    }

    @Override
    public void update(Clinica clinica, DirectorClinica directorClinica) {
        clinica.setDirector(directorClinica);
    }

    @Override
    public void update_id(int id, DirectorClinica directorClinica) {
        for (Clinica clinica:clinici){
            if(clinica.getId() == id){
                clinica.setDirector(directorClinica);
            }
        }
    }

    @Override
    public ArrayList<Clinica> all() {
        return clinici;
    }
}
