package mx.unam.aragon.zorrito.controllers;

import jakarta.validation.Valid;
import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.service.Producto.ProductoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/producto")
public class ProductoController {
    private  static final Logger logger =
            LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoService productoService;

    @GetMapping("/agregar_producto")
    public String altaProducto(Model model) {
        Producto producto =  new Producto();
        model.addAttribute("producto", producto);
        model.addAttribute("contenido", "Alta producto");
        return "/producto/agregar-producto";
    }

    // guardar producto
    @PostMapping("/guardar-producto")
    public String guardarProducto(@Valid @ModelAttribute("producto") Producto producto,
                               BindingResult result, Model model) {
        if(result.hasErrors()){
            return "/producto/agregar-producto";
        }
        productoService.save(producto);
        model.addAttribute("contenido","Se almaceno con exito");
        return "redirect:/producto/listar_productos";
    }

    @GetMapping("/listar_productos")
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        model.addAttribute("contenido", "Lista de productos");
        return "/producto/lista-producto";
    }
}
