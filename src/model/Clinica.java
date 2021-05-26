package model;

public class Clinica {
    private int id;
    private DirectorClinica directorClinica;
    private String locatie;


    public int getId() {
        return id;
    }

    public DirectorClinica getDirector() {
        return directorClinica;
    }

    public void setDirector(DirectorClinica directorClinica) {
        this.directorClinica = directorClinica;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Clinica(String locatie, int id) {
        this.locatie = locatie;
        this.id = id;

    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }









    @Override
    public String toString() {
        return this.locatie;
    }
}
