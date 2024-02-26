package Repositories;

import java.util.List;

public interface IRepository<T> {
    int add(T entity);
    List<T> getAll();
    T getById(int id);
    void update(T entity);
    void delete(int id);
}
