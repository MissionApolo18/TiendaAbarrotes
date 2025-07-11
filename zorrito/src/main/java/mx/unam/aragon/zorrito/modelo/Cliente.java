package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;
import mx.unam.aragon.zorrito.validators.correo.CorreoValido;
import mx.unam.aragon.zorrito.validators.nombre.ValidNombre;

@Entity(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	private Long idCliente;
	
	@Column(name = "nombre")
	//@ValidNombre
	private String nombreCliente;
	
	@Column(name = "correo")
	@CorreoValido //aplicando validador
	private String correoCliente;
	
	@Column(name = "telefono")
	private String telefonoCliente;
	
}
