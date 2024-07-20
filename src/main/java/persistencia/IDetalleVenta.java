package persistencia;

import modelos.DetalleVenta;
import modelos.Venta;
import persistencia.dao.CanGet;
import persistencia.dao.CanInsert;
import persistencia.dao.CanList;
import persistencia.dao.DAO;

public interface IDetalleVenta extends CanInsert<DetalleVenta>, CanList<DetalleVenta> {
}
