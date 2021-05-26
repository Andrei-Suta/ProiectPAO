package service;

import model.Clinica;
import model.Rezident;

public interface RezidentService extends CRUD<Rezident> {
    void create(String nume, int id, String specializare, char sex, Clinica clinica, int anRezidentiat);
}
