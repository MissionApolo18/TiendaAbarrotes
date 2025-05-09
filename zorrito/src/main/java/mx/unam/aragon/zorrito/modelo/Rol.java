package mx.unam.aragon.zorrito.modelo;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "rol")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Rol {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol", nullable = false)
	private long id_Rol;
	
	@Column(name = "rol")
	private String nombreRol;
}
