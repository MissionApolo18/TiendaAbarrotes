package mx.unam.aragon.zorrito.controllers;

import jakarta.mail.MessagingException;
import mx.unam.aragon.zorrito.dto.pedido.HistorialPedidoDto;
import mx.unam.aragon.zorrito.dto.pedido.ItemPedidoDto;
import mx.unam.aragon.zorrito.dto.pedido.PedidoDto;
import mx.unam.aragon.zorrito.modelo.*;
import mx.unam.aragon.zorrito.repository.DistribuidorRepository;
import mx.unam.aragon.zorrito.service.Correo.PedidoCorreoService;
import mx.unam.aragon.zorrito.service.DetallePedidoDis.DetallePedidoDistribuidorService;
import mx.unam.aragon.zorrito.service.Distribuidor.DistribuidorService;
import mx.unam.aragon.zorrito.service.PedidoDistribuidor.PedidoDisService;
import mx.unam.aragon.zorrito.service.Producto.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private DistribuidorService distribuidorService;

    @Autowired
    private PedidoDisService pedidoService;

    @Autowired
    private DetallePedidoDistribuidorService detallePedidoService;

    @Autowired
    private PedidoCorreoService pedidoCorreoService;

    @Autowired
    private DistribuidorRepository distribuidorRepository;

    @GetMapping("/nuevo_pedido")
    public String mostrarFormularioPedido(Model model) {
        model.addAttribute("pedidoDto", new PedidoDto());

        List<Distribuidor> distribuidores = distribuidorService.findAll();
        List<Producto> productos = productoService.findAll();

        model.addAttribute("distribuidores", distribuidores);
        model.addAttribute("productos", productos);

        return "/pedido/agregar-pedido";
    }

    @PostMapping("/guardar")
    public String guardarPedido(@ModelAttribute PedidoDto pedidoDto, RedirectAttributes redirectAttributes) {

        // Buscar distribuidor completo
        Distribuidor distribuidor = distribuidorService.findById(pedidoDto.getIdDistribuidor());
        if (distribuidor == null) {
            redirectAttributes.addFlashAttribute("error", "Distribuidor no encontrado.");
            return "redirect:/pedido/nuevo_pedido";
        }

        // Crear y asignar PedidoDistribuidor
        PedidoDistribuidor pedido = new PedidoDistribuidor();
        pedido.setIdDistribuidor(distribuidor);
        pedido.setFecha(LocalDate.now()); // Usamos LocalDate

        // Guardar pedido
        pedido = pedidoService.save(pedido);

        // Guardar detalles del pedido
        for (ItemPedidoDto itemDto : pedidoDto.getItems()) {
            DetallePedidoDistribuidor detalle = new DetallePedidoDistribuidor();

            Producto producto = new Producto();
            producto.setId_producto(itemDto.getIdProducto());

            detalle.setPedidoDistribuidor(pedido);
            detalle.setProducto(producto);
            detalle.setCantidad(itemDto.getCantidad());

            detallePedidoService.save(detalle);
        }

        // --- COMPLETAR DATOS NECESARIOS PARA EL CORREO ---
        pedidoDto.setIdPedido(pedido.getIdPedDistri());
        pedidoDto.setFecha(java.sql.Date.valueOf(pedido.getFecha())); // LocalDate -> java.util.Date

        // ⚠️ Aquí asignamos correo y nombre del proveedor (obligatorio para el envío del correo)
        pedidoDto.setCorreoProveedor(distribuidor.getCorreoDistribuidor());
        pedidoDto.setNombreProveedor(distribuidor.getNombreDistribuidor());

        // Completar nombres de productos
        for (ItemPedidoDto item : pedidoDto.getItems()) {
            Producto producto = productoService.findById(item.getIdProducto());
            if (producto != null) {
                item.setNombreProducto(producto.getNombreProducto());
            }
        }

        // Enviar correo al proveedor
        try {
            pedidoCorreoService.enviarPedidoAlProveedor(pedidoDto);
            redirectAttributes.addFlashAttribute("mensaje", "Pedido guardado y correo enviado.");
        } catch (MessagingException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("error", "Pedido guardado, pero no se pudo enviar el correo.");
        }

        return "redirect:/pedido/nuevo_pedido";
    }


    @GetMapping("/historial")
    public String verHistorialPedidos(Model model) {
        List<PedidoDistribuidor> pedidos = pedidoService.findAll(); // O uno más específico

        List<HistorialPedidoDto> historial = new ArrayList<>();

        for (PedidoDistribuidor pedido : pedidos) {
            HistorialPedidoDto dto = new HistorialPedidoDto();
            dto.setNombreProveedor(pedido.getIdDistribuidor().getNombreDistribuidor());
            dto.setFecha(pedido.getFecha());

            List<String> items = new ArrayList<>();
            List<DetallePedidoDistribuidor> detalles = detallePedidoService.findByPedidoDistribuidor(pedido);

            for (DetallePedidoDistribuidor detalle : detalles) {
                String nombreProducto = detalle.getProducto().getNombreProducto();
                int cantidad = detalle.getCantidad();
                items.add(nombreProducto + " - " + cantidad + " unidades");
            }

            dto.setProductosYcantidades(items);
            historial.add(dto);
        }

        model.addAttribute("pedidos", historial);
        return "pedido/lista-pedido";
    }



}
