package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;
import mx.unam.aragon.zorrito.validators.correo.CorreoValido;

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
	@CorreoValido
	private String correoDistribuidor;
}
