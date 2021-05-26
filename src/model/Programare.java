package model;

public class Programare {
    private int id;
    private int id_medic;
    private int id_client;
    private String data;
    private double ora;

    public Programare(int id, int id_medic, int id_client, String data, double ora) {
        this.id=id;
        this.id_medic = id_medic;
        this.id_client = id_client;
        this.data = data;
        this.ora = ora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMedic() {
        return id_medic;
    }

    public void setMedic(int id_medic) {
        this.id_medic = id_medic;
    }

    public int getClient() {
        return id_client;
    }

    public void setClient(int id_client) {
        this.id_client = id_client;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getOra() {
        return ora;
    }

    public void setOra(double ora) {
        this.ora = ora;
    }
}
