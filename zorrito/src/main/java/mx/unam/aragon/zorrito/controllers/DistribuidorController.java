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
    
    @Autowired
    private DistribuidorService distribuidorService;
    
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
    
}
