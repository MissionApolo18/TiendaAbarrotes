package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "detalle_pedido_distribuidor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class DetallePedidoDistribuidor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_detalle")
	private long idDetalle;
	
	@JoinColumn(name = "id_pedido", referencedColumnName = "id_pedido")
	@ManyToOne
	private PedidoDistribuidor IdpedidoDis;
	
	@JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
	@ManyToOne
	private Producto IdProductoDist;
	
	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "precio_unitario")
	private double precioUnitario;
}
