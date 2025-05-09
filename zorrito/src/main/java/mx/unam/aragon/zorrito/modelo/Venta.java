package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private long idVenta;
	
	@JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
	@ManyToOne
	private Cliente IdClienteVenta;
	
	@JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	@ManyToOne
	private Usuarios IdUsuarioVenta;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "total")
	private double total;
	
}
