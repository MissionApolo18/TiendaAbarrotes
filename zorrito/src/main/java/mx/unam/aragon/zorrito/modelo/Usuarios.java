package mx.unam.aragon.zorrito.modelo;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Usuarios {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private long id_usuario;
	
	
	@ManyToOne()
	@JoinColumn(name = "rol", referencedColumnName = "id_rol")
	private Rol rolUsuario;
	
	@Column(name = "nombre")
	private String nombreUsuario;

	@Column(name = "password")
	private String password;
	
	@Column(name = "username")
	private String username;                     ;
}
