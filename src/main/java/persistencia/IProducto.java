package persistencia;

import modelos.Producto;
import modelos.dto.Response;
import persistencia.dao.DAO;

public interface IProducto extends DAO<Producto, Long> {

    Response<Producto> updateStock(Producto value);
}
