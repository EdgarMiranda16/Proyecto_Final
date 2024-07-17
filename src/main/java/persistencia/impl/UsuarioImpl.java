package persistencia.impl;

import conexion.Conexion;
import constantes.EstadoResponse;

import static constantes.Sentencias.*;

import modelos.Perfil;
import modelos.Usuario;
import modelos.dto.Response;
import persistencia.IUsuario;
import utilidades.Fecha;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioImpl implements IUsuario {

    @Override
    public Response<Usuario> iniciarSesion(Usuario usuario) {
        Response<Usuario> response = new Response();

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(LOGIN_USUARIO)) {

            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                response.setStatus(EstadoResponse.WARNING);
                response.setMessage("Credenciales Incorrectas...!");
                return response;
            }

            Usuario responseUsuario = this.registro(rs);

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Bienvenido...!");
            response.setData(responseUsuario);
            rs.close();

        } catch (SQLException ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al iniciar sesion");
            System.out.println(ex);
        }

        return response;
    }

    @Override
    public Response<Usuario> insert(Usuario value) {
        Response<Usuario> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(INSERT_USUARIO)) {
            ps.setLong(1, value.getPerfil().getId());
            ps.setString(2, value.getUsuario());
            ps.setString(3, value.getPassword());
            ps.setString(4, value.getEmail());

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
    public Response<Usuario> update(Usuario value) {
        Response<Usuario> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(UPDATE_USUARIO)) {
            ps.setLong(1, value.getPerfil().getId());
            ps.setString(2, value.getUsuario());
            ps.setString(3, value.getPassword());
            ps.setString(4, value.getEmail());
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
    public Response<Usuario> delete(Usuario value) {
        Response<Usuario> response = new Response();

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(DELETE_USUARIO)) {
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
    public Response<List<Usuario>> list() {
        Response<List<Usuario>> response = new Response();
        List<Usuario> registros = new ArrayList<>();
        response.setData(registros);

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(LIST_USUARIO);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = this.registro(rs);
                registros.add(usuario);
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
    public Response<Usuario> get(Long id) {
        Response<Usuario> response = new Response();

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_USUARIO)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                response.setStatus(EstadoResponse.WARNING);
                response.setMessage("No se encontraron registros...!");
                return response;
            }

            Usuario usuario = this.registro(rs);

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Registro Leído...!");
            response.setData(usuario);

        } catch (Exception ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al leer el registro");
            System.out.println(ex);
        }

        return response;
    }

    private Usuario registro(ResultSet rs) throws SQLException {
        Perfil responsePerfil = Perfil.builder()
                .id(rs.getLong("perfil_id"))
                .nombrePerfil(rs.getString("nombre_perfil"))
                .descripcion(rs.getString("descripcion"))
                .build();

        return Usuario.builder()
                .id(rs.getLong("usuario_id"))
                .perfil(responsePerfil)
                .usuario(rs.getString("usuario"))
                .password(rs.getString("password"))
                .email(rs.getString("email"))
                .fechaCreacion(Fecha.cast(rs.getString("fecha_creacion")))
                .build();
    }
}
