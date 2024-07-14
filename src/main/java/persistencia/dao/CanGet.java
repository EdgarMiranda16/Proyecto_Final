package persistencia.dao;

import modelos.dto.Response;

@FunctionalInterface
public interface CanGet<T, K> {
    Response<T> get(K id);
}
