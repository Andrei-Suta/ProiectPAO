package service;

import model.Clinica;
import model.DirectorClinica;

import java.util.ArrayList;

public interface ClinicaService extends CRUD<Clinica>{
    void create(String locatie, int id);
    void update(Clinica clinica, DirectorClinica directorClinica);
    void update_id(int id, DirectorClinica directorClinica);
    ArrayList<Clinica> all();


}
