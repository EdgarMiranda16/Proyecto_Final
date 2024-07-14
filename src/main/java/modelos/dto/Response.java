package modelos.dto;

import constantes.EstadoResponse;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Response<T> {

    private EstadoResponse status;
    private String message;
    private T data;
}
