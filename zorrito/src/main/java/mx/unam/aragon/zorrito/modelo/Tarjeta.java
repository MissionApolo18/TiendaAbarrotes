package mx.unam.aragon.zorrito.modelo;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "tarjeta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Tarjeta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarjeta")
	private long idTarjeta;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_pago", referencedColumnName = "id_pago")
	private Pago idPagoTarjeta;
	
	@Column(name = "tipo_tarjeta")
	private String tipoTarjeta;
	
	@Column(name = "digitos_tarjeta")
	private String digitosTarjeta;
	
	@Column(name="banco")
	private String banco;
	
	@Column(name = "ccv")
	private String ccv;
}
