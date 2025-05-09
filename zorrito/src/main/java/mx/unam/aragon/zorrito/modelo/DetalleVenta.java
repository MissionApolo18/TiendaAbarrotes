package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "detalle_venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DetalleVenta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle")
	private long idDetalleVenta;
	
	@JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
	@OneToMany
	private List<Venta> ventas;
	
	@JoinColumn(name = "id_producto",referencedColumnName = "id_producto")
	@OneToMany
	private List<Producto> productos;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "precio_unitario")
	private double precioUnitario;
}
