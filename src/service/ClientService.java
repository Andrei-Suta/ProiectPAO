package service;

import model.Client;

import java.util.Set;

public interface ClientService extends CRUD<Client>{
    void create(String nume, int id, int varsta, char sex, String nevoieSpecializare);
    void update(Client obiect, int optiune, String str);
    void update(Client obiect, int varsta);
    void update(Client obiect, char sex);
    void update_id(int id, int optiune, String str);
    void update_id(int id, int varsta);
    void update_id(int id, char sex);
    Set<Client> all();
    void addClient(Client client);
    void deleteClientByID(int clientid);
    void modifyClientById(int clientid, Client client);
}
