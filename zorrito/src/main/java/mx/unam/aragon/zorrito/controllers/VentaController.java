package mx.unam.aragon.zorrito.controllers;

import mx.unam.aragon.zorrito.dto.HistorialVentaDto;
import mx.unam.aragon.zorrito.dto.ItemVentaDto;
import mx.unam.aragon.zorrito.dto.VentaDto;
import mx.unam.aragon.zorrito.modelo.*;

import mx.unam.aragon.zorrito.service.Cliente.ClienteService;
import mx.unam.aragon.zorrito.service.Correo.EmailService;
import mx.unam.aragon.zorrito.service.CorteInventario.CorteInventarioService;
import mx.unam.aragon.zorrito.service.DetalleVenta.DetalleVentaService;
import mx.unam.aragon.zorrito.service.MetodoPago.MetodoPagoService;
import mx.unam.aragon.zorrito.service.Pago.PagoService;
import mx.unam.aragon.zorrito.service.Producto.ProductoService;
import mx.unam.aragon.zorrito.service.Usuario.UsuariosService;
import mx.unam.aragon.zorrito.service.Venta.VentaService;
import mx.unam.aragon.zorrito.utils.VentaPdfGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Date;
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
    @Autowired
    private CorteInventarioService corteInventarioService;

    @Autowired
    private EmailService emailService;

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
        Cliente cliente = clienteService.findByTelefono(ventaDto.getTelefonoCliente());

        if (cliente == null) {
            redirectAttributes.addFlashAttribute("error", "❌ No se encontró un cliente con ese número telefónico.");
            return "redirect:/venta/agregar_venta"; // ⛔ Detener flujo si no se encuentra cliente
        }

        // Crear y guardar venta
        Venta venta = new Venta();
        venta.setIdClienteVenta(clienteService.findByTelefono(ventaDto.getTelefonoCliente()));
        venta.setIdUsuarioVenta(usuario);
        venta.setFecha(new java.util.Date());
        Venta ventaGuardada = ventaService.save(venta);

        // ✅ Validar que items no sea null ni vacío antes de recorrerlo
        if (ventaDto.getItems() != null && !ventaDto.getItems().isEmpty()) {
            ventaDto.getItems().forEach(item -> {
                Producto producto = productoService.findById(item.getIdProducto());

                //disminuyendo el stock
                int nuevoStock = producto.getStockProducto() - item.getCantidad();
                if(nuevoStock < 0){
                    throw new RuntimeException("No hay suficiente stock para el producto: "
                            + producto.getNombreProducto());
                }

                producto.setStockProducto(nuevoStock);
                productoService.save(producto);//actualizamos el stock

                DetalleVenta detalle = new DetalleVenta();
                detalle.setVentas(ventaGuardada);
                detalle.setProductos(productoService.findById(item.getIdProducto()));
                detalle.setCantidad(item.getCantidad());
                detalle.setPrecioUnitario(item.getPrecioUnitario());
                detalleVentaService.save(detalle);

                // Registrar corte tipo "fin"
                CorteInventario corte = new CorteInventario();
                corte.setProducto(producto);
                corte.setFecha(new Date());
                corte.setTipo(Tipo.fin);
                corte.setCantidad(nuevoStock);
                corteInventarioService.save(corte);
            });
        } else {
            // Puedes manejarlo como error, log, o simplemente continuar
            System.out.println("Advertencia: No se proporcionaron detalles de venta");
        }

        // Guardar pago
        Pago pago = new Pago();
        pago.setIdVenta(ventaGuardada.getIdVenta());
        pago.setIdMethod(metodoPagoService.findById(ventaDto.getIdMetodoPago()));
        pago.setTotalPagado(ventaDto.getTotal());
        pagoService.save(pago);

        // correos
        try {
            List<ItemVentaDto> items = detalleVentaService.obtenerItemsDeVenta(ventaGuardada.getIdVenta());
            String clienteNombre = cliente.getNombreCliente();
            String metodoPago = pagoService.findByIdVenta(ventaGuardada.getIdVenta()).getIdMethod().getDescripcion();
            double total = pagoService.findByIdVenta(ventaGuardada.getIdVenta()).getTotalPagado();

            // Generar PDF en memoria
            ByteArrayInputStream pdfStream = VentaPdfGenerator.generarPdf(ventaGuardada, items, clienteNombre, metodoPago, total);
            byte[] pdfBytes = pdfStream.readAllBytes();

            // Enviar correo al cliente
            emailService.enviarCorreoConAdjunto(
                    cliente.getCorreoCliente(), // Asegúrate de que este campo existe y tiene correo válido
                    "Gracias por tu compra en Zorro Abarrotero",
                    "<p>Hola <b>" + clienteNombre + "</b>, gracias por tu compra.<br>Adjunto encontrarás tu ticket.</p>",
                    pdfBytes,
                    "ticket_venta.pdf"
            );
        } catch (Exception e) {
            e.printStackTrace(); // Puedes cambiarlo por logger si lo deseas
        }





        // Mensaje para mostrar en la vista
        redirectAttributes.addFlashAttribute("mensaje", "✅ Venta registrada exitosamente.");
        //return "redirect:/venta/agregar_venta";
        redirectAttributes.addFlashAttribute("idVenta", ventaGuardada.getIdVenta());
        return "redirect:/venta/pdf/" + ventaGuardada.getIdVenta();
    }
    
    // metodo para el pdf
    @GetMapping("/pdf/{id}")
    public ResponseEntity<byte[]> generarPdfVenta(@PathVariable("id") Long idVenta) {
        Venta venta = ventaService.findById(idVenta);
        List<ItemVentaDto> items = detalleVentaService.obtenerItemsDeVenta(idVenta); // debes crear este método
        String clienteNombre = venta.getIdClienteVenta().getNombreCliente();
        String metodoPago = pagoService.findByIdVenta(idVenta).getIdMethod().getDescripcion();
        double total = pagoService.findByIdVenta(idVenta).getTotalPagado();

        ByteArrayInputStream pdfStream = VentaPdfGenerator.generarPdf(venta, items, clienteNombre, metodoPago, total);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=venta_" + idVenta + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfStream.readAllBytes());
    }

    @GetMapping("/descarga-pdf")
    public String descargaPdf(Model model) {
        // idVenta ya está en el modelo por FlashAttributes
        return "/venta/descarga-pdf";
    }


    @GetMapping("/listar_venta")
    public String histrialVentas(Model model){
        List<Venta> ventas = ventaService.findAll();
        List<HistorialVentaDto> historial = new ArrayList<>();

        for(Venta venta: ventas){
            HistorialVentaDto dto = new HistorialVentaDto();
            dto.setNombreCajero(venta.getIdUsuarioVenta().getNombreUsuario());
            dto.setNombreCliente(venta.getIdClienteVenta().getTelefonoCliente());
            dto.setFechaVenta(venta.getFecha());


            // Armar productos y cantidades
            List<DetalleVenta> detalles = detalleVentaService.findByVentas(venta);
            List<String> items = new ArrayList<>();
            for(DetalleVenta det: detalles){
                items.add(det.getProductos().getNombreProducto() + " (" + det.getCantidad() + ")");
            }
            dto.setProductosYcantidades(items);

            // Buscar pago para total
            Pago pago = pagoService.findByIdVenta(venta.getIdVenta());
            dto.setTotalPagado(pago.getTotalPagado());

            historial.add(dto); // guardamos el dto en el objto historial
        }
        model.addAttribute("historial", historial);
        model.addAttribute("ventas", ventas);
        model.addAttribute("contenido", "Historial de Ventas");
        return "/venta/lista-venta";
    }


}