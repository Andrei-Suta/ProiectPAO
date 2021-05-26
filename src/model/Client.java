package model;

public class Client {
    private String nume;
    private int id;
    private int varsta;
    private char sex;
    private String nevoieSpecializare;

    public Client(String nume, int id, int varsta, char sex, String nevoieSpecializare) {
        this.nume = nume;
        this.id = id;
        this.varsta = varsta;
        this.sex = sex;
        this.nevoieSpecializare = nevoieSpecializare;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getNevoieSpecializare() {
        return nevoieSpecializare;
    }

    public void setNevoieSpecializare(String nevoieSpecializare) {
        this.nevoieSpecializare = nevoieSpecializare;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {

        return this.nume + "  ID:" + this.getId() + "  NevoieSpecializare:" + this.nevoieSpecializare +"  Sex:" + this.sex + "  Varsta:" + this.varsta +  "\n";

    }
}
