package model;

public class DirectorClinica extends Medic{

    private int anConducere;



    public DirectorClinica(String nume, int id, String specializare, char sex, int aniExperienta, Clinica clinica, int anConducere) {
        super(nume, id, specializare, sex, aniExperienta, clinica );
        this.anConducere = anConducere;
    }

}
