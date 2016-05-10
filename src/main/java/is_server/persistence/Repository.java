package is_server.persistence;

import spark.Request;

import java.util.Map;

public interface Repository<T> {

    T add(Request request);

    Map<Integer, T> findAll();

    T findById(int id);

    T update(Request request);
}
