package service;

import model.Description;

public interface ServiceInterf<T extends Description> {
    void create(int id, String desccription);

}
