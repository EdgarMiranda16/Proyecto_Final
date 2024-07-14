package persistencia;

import modelos.Usuario;
import modelos.dto.Response;
import persistencia.dao.DAO;

public interface IUsuario extends DAO<Usuario, Long> {

    Response<Usuario> iniciarSesion(Usuario usuario);

}
