package mx.unam.aragon.zorrito.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity(name = "pago")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Pago {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pago")
	private Long idPago;
	
	@Column(name = "id_venta")
	private Long idVenta;
	
	@JoinColumn(name = "id_metodo", referencedColumnName = "id_metodo")
	@OneToOne(fetch = FetchType.EAGER)
	private MetodoPago idMethod;
	
	@Column(name = "total_pagado")
	private double totalPagado=0.00;
	
}
