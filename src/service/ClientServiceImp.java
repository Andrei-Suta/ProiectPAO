package service;

import model.Client;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ClientServiceImp implements ClientService {

    DataBase db = DataBase.getInstance();

    protected Set<Client> clienti = new HashSet<>();
    ResultSet resultSet = null;

    @Override
    public void getAll() {
        try (PreparedStatement preparedStatement = db.getDBConnection()
                .prepareStatement("SELECT * FROM client;")) {


            resultSet = preparedStatement.executeQuery();
            display(resultSet);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        for(Client client:clienti){
            System.out.println(client.toString());
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Select_All_From_Client", timestamp);
    }

    private void display(ResultSet myRs) throws SQLException {
        while (myRs.next()) {
            int id = myRs.getInt("id");
            String nume = myRs.getString("nume");
            String sex = myRs.getString("sex");
            String specializare = myRs.getString("specializare");
            double clinica = myRs.getInt("id_clinica");

            System.out.printf("%s, %s, %s, %s, %s\n", id, nume, sex, specializare, clinica);
        }
    }

    @Override
    public Client save(Client obiect) {
        clienti.add(obiect);
        return obiect;
    }

    @Override
    public void delete(Client obiect) {
        clienti.remove(obiect);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Delete_Client", timestamp);

    }

    @Override
    public void delete_id(int id) {
        for(Client client:clienti){
            if(client.getId()==id){
                delete(client);
            }
        }
        deleteClientByID(id);
    }

    @Override
    public void create(String nume, int id, int varsta, char sex, String nevoieSpecializare) {
        save(new Client(nume, id, varsta, sex, nevoieSpecializare));
        addClient(new Client(nume, id, varsta, sex, nevoieSpecializare));
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Create_Client", timestamp);
    }

    @Override
    public void update(Client obiect, int optiune, String str) {
        if(optiune == 1){
            obiect.setNume(str);
        }if(optiune == 2){
            obiect.setNevoieSpecializare(str);
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Client", timestamp);
        modifyClientById(obiect.getId(), obiect);
    }

    @Override
    public void update(Client obiect, int varsta) {
        obiect.setVarsta(varsta);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Client", timestamp);
        modifyClientById(obiect.getId(), obiect);
    }

    @Override
    public void update(Client obiect, char sex) {
        obiect.setSex(sex);
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Client", timestamp);
        modifyClientById(obiect.getId(), obiect);
    }

    @Override
    public void update_id(int id, int optiune, String str) {
        for(Client client:clienti){
            if(client.getId() == id){
                if(optiune == 1){
                    client.setNume(str);
                }if(optiune == 2){
                    client.setNevoieSpecializare(str);
                }
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Client", timestamp);
    }

    @Override
    public void update_id(int id, int varsta) {
        for(Client client:clienti){
            if (client.getId() == id){
                client.setVarsta(varsta);
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Client", timestamp);
    }

    @Override
    public void update_id(int id, char sex) {
        for(Client client:clienti){
            if(client.getId() == id){
                client.setSex(sex);
            }
        }
        LocalDateTime timestamp=LocalDateTime.now();
        Audit.logAction("Update_Client", timestamp);
    }

    @Override
    public Set<Client> all() {
        return clienti;
    }

    @Override
    public void addClient(Client client) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = db.getDBConnection()
                    .prepareStatement("insert into medic values(?, ?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, client.getId());
            preparedStatement.setString(2, client.getNume());
            preparedStatement.setInt(3, client.getVarsta());
            preparedStatement.setString(4, String.valueOf(client.getSex()));
            preparedStatement.setString(5, client.getNevoieSpecializare());

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
    public void deleteClientByID(int clientid) {
        try {

            PreparedStatement preparedStatement = db.getDBConnection()
                    .prepareStatement("DELETE FROM client WHERE id = ?");
            preparedStatement.setInt(1, clientid);

            preparedStatement.executeUpdate();

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifyClientById(int clientid, Client client) {
        try (PreparedStatement preparedStatement = db.getDBConnection()
                .prepareStatement("UPDATE client c SET c.nume = ?, c.varsta = ?, c.sex = ?, c.specializare = ? WHERE c.id = ?")) {

            preparedStatement.setString(1, client.getNume());
            preparedStatement.setInt(2, client.getVarsta());
            preparedStatement.setString(3, String.valueOf(client.getSex()));
            preparedStatement.setString(4, client.getNevoieSpecializare());
            preparedStatement.setInt(5, client.getId());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}