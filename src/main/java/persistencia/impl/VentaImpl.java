package persistencia.impl;

import conexion.Conexion;
import constantes.EstadoResponse;
import modelos.*;
import modelos.dto.Response;
import persistencia.IVenta;
import utilidades.Fecha;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static constantes.Sentencias.*;

public class VentaImpl implements IVenta {

    @Override
    public Response<Venta> insert(Venta value) {
        Response<Venta> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(INSERT_VENTA, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, value.getCliente().getId());
            ps.setLong(2, value.getUsuario().getId());
            ps.setDouble(3, value.getTotal());

            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long idGenerado = generatedKeys.getLong(1);
                    value.setId(idGenerado);
                }
            }

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
    public Response<List<Venta>> list() {
        Response<List<Venta>> response = new Response();
        List<Venta> registros = new ArrayList<>();
        response.setData(registros);

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(LIST_VENTA);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Venta venta = this.registro(rs);
                registros.add(venta);
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
    public Response<Venta> get(Long id) {
        Response<Venta> response = new Response();

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_VENTA)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                response.setStatus(EstadoResponse.WARNING);
                response.setMessage("No se encontraron registros...!");
                return response;
            }

            Venta venta = this.registro(rs);

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Registro Leído...!");
            response.setData(venta);

        } catch (Exception ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al leer el registro");
            System.out.println(ex);
        }

        return response;
    }

    private Venta registro(ResultSet rs) throws SQLException {
        Cliente cliente = Cliente.builder()
                .id(rs.getLong("cliente_id"))
                .nombres(rs.getString("cliente_nombres"))
                .apellidos(rs.getString("cliente_apellidos"))
                .build();

        return Venta.builder()
                .id(rs.getLong("venta_id"))
                .cliente(cliente)
                .fechaVenta(Fecha.cast(rs.getString("fecha_venta")))
                .total(rs.getDouble("total"))
                .build();
    }
}
