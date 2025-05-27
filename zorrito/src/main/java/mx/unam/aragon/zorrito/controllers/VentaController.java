package mx.unam.aragon.zorrito.controllers;

import mx.unam.aragon.zorrito.dto.VentaDto;
import mx.unam.aragon.zorrito.modelo.*;

import mx.unam.aragon.zorrito.service.Cliente.ClienteService;
import mx.unam.aragon.zorrito.service.DetalleVenta.DetalleVentaService;
import mx.unam.aragon.zorrito.service.MetodoPago.MetodoPagoService;
import mx.unam.aragon.zorrito.service.Pago.PagoService;
import mx.unam.aragon.zorrito.service.Producto.ProductoService;
import mx.unam.aragon.zorrito.service.Usuario.UsuariosService;
import mx.unam.aragon.zorrito.service.Venta.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private VentaService ventaService;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private PagoService pagoService;
    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/agregar_venta")
    public String mostrarFormularioVenta(Model model) {
        // Preparar el DTO vacío para el formulario
        model.addAttribute("ventaDto", new VentaDto());

        // Cargar clientes, productos, métodos de pago para el select
        List<Cliente> clientes = clienteService.findAll();
        List<Producto> productos = productoService.findAll();
        List<MetodoPago> metodosPago = metodoPagoService.findAll();

        model.addAttribute("clientes", clientes);
        model.addAttribute("productos", productos);
        model.addAttribute("metodosPago", metodosPago);

        return "/venta/agregar-venta";
    }


    @PostMapping("/guardar")
    public String guardarVenta(@ModelAttribute("ventaDto") VentaDto ventaDto, Model model, RedirectAttributes redirectAttributes) {
        // Obtener usuario autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Usuarios usuario = usuariosService.findByUsername(username);

        // Crear y guardar venta
        Venta venta = new Venta();
        venta.setIdClienteVenta(clienteService.findById(ventaDto.getIdCliente()));
        venta.setIdUsuarioVenta(usuario);
        venta.setFecha(new java.util.Date());
        Venta ventaGuardada = ventaService.save(venta);

        // ✅ Validar que items no sea null ni vacío antes de recorrerlo
        if (ventaDto.getItems() != null && !ventaDto.getItems().isEmpty()) {
            ventaDto.getItems().forEach(item -> {
                DetalleVenta detalle = new DetalleVenta();
                detalle.setVentas(ventaGuardada);
                detalle.setProductos(productoService.findById(item.getIdProducto()));
                detalle.setCantidad(item.getCantidad());
                detalle.setPrecioUnitario(item.getPrecioUnitario());
                detalleVentaService.save(detalle);
            });
        } else {
            // Puedes manejarlo como error, log, o simplemente continuar
            System.out.println("Advertencia: No se proporcionaron detalles de venta (items)");
        }

        // Guardar pago
        Pago pago = new Pago();
        pago.setIdVenta(ventaGuardada.getIdVenta());
        pago.setIdMethod(metodoPagoService.findById(ventaDto.getIdMetodoPago()));
        pago.setTotalPagado(ventaDto.getTotal());
        pagoService.save(pago);

        // Mensaje para mostrar en la vista
        redirectAttributes.addFlashAttribute("mensaje", "✅ Venta registrada exitosamente.");
        return "redirect:/venta/agregar_venta";
    }


}