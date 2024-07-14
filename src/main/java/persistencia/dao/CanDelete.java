package persistencia.dao;

import modelos.dto.Response;

@FunctionalInterface
public interface CanDelete<T> {
    Response<T> delete(T value);
}
