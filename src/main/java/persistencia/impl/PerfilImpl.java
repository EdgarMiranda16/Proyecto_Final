package persistencia.impl;

import conexion.Conexion;
import constantes.EstadoResponse;
import modelos.Perfil;
import modelos.dto.Response;
import persistencia.IPerfil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constantes.Sentencias.*;

public class PerfilImpl implements IPerfil {

    @Override
    public Response<List<Perfil>> list() {
        Response<List<Perfil>> response = new Response();
        List<Perfil> registros = new ArrayList<>();
        response.setData(registros);

        try (Connection conexion = Conexion.getConnection(); PreparedStatement ps = conexion.prepareStatement(LIST_PERFIL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Perfil perfil = this.registro(rs);
                registros.add(perfil);
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
    public Response<Perfil> get(Long id) {
        Response<Perfil> response = new Response();

        try (Connection connection = Conexion.getConnection();
             PreparedStatement ps = connection.prepareStatement(GET_PERFIL)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {
                response.setStatus(EstadoResponse.WARNING);
                response.setMessage("No se encontraron registros...!");
                return response;
            }

            Perfil perfil = this.registro(rs);

            response.setStatus(EstadoResponse.SUCCESS);
            response.setMessage("Registro Leído...!");
            response.setData(perfil);
            rs.close();

        } catch (Exception ex) {
            response.setStatus(EstadoResponse.ERROR);
            response.setMessage("Error al leer el registro");
            System.out.println(ex);
        }

        return response;
    }

    private Perfil registro(ResultSet rs) throws SQLException {
        return Perfil.builder()
                .id(rs.getLong("id"))
                .nombrePerfil(rs.getString("nombre_perfil"))
                .descripcion(rs.getString("descripcion"))
                .build();
    }


}
