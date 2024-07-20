package modelos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Venta {

    private Long id;
    private Cliente cliente;
    private LocalDateTime fechaVenta;
    private List<DetalleVenta> detalleVenta;
    private Double total;
}
