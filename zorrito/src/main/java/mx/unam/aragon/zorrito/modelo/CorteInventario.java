package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity(name = "corte_inventario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CorteInventario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_corte")
	private long idCorte;
	/*
	@JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
	@OneToMany
	private List<Producto> productos;*/

	@ManyToOne
	@JoinColumn(name = "id_producto", referencedColumnName = "id_producto", nullable = false)
	private Producto producto;
	
	@Column(name = "fecha")
	private Date fecha;

	@Enumerated(EnumType.STRING)
	@Column(name = "tipo")
	private Tipo tipo;
	
	@Column(name = "cantidad")
	private int cantidad;
}
