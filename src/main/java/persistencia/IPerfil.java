package persistencia;

import modelos.Perfil;
import persistencia.dao.CanGet;
import persistencia.dao.CanList;

public interface IPerfil extends CanList<Perfil>, CanGet<Perfil, Long> {
}
