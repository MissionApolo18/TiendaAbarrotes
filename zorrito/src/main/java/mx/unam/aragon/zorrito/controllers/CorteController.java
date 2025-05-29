package mx.unam.aragon.zorrito.controllers;

import mx.unam.aragon.zorrito.modelo.CorteInventario;
import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.service.CorteInventario.CorteInventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/corte")
public class CorteController {

    @Autowired
    private CorteInventarioService corteInventarioService;

    @GetMapping("/listar_cortes")
    public String mostrarCorte(Model model){
        List<CorteInventario> cortes = corteInventarioService.findAll();
        model.addAttribute("cortes", cortes);
        model.addAttribute("contenido", "Historial de Inventario");
        return "/corte/lista-corte";
    }
}
