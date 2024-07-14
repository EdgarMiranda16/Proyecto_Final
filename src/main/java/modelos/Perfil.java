package modelos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Perfil {

    private Long id;
    private String nombrePerfil;
    private String descripcion;
}
