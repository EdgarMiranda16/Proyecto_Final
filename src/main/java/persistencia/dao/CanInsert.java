package persistencia.dao;

import modelos.dto.Response;

@FunctionalInterface
public interface CanInsert<T> {
    Response<T> insert(T value);
}
