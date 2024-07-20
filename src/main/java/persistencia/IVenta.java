package persistencia;

import modelos.Carrito;
import modelos.Venta;
import persistencia.dao.*;

public interface IVenta extends CanInsert<Venta>, CanList<Venta>, CanGet<Venta, Long> {
}
