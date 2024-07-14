package persistencia.dao;

import modelos.dto.Response;

import java.util.List;

@FunctionalInterface
public interface CanList<T> {
    Response<List<T>> list();
}
