package mx.unam.aragon.zorrito.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PedidoDto {
	
	private Long iddetalle;
	private Long idpedido;
	private Long idproducto;
	private int cantidad;
	
	private List<ItemPedidoDto> items = new ArrayList<>();
	
	private Double total; // opcional, puede calcularse en el backend
	
}
