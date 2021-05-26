package model;

public class Rezident extends Medic{

    private int anRezidentiat;

    public Rezident(String nume, int id, String specializare, char sex, Clinica clinica, int anRezidentiat) {
        super(nume, id, specializare, sex, 0, clinica);
        this.anRezidentiat = anRezidentiat;
    }

    public int getAnRezidentiat() {
        return anRezidentiat;
    }

    public void setAnRezidentiat(int anRezidentiat) {
        this.anRezidentiat = anRezidentiat;
    }
}
