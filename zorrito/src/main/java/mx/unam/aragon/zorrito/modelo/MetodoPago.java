package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "metodo_pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MetodoPago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_metodo")
	private long idMetodo;
	
	@Column(name = "descripcion")
	private String descripcion;
}
