package service;

import model.Programare;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ProgramareServiceImp implements ProgramareService{

    protected Set<Programare> programari = new HashSet<>();

    DataBase db = DataBase.getInstance();


    @Override
    public void getAll() {
        for(Programare programare:programari){
            System.out.println(programare.toString());
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Select_All_From_Programare", timestamp);
    }

    @Override
    public Programare save(Programare obiect) {
        programari.add(obiect);
        return obiect;
    }

    @Override
    public void delete(Programare obiect) {
        programari.remove(obiect);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Delete_Programare", timestamp);
    }

    @Override
    public void delete_id(int id) {
        for(Programare programare:programari){
            if(programare.getId() == id){
                delete(programare);
            }
        }
        deleteProgramareByID(id);
    }

    @Override
    public void create(int id, int id_medic, int id_client, String data, double ora) {
        save(new Programare(id, id_medic, id_client, data, ora));
        addProgramare(new Programare(id, id_medic, id_client, data, ora));
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Create_Programare", timestamp);
    }

    @Override
    public void update(Programare obiect, int id_medic) {
        obiect.setMedic(id_medic);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Programare", timestamp);
    }

    @Override
    public void update(Programare obiect, String data) {
        obiect.setData(data);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Programare", timestamp);
    }

    @Override
    public void update(Programare obiect, double ora) {
        obiect.setOra(ora);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Programare", timestamp);
    }

    @Override
    public void update_id(int id, int id_medic) {
        for(Programare programare:programari){
            if(programare.getId() == id){
                programare.setMedic(id_medic);
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Programare", timestamp);
    }

    @Override
    public void update_id(int id, String data) {
        for(Programare programare:programari){
            if(programare.getId() == id){
                programare.setData(data);
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Programare", timestamp);
    }

    @Override
    public void update_id(int id, double ora) {
        for(Programare programare:programari){
            if(programare.getId() == id){
                programare.setOra(ora);
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Programare", timestamp);
    }

    @Override
    public Set<Programare> all() {
        return programari;
    }

    @Override
    public void addProgramare(Programare programare) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = db.getDBConnection()
                    .prepareStatement("insert into programare values(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, programare.getId());
            preparedStatement.setString(2, programare.getData());
            preparedStatement.setDouble(3, programare.getOra());
            preparedStatement.setInt(4, programare.getMedic());
            preparedStatement.setInt(5, programare.getClient());

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
    public void deleteProgramareByID(int programareid) {
        try {

            PreparedStatement preparedStatement = db.getDBConnection()
                    .prepareStatement("DELETE FROM programare WHERE id = ?");
            preparedStatement.setInt(1, programareid);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
