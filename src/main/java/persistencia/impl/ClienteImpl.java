package persistencia.impl;

import conexion.Conexion;
import constantes.EstadoResponse;
import modelos.Cliente;
import modelos.dto.Response;
import persistencia.ICliente;
import utilidades.Fecha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constantes.Sentencias.*;

public class ClienteImpl implements ICliente {

    @Override
    public Response<Cliente> insert(Cliente value) {
        Response<Cliente> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(INSERT_CLIENTE)) {
            ps.setString(1, value.getNombres());
            ps.setString(2, value.getApellidos());
            ps.setString(3, value.getDireccion());
            ps.setString(4, value.getTelefono());
            ps.setString(5, value.getEmail());

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
    public Response<Cliente> update(Cliente value) {
        Response<Cliente> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(UPDATE_CLIENTE)) {
            ps.setString(1, value.getNombres());
            ps.setString(2, value.getApellidos());
            ps.setString(3, value.getDireccion());
            ps.setString(4, value.getTelefono());
            ps.setString(5, value.getEmail());
            ps.setLong(6, value.getId());
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
    public Response<Cliente> delete(Cliente value) {
        Response<Cliente> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(DELETE_CLIENTE)) {
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
    public Response<List<Cliente>> list() {
        Response<List<Cliente>> response = new Response();
        List<Cliente> registros = new ArrayList<>();
        response.setData(registros);

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(LIST_CLIENTE);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = this.registro(rs);
                registros.add(cliente);
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
    public Response<Cliente> get(Long id) {
        Response<Cliente> response = new Response();

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_CLIENTE)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                response.setStatus(EstadoResponse.WARNING);
                response.setMessage("No se encontraron registros...!");
                return response;
            }

            Cliente cliente = this.registro(rs);

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Registro Leído...!");
            response.setData(cliente);

        } catch (Exception ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al leer el registro");
            System.out.println(ex);
        }

        return response;
    }

    private Cliente registro(ResultSet rs) throws SQLException {
        return Cliente.builder()
                .id(rs.getLong("id"))
                .nombres(rs.getString("nombres"))
                .apellidos(rs.getString("apellidos"))
                .direccion(rs.getString("direccion"))
                .telefono(rs.getString("telefono"))
                .email(rs.getString("email"))
                .fechaRegistro(Fecha.cast(rs.getString("fecha_registro")))
                .build();
    }
}
