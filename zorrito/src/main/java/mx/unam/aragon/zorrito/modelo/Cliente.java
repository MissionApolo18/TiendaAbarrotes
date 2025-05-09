package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

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
	private long idCliente;
	
	@Column(name = "nombre")
	private String nombreCliente;
	
	@Column(name = "correo")
	private String correoCliente;
	
	@Column(name = "telefono")
	private int telefonoCliente;
	
}
