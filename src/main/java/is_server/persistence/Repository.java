package is_server.persistence;

import java.util.Map;

public interface Repository<T> {

    T add(T object);

    Map<Integer, T> findAll();

    T findById(int id);

    T update(T object);
}
