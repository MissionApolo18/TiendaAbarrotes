// PedidoCorreoService.java
package mx.unam.aragon.zorrito.service.Correo;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import mx.unam.aragon.zorrito.dto.pedido.ItemPedidoDto;
import mx.unam.aragon.zorrito.dto.pedido.PedidoDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoCorreoService {

    private final EmailService emailService;

    public void enviarPedidoAlProveedor(PedidoDto pedidoDto) throws MessagingException {
        String asunto = "Nuevo Pedido #" + pedidoDto.getIdPedido();
        String cuerpo = construirCuerpoHtml(pedidoDto);

        emailService.enviarCorreoConAdjunto(
                pedidoDto.getCorreoProveedor(),
                asunto,
                cuerpo,
                null, // sin PDF por ahora
                null
        );
    }



    private String construirCuerpoHtml(PedidoDto pedido) {
        StringBuilder html = new StringBuilder();
        html.append("<h2>Nuevo Pedido desde el Zorro Abarrotero</h2>");
        html.append("<p>Por este medio le hago envio de los productos que solicito para el Zorro Abarrotero</p>");
        html.append("<p><strong>Proveedor:</strong> ").append(pedido.getNombreProveedor()).append("</p>");
        html.append("<p><strong>Fecha:</strong> ").append(pedido.getFecha()).append("</p>");
        html.append("<table border='1' cellpadding='5'><tr>")
                .append("<th>Producto</th><th>Cantidad</th></tr>");

        for (ItemPedidoDto item : pedido.getItems()) {
            html.append("<tr>")
                    .append("<td>").append(item.getNombreProducto()).append("</td>")
                    .append("<td>").append(item.getCantidad()).append("</td>")
                    .append("</tr>");
        }

        html.append("</table>");
        html.append("<p>Gracias por su atención.</p>");

        return html.toString();
    }
}
