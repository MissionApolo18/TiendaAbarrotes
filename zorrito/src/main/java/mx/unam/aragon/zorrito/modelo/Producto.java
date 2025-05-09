package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto")
    private long id_producto;
    
    @Column(name = "nombre")
    private String nombreProducto;
    
    @Column(name = "precio_unitario_venta")
    private Double precioProducto;
    
    @Column(name = "stock")
    private int stockProducto;
    
    @Column(name = "imagen_url")
    private String imagenProducto;
}
