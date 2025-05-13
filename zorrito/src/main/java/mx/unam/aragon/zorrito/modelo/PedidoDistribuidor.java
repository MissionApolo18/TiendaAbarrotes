package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "pedido_distribuidor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PedidoDistribuidor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pedido")
	private long idPedDistri;
	
	@JoinColumn(name = "id_distribuidor",referencedColumnName = "id_distribuidor")
	@ManyToOne(fetch = FetchType.EAGER)
	private Distribuidor idDistribuidor;
	
	@Column(name = "fecha")
	private Date fecha;
}
