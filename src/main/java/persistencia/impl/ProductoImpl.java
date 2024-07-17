package persistencia.impl;

import conexion.Conexion;
import constantes.EstadoResponse;
import modelos.Cliente;
import modelos.Producto;
import modelos.dto.Response;
import persistencia.ICliente;
import persistencia.IProducto;
import utilidades.Fecha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constantes.Sentencias.*;

public class ProductoImpl implements IProducto {

    @Override
    public Response<Producto> insert(Producto value) {
        Response<Producto> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(INSERT_PRODUCTO)) {
            ps.setString(1, value.getNombreProducto());
            ps.setString(2, value.getDescripcion());
            ps.setDouble(3, value.getPrecio());
            ps.setInt(4, value.getStock());

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
    public Response<Producto> update(Producto value) {
        Response<Producto> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(UPDATE_PRODUCTO)) {
            ps.setString(1, value.getNombreProducto());
            ps.setString(2, value.getDescripcion());
            ps.setDouble(3, value.getPrecio());
            ps.setInt(4, value.getStock());
            ps.setLong(5, value.getId());
            ps.executeUpdate();

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Registro actualizado...!");
            response.setData(value);
        } catch (Exception ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al actualizar el registro");
            System.out.println(ex);
        }

        return response;
    }

    @Override
    public Response<Producto> delete(Producto value) {
        Response<Producto> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(DELETE_PRODUCTO)) {
            ps.setLong(1, value.getId());
            ps.executeUpdate();

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Registro Eliminado...!");
            response.setData(value);
        } catch (Exception ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al eliminar el registro");
            System.out.println(ex);
        }

        return response;
    }

    @Override
    public Response<List<Producto>> list() {
        Response<List<Producto>> response = new Response();
        List<Producto> registros = new ArrayList<>();
        response.setData(registros);

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(LIST_PRODUCTO);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = this.registro(rs);
                registros.add(producto);
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

    @Override
    public Response<Producto> get(Long id) {
        Response<Producto> response = new Response();

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_PRODUCTO)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                response.setStatus(EstadoResponse.WARNING);
                response.setMessage("No se encontraron registros...!");
                return response;
            }

            Producto producto = this.registro(rs);

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Registro Leído...!");
            response.setData(producto);

        } catch (Exception ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al leer el registro");
            System.out.println(ex);
        }

        return response;
    }

    private Producto registro(ResultSet rs) throws SQLException {
        return Producto.builder()
                .id(rs.getLong("id"))
                .nombreProducto(rs.getString("nombre_producto"))
                .descripcion(rs.getString("descripcion"))
                .precio(rs.getDouble("precio"))
                .stock(rs.getInt("stock"))
                .fechaAgregado(Fecha.cast(rs.getString("fecha_agregado")))
                .build();
    }
}
