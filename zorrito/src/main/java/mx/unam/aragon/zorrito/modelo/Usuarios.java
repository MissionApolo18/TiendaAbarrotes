package mx.unam.aragon.zorrito.modelo;
import jakarta.persistence.*;
import lombok.*;
import mx.unam.aragon.zorrito.validators.contrasenia.ValidPassword;
import mx.unam.aragon.zorrito.validators.nombre.ValidNombre;
import mx.unam.aragon.zorrito.validators.usuario.UniqueUsername;

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
	private Long id_usuario;
	
	
	@ManyToOne()
	@JoinColumn(name = "rol", referencedColumnName = "id_rol")
	private Rol rolUsuario;
	
	@Column(name = "nombre")
	//@ValidNombre
	private String nombreUsuario;

	@Column(name = "password")
	@ValidPassword // validaciones de password
	private String password;
	
	@Column(name = "username")
	//@UniqueUsername // validaciones de que el username no se repita
	private String username;
}
