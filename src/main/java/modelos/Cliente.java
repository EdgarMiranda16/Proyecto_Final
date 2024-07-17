package modelos;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Cliente {

    private Long id;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private String email;
    private LocalDateTime fechaRegistro;
}
