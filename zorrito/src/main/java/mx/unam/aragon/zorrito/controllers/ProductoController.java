package mx.unam.aragon.zorrito.controllers;

import jakarta.validation.Valid;
import mx.unam.aragon.zorrito.modelo.CorteInventario;
import mx.unam.aragon.zorrito.modelo.Distribuidor;
import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.modelo.Tipo;
import mx.unam.aragon.zorrito.service.CorteInventario.CorteInventarioService;
import mx.unam.aragon.zorrito.service.Distribuidor.DistribuidorService;
import mx.unam.aragon.zorrito.service.Producto.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    private  static final Logger logger =
            LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;
    @Autowired
    private DistribuidorService distribuidorService;
    @Autowired
    private CorteInventarioService corteInventarioService;

    // para agregar un producto
    @GetMapping("/agregar_producto")
    public String altaProducto(Model model) {
        Producto producto =  new Producto();
        model.addAttribute("producto", producto);
        model.addAttribute("distribuidores", distribuidorService.findAll());
        model.addAttribute("contenido", "Alta producto");
        return "/producto/agregar-producto";
    }

    // para guardar el producto con su proovedor
    @PostMapping("/guardar-producto")
    public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto,
                                  BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("distribuidores", distribuidorService.findAll());
            return "/producto/agregar-producto";
        }

        // Cargar distribuidor completo usando el id enviado en producto.getDistribuidor().getId_distribuidor()
        if(producto.getDistribuidor() != null && producto.getDistribuidor().getId_distribuidor() != 0) {
            Distribuidor distribuidor = distribuidorService.findById(producto.getDistribuidor().getId_distribuidor());
            producto.setDistribuidor(distribuidor);
        } else {
            // Maneja error o asigna valor por defecto, según tu lógica
            result.rejectValue("distribuidor", "error.producto", "Debe seleccionar un distribuidor");
            model.addAttribute("distribuidores", distribuidorService.findAll());
            return "/producto/agregar-producto";
        }

        productoService.save(producto);
        // 👇 Aquí se registra el corte tipo "inicio"
        CorteInventario corte = new CorteInventario();
        corte.setProducto(producto);
        corte.setFecha(new Date());
        corte.setTipo(Tipo.inicio);
        corte.setCantidad(producto.getStockProducto());
        corteInventarioService.save(corte);
        return "redirect:/producto/listar_productos";
    }

    // para listar los productos
    @GetMapping("/listar_productos")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("contenido", "Lista de productos");
        return "/producto/lista-producto";
    }

    // para modificar metodo get
    @GetMapping("/modificar-producto/{id}")
    public String editarProducto(@PathVariable(value = "id") Long idProducto,
                                ModelMap model){
        Producto producto = productoService.findById(idProducto);
        model.addAttribute("producto", producto);
        model.addAttribute("distribuidores", distribuidorService.findAll());
        model.addAttribute("contenido", "Modificar Producto");
        return "/producto/agregar-producto";
    }

    // para eliminar producto
    @GetMapping("/eliminar-producto/{id}")
    public String eliminarProducto(@PathVariable("id") Long idProducto) {
        productoService.deleteById(idProducto);
        return "redirect:/producto/listar_productos"; // importante usar redirect
    }
}
