package modelos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Carrito {

    private Producto producto;
    private Integer cantidad;
    private Double total;
}
