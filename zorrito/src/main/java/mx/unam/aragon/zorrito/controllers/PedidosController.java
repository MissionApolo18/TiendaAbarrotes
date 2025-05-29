package mx.unam.aragon.zorrito.controllers;

import mx.unam.aragon.zorrito.dto.ItemPedidoDto;
import mx.unam.aragon.zorrito.dto.PedidoDto;
import mx.unam.aragon.zorrito.modelo.*;
import mx.unam.aragon.zorrito.service.Distribuidor.DistribuidorService;
import mx.unam.aragon.zorrito.service.PedidoDistribuidor.PedidoDisService;
import mx.unam.aragon.zorrito.service.DetallePedidoDis.DetallePedidoDistribuidorService;
import mx.unam.aragon.zorrito.service.Producto.ProductoService;
import mx.unam.aragon.zorrito.service.Usuario.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidosController {
	
	@Autowired
	private DistribuidorService distribuidorService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private PedidoDisService pedidoService;
	
	@Autowired
	private DetallePedidoDistribuidorService detallePedidoService;
	
	@Autowired
	private UsuariosService usuariosService;
	
	@GetMapping("/realizar_pedido")
	public String mostrarFormularioPedido(Model model) {
		model.addAttribute("pedidoDto", new PedidoDto());
		model.addAttribute("distribuidores", distribuidorService.findAll());
		model.addAttribute("productos", productoService.findAll());
		model.addAttribute("iddetalle", new DetallePedidoDistribuidorService() {
			@Override
			public DetallePedidoDistribuidor save(DetallePedidoDistribuidor detallePedido) {
				return null;
			}
			
			@Override
			public List<DetallePedidoDistribuidor> findAll() {
				return List.of();
			}
			
			@Override
			public void deleteById(Long id) {
			
			}
			
			@Override
			public DetallePedidoDistribuidor findById(Long id) {
				return null;
			}
			
			@Override
			public boolean existsByPedidoAndProducto(PedidoDistribuidor pedido, Producto producto) {
				return false;
			}
		});
		return "/pedidos/realizar-pedido";
	}
	
	@PostMapping("/guardar")
	public String guardarPedido(@ModelAttribute("pedidoDto") PedidoDto pedidoDto, RedirectAttributes redirectAttributes) {
		// Obtener usuario autenticado
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Usuarios usuario = usuariosService.findByUsername(username);
		
		// Buscar distribuidor por ID desde el DTO
		Distribuidor distribuidor = distribuidorService.findById(pedidoDto.getIddetalle());
		if (distribuidor == null) {
			redirectAttributes.addFlashAttribute("error", "❌ No se encontró un distribuidor con ese Id.");
			return "redirect:/pedidos/realizar_pedido";
		}
		
		// Crear y guardar el pedido principal
		PedidoDistribuidor pedido = new PedidoDistribuidor();
		pedido.setIdDistribuidor(distribuidor);
		pedido.setFecha(new Date());
		PedidoDistribuidor pedidoGuardado = pedidoService.save(pedido);
		
		// Verificar si hay ítems de pedido
		if (pedidoDto.getItems() != null && !pedidoDto.getItems().isEmpty()) {
			for (ItemPedidoDto item : pedidoDto.getItems()) {
				Producto producto = productoService.findById(item.getIdProducto());
				
				if (producto != null) {
					// Actualizar stock del producto
					int nuevoStock = producto.getStockProducto() + item.getCantidad();
					producto.setStockProducto(nuevoStock);
					productoService.save(producto);
					
					// Crear y guardar el detalle del pedido
					DetallePedidoDistribuidor detalle = new DetallePedidoDistribuidor();
					detalle.setIdpedidoDis(pedidoGuardado);
					detalle.setIdProductoDist(producto);
					detalle.setCantidad(item.getCantidad());
					detalle.setPrecioUnitario(item.getPrecioUnitario());
					
					detallePedidoService.save(detalle);
				} else {
					System.out.println("⚠️ Producto no encontrado con ID: " + item.getIdProducto());
				}
			}
		} else {
			System.out.println("⚠️ Advertencia: No se proporcionaron detalles del pedido");
		}
		
		redirectAttributes.addFlashAttribute("mensaje", "✅ Pedido registrado exitosamente.");
		return "redirect:/pedidos/listar_pedidos";
	}
	
	
	@GetMapping("/listar_pedidos")
	public String listarPedidos(Model model) {
		List<PedidoDistribuidor> pedidos = pedidoService.findAll();
		model.addAttribute("pedidos", pedidos);
		return "/pedidos/lista-pedidos";
	}
}
