package modelos;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Producto {

    private Long id;
    private String nombreProducto;
    private String descripcion;
    private Double precio;
    private Integer stock;
    private LocalDateTime fechaAgregado;
}
