package modelos;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Usuario {

    private Long id;
    private Perfil perfil;
    private String usuario;
    private String password;
    private String email;
    private LocalDateTime fechaCreacion;
}
