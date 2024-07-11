package persistencia.dao;

import persistencia.dto.Response;

import java.util.List;

public interface DAO<T, K> {

    Response<T> insert(T value);

    Response<T> update(T value);

    Response<T> delete(T value);

    Response<List<T>> list();

    Response<T> get(K id);
}
