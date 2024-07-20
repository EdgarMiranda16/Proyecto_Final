package modelos;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DetalleVenta {

    private Long id;
    private Venta venta;
    private Producto producto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subTotal;
}
