package persistencia.dao;

import modelos.dto.Response;

@FunctionalInterface
public interface CanUpdate<T> {
    Response<T> update(T value);
}
