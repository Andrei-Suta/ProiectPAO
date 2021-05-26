package service;

import model.Clinica;
import model.Rezident;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class RezidentServiceImp implements RezidentService {



    protected Set<Rezident> rezidenti = new HashSet<>();

    @Override
    public void getAll() {
        for(Rezident rezident:rezidenti){
            System.out.println(rezident.toString());
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Select_All_From_Rezident", timestamp);
    }

    @Override
    public Rezident save(Rezident obiect) {
        rezidenti.add(obiect);
        return obiect;
    }

    @Override
    public void delete(Rezident obiect) {
        rezidenti.remove(obiect);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Delete_Rezident", timestamp);
    }

    @Override
    public void delete_id(int id) {
        for(Rezident rezident:rezidenti){
            if(rezident.getId() == id){
                delete(rezident);
            }
        }
    }


    @Override
    public void create(String nume, int id, String specializare, char sex, Clinica clinica, int anRezidentiat) {
        save(new Rezident(nume, id, specializare, sex, clinica, anRezidentiat));
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Create_Rezident", timestamp);
    }
}