package service;

import model.Clinica;
import model.Medic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MedicServiceImp implements MedicService {



    public Set<Medic> medici = new HashSet<>();
    ClinicaService clinicaService = new ClinicaServiceImp();
    Clinica x;

    DataBase db = DataBase.getInstance();

    @Override
    public void addMedic(Medic medic) {

        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = db.getDBConnection()
                    .prepareStatement("insert into medic values(?, ?, ?, ?, ?, ? );", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, medic.getId());
            preparedStatement.setString(2, medic.getNume());
            preparedStatement.setString(3, medic.getSpecializare());
            preparedStatement.setString(4, String.valueOf(medic.getSex()));
            preparedStatement.setInt(5, medic.getAniExperienta());
            preparedStatement.setInt(6, medic.getClinica().getId());

            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            //resultSet.next();
            //return Integer.parseInt(resultSet.getString(1));

        } catch (SQLException e) {
            e.printStackTrace();
            //return -1;
        }
    }


    @Override
    public void deleteMedicByID(int medicid) {
        try {

            PreparedStatement preparedStatement = db.getDBConnection()
                    .prepareStatement("DELETE FROM medic WHERE id = ?");
            preparedStatement.setInt(1, medicid);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @Override
    public void getAll() {
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = db.getDBConnection()
                .prepareStatement("SELECT * FROM medic;")) {


            resultSet = preparedStatement.executeQuery();
            display(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //for(Medic medic:medici) {
            //System.out.println(medic.toString());
        //}
        //LocalDateTime timestamp=LocalDateTime.now();
        //Audit.logAction("Select_All_From_Medic", timestamp);

    }

    @Override
    public Medic save(Medic obiect) {
        medici.add(obiect);
        return obiect;
    }

    @Override
    public void delete(Medic obiect) {
        medici.remove(obiect);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Delete_Medic", timestamp);
    }

    @Override
    public void delete_id(int id) {
        boolean ok = false;
        for(Medic medic:medici) {
            if (medic.getId() == id) {
                delete(medic);
                ok = true;
                break;
            }

        }
        deleteMedicByID(id);
        if (!ok){
            System.out.println("Nu exista medic cu acest ID");
            System.exit(0);
        }

    }
    @Override
    public void create(String nume, int id, String specializare, char sex, int aniExperienta, Clinica clinica) {
        Medic medic = new Medic(nume, id, specializare, sex, aniExperienta, clinica);
        save(medic);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Create_Medic", timestamp);
        addMedic(medic);

    }

    @Override
    public void update(Medic obiect, int optiune, String nume) {
        if(optiune == 1){
            obiect.setNume(nume);
        }
        if(optiune == 2){
            obiect.setSpecializare(nume);
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Medic", timestamp);
        modifyMedicById(obiect.getId(), obiect);
    }

    @Override
    public void update(Medic obiect, Clinica clinica) {
        obiect.setClinica(clinica);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Medic", timestamp);
        modifyMedicById(obiect.getId(), obiect);
    }

    @Override
    public void update(Medic obiect, char sex) {
        obiect.setSex(sex);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Medic", timestamp);
    }

    @Override
    public void update(Medic obiect, int ani) {
        obiect.setAniExperienta(ani);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Medic", timestamp);
        modifyMedicById(obiect.getId(), obiect);

    }

    @Override
    public void update_id(int id, int optiune, String nume) {
        for(Medic medic:medici){
            if(medic.getId() == id){
                if(optiune == 1){
                    medic.setNume(nume);
                }
                if(optiune == 2){
                    medic.setSpecializare(nume);
                }
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Medic", timestamp);
    }

    @Override
    public void update_id(int id, Clinica clinica) {
        for(Medic medic:medici){
            if(medic.getId() == id){
                medic.setClinica(clinica);
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Medic", timestamp);
    }

    @Override
    public void update_id(int id, char sex) {
        for(Medic medic:medici){
            if(medic.getId() == id){
                medic.setSex(sex);
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Medic", timestamp);
    }

    @Override
    public void update_id(int id, int ani) {
        for(Medic medic:medici){
            if(medic.getId() == id){
                medic.setAniExperienta(ani);
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Medic", timestamp);
    }

    @Override
    public Set<Medic> all() {
        return medici;
    }

    @Override
    public void modifyMedicById(int medicid, Medic medic) {

        try (PreparedStatement preparedStatement = db.getDBConnection()
                .prepareStatement("UPDATE medic m SET m.nume = ?, m.specializare = ?, m.sex = ?, m.experienta = ?, m.id_clinica = ? WHERE m.id = ?")) {

            preparedStatement.setString(1, medic.getNume());
            preparedStatement.setString(2, medic.getSpecializare());
            preparedStatement.setString(3, String.valueOf(medic.getSex()));
            preparedStatement.setInt(4, medic.getAniExperienta());
            preparedStatement.setInt(5, medic.getClinica().getId());
            preparedStatement.setInt(6, medic.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            int id = myRs.getInt("id");
            String nume = myRs.getString("nume");
            int varsta = myRs.getInt("varsta");
            String sex = myRs.getString("sex");
            String specializare = myRs.getString("specializare");

            System.out.printf("%s, %s, %s, %s, %s\n", id, nume, varsta, sex, specializare);
        }
    }

    public Medic retrieveMedicById(int medicid) {

        Medic medic = null;

        try {
            PreparedStatement preparedStatement
                    = db.getDBConnection().prepareStatement("SELECT m.id, m.nume, m.specializare, m.sex, m.experienta, m.id_clinica FROM medic m WHERE id = ?");
            preparedStatement.setInt(1, medicid);

            ResultSet resultSet = preparedStatement.executeQuery();
            for(Clinica clinica:clinicaService.all()){
                if(clinica.getId() == resultSet.getInt(6)){
                    x.setLocatie(clinica.getLocatie());
                    x.setId(clinica.getId());
                }
            }
            while (resultSet.next()) {
                medic = new Medic(
                        resultSet.getString(2),
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getString(4).charAt(0),
                        resultSet.getInt(5),
                        x
                );
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medic;
    }



}


