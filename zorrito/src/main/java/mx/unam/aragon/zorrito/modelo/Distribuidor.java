package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "distribuidor")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Distribuidor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_distribuidor")
	private long id_distribuidor;
	
	@Column(name = "nombre")
	private String nombreDistribuidor;
	
	@Column(name = "correo")
	private String correoDistribuidor;
}
