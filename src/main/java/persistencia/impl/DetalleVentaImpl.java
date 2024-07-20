package persistencia.impl;

import conexion.Conexion;
import constantes.EstadoResponse;
import modelos.DetalleVenta;
import modelos.Producto;
import modelos.dto.Response;
import persistencia.IDetalleVenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constantes.Sentencias.*;

public class DetalleVentaImpl implements IDetalleVenta {

    @Override
    public Response<DetalleVenta> insert(DetalleVenta value) {
        Response<DetalleVenta> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(INSERT_DETALLE_VENTA)) {
            ps.setLong(1, value.getVenta().getId());
            ps.setLong(2, value.getProducto().getId());
            ps.setInt(3, value.getCantidad());
            ps.setDouble(4, value.getPrecioUnitario());
            ps.setDouble(5, value.getSubTotal());

            ps.executeUpdate();

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Registro ingresado...!");
            response.setData(value);
        } catch (Exception ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al ingresar el registro");
            System.out.println(ex);
        }

        return response;
    }

    @Override
    public Response<List<DetalleVenta>> list() {
        Response<List<DetalleVenta>> response = new Response();
        List<DetalleVenta> registros = new ArrayList<>();
        response.setData(registros);

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(LIST_DETALLE_VENTA);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DetalleVenta detalleVenta = this.registro(rs);
                registros.add(detalleVenta);
            }

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Registros Leídos...!");
            response.setData(registros);
        } catch (Exception ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al leer los registros");
            System.out.println(ex);
        }

        return response;
    }

    private DetalleVenta registro(ResultSet rs) throws SQLException {
        Producto producto = Producto.builder()
                .id(rs.getLong("producto_id"))
                .nombreProducto(rs.getString("nombre_producto"))
                .descripcion(rs.getString("producto_descripcion"))
                .precio(rs.getDouble("precio_producto"))
                .stock(rs.getInt("stock_producto"))
                .build();

        return DetalleVenta.builder()
                .id(rs.getLong("detalle_id"))
                .producto(producto)
                .cantidad(rs.getInt("cantidad"))
                .precioUnitario(rs.getDouble("precio_unitario"))
                .subTotal(rs.getDouble("subtotal"))
                .build();
    }
}
