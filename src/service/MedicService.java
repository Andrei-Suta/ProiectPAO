package service;

import model.Clinica;
import model.Medic;

import java.util.Set;

public interface MedicService extends CRUD<Medic>{
    void create(String nume, int id, String specializare, char sex, int aniExperienta, Clinica clinica);
    void update(Medic obiect, int optiune, String nume);
    void update(Medic obiect, Clinica clinica);
    void update(Medic obiect, char sex);
    void update(Medic obiect, int ani);
    void update_id(int id, int optiune, String nume);
    void update_id(int id, Clinica clinica);
    void update_id(int id, char sex);
    void update_id(int id, int ani);
    Set<Medic> all();
    void addMedic(Medic medic);
    void deleteMedicByID(int medicid);
    void modifyMedicById(int medicid, Medic medic);

}
