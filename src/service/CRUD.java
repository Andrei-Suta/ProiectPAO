package service;

import java.io.IOException;

public interface CRUD<T> {


    void getAll();
    T save(T obiect);
    void delete(T obiect) throws IOException;
    void delete_id(int id) throws IOException;



}
