package dao;

import java.util.List;

public interface AbstractDao <T, K> {

    void save(T t);

    T getById (K k);

    List<T> getAll ();

    void update (T t);

    void delete(T t);

}
