package service;

import model.Programare;

import java.util.Set;

public interface ProgramareService extends CRUD<Programare>{
    void create(int id, int id_medic, int id_client, String data, double ora);
    void update(Programare obiect, int id_medic);
    void update(Programare obiect, String data);
    void update(Programare obiect, double ora);
    void update_id(int id, int id_medic);
    void update_id(int id, String data);
    void update_id(int id, double ora);
    Set<Programare> all();
    public void addProgramare(Programare programare);
    void deleteProgramareByID(int programareid);
}
