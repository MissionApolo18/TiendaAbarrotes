package mx.unam.aragon.zorrito.controllers;

import jakarta.validation.Valid;
import mx.unam.aragon.zorrito.modelo.DetallePedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.Distribuidor;
import mx.unam.aragon.zorrito.modelo.PedidoDistribuidor;
import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.service.DetallePedidoDis.DetallePedidoDistribuidorService;
import mx.unam.aragon.zorrito.service.Distribuidor.DistribuidorService;
import mx.unam.aragon.zorrito.service.MetodoPago.MetodoPagoService;
import mx.unam.aragon.zorrito.service.PedidoDistribuidor.PedidoDisService;
import mx.unam.aragon.zorrito.service.Producto.ProductoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/distribuidor")
public class DistribuidorController {
    
    private static final Logger logger = LoggerFactory.getLogger(DistribuidorController.class);
    
    @Autowired
    private DistribuidorService distribuidorService;
    
    @Autowired
    private PedidoDisService pedidoService;
    
    @Autowired
    private DetallePedidoDistribuidorService detalleService;
    
    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private MetodoPagoService metodoPagoService;
    
    
    @GetMapping("/nuevo")
    public String mostrarFormularioDistribuidor(Model model) {
        model.addAttribute("distribuidor", new Distribuidor());
        model.addAttribute("contenido", "Alta distribuidor");
        return "/distribuidor/agregar-distribuidor";
    }
    
    @PostMapping("/guardar")
    public String guardarDistribuidor(@Valid @ModelAttribute("distribuidor") Distribuidor distribuidor,
                                      BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("contenido", "Errores en el formulario");
            return "/distribuidor/agregar-distribuidor";
        }
        
        distribuidorService.save(distribuidor);
        return "redirect:/distribuidor/listar";
    }
    
    @GetMapping("/listar")
    public String listarDistribuidores(Model model) {
        List<Distribuidor> distribuidores = distribuidorService.findAll();
        model.addAttribute("distribuidores", distribuidores);
        model.addAttribute("contenido", "Lista de Proveedores");
        return "/distribuidor/lista-distribuidor";
    }
    
    @GetMapping("/editar/{id}")
    public String editarDistribuidor(@PathVariable Long id, ModelMap model) {
        Distribuidor distribuidor = distribuidorService.findById(id);
        if (distribuidor == null) {
            return "redirect:/distribuidor/listar";
        }
        model.addAttribute("distribuidor", distribuidor);
        model.addAttribute("contenido", "Modificar Proveedor");
        return "/distribuidor/agregar-distribuidor";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminarDistribuidor(@PathVariable Long id) {
        distribuidorService.deleteById(id);
        return "redirect:/distribuidor/listar";
    }
    
    //Pedidos
    
    @GetMapping("/pedidos/nuevo")
    public String nuevoPedido(Model model) {
        model.addAttribute("pedidoDto", new PedidoDistribuidor());
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("metodosPago", metodoPagoService.findAll());
        return "/pedido/formulario-pedido";
    }
    
    @PostMapping("/pedidos/guardar")
    public String guardarPedido(@Valid @ModelAttribute("pedidoDto") PedidoDistribuidor pedido,
                                BindingResult result,
                                @RequestParam("total") double total,
                                @RequestParam Map<String, String> allParams,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("productos", productoService.findAll());
            model.addAttribute("metodosPago", metodoPagoService.findAll());
            return "/pedido/formulario-pedido";
        }
        
        pedidoService.save(pedido);
        
        int i = 0;
        while (allParams.containsKey("items[" + i + "].idProducto")) {
            try {
                Long idProducto = Long.parseLong(allParams.get("items[" + i + "].idProducto"));
                int cantidad = Integer.parseInt(allParams.get("items[" + i + "].cantidad"));
                double precioUnitario = Double.parseDouble(allParams.get("items[" + i + "].precioUnitario"));
                
                Producto producto = productoService.findById(idProducto);
                if (producto == null) {
                    logger.warn("Producto con id {} no encontrado", idProducto);
                    i++;
                    continue;
                }
                
                DetallePedidoDistribuidor detalle = new DetallePedidoDistribuidor();
                detalle.setIdpedidoDis(pedido);
                detalle.setIdProductoDist(producto);
                detalle.setCantidad(cantidad);
                detalle.setPrecioUnitario(precioUnitario);
                
                detalleService.save(detalle);
            } catch (Exception e) {
                logger.error("Error procesando detalle del producto: {}", e.getMessage());
            }
            i++;
        }
        
        return "redirect:/distribuidor/listar";
    }
    
    @GetMapping("/pedidos/anadir-producto")
    public String anadirProductoAPedido(@RequestParam("pedidoId") Long pedidoId,
                                        @RequestParam("productoId") Long productoId) {
        PedidoDistribuidor pedido = pedidoService.findById(pedidoId);
        Producto producto = productoService.findById(productoId);
        
        if (pedido == null || producto == null) {
            return "redirect:/producto/listar_bajo_stock?pedidoId=" + pedidoId;
        }
        
        boolean yaExiste = detalleService.existsByPedidoAndProducto(pedido, producto);
        if (!yaExiste) {
            DetallePedidoDistribuidor detalle = new DetallePedidoDistribuidor();
            detalle.setIdpedidoDis(pedido);
            detalle.setIdProductoDist(producto);
            detalle.setCantidad(50); // Idealmente configurable
            detalle.setPrecioUnitario(producto.getPrecioProducto());
            detalleService.save(detalle);
        }
        
        return "redirect:/producto/listar_bajo_stock?pedidoId=" + pedidoId;
    }
}
