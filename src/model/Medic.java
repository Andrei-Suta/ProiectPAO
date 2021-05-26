package model;

public class Medic {
    private String nume;
    private int id;
    private String specializare;
    private char sex;
    private int aniExperienta;
    private Clinica clinica;


    public Medic(String nume, int id, String specializare, char sex, int aniExperienta, Clinica clinica) {
        this.nume = nume;
        this.id = id;
        this.specializare = specializare;
        this.sex = sex;
        this.aniExperienta = aniExperienta;
        this.clinica = clinica;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAniExperienta(){
        return aniExperienta;
    }


    public void setAniExperienta(int aniExperienta){
        this.aniExperienta = aniExperienta;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Clinica getClinica() {
        return clinica;
    }

    public void setClinica(Clinica clinica) {
        this.clinica = clinica;
    }

    @Override
    public String toString() {

        return this.nume + "  ID:" + this.getId() + "  Specializare:" + this.specializare +"  Sex:" + this.sex + "  AniExperienta:" + this.aniExperienta + "  Clinica:" + this.clinica + "\n";

    }
}
