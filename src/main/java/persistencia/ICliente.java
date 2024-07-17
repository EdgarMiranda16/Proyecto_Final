package persistencia;

import modelos.Cliente;
import modelos.Usuario;
import modelos.dto.Response;
import persistencia.dao.DAO;

public interface ICliente extends DAO<Cliente, Long> {
}
