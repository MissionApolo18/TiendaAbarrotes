package mx.unam.aragon.zorrito.controllers;

import jakarta.validation.Valid;
import mx.unam.aragon.zorrito.modelo.Distribuidor;
import mx.unam.aragon.zorrito.service.Distribuidor.DistribuidorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/distribuidor")
public class DistribuidorController {
    private  static final Logger logger =
            LoggerFactory.getLogger(DistribuidorController.class);

    @Autowired
    private DistribuidorService distribuidorService;

    @GetMapping("/agregar_distribuidor")
    public String altaDistribuidor(Model model) {
        Distribuidor distribuidor =  new Distribuidor();
        model.addAttribute("distribuidor", distribuidor);
        model.addAttribute("contenido", "Alta distribuidor");
        return "/distribuidor/agregar-distribuidor";
    }

    // guardar producto
    @PostMapping("/guardar-distribuidor")
    public String guardarDistribuidor(@Valid @ModelAttribute("distribuidor") Distribuidor distribuidor,
                                 BindingResult result, Model model) {
        if(result.hasErrors()){
            return "/distribuidor/agregar-distribuidor";
        }
        distribuidorService.save(distribuidor);
        model.addAttribute("contenido","Se almaceno con exito");
        return "/distribuidor/agregar-distribuidor";
    }

    @GetMapping("/listar_distribuidor")
    public String listarDistribuidor(Model model) {
        List<Distribuidor> distribuidores = distribuidorService.findAll();
        model.addAttribute("distribuidores", distribuidores);
        model.addAttribute("contenido", "Lista de Proovedores");
        return "/distribuidor/lista-distribuidor";
    }

    // para modificar metodo get
    @GetMapping("/modificar-distribuidor/{id}")
    public String editarDistribuidor(@PathVariable(value = "id") Long id,
                                 ModelMap model){
        Distribuidor distribuidor = distribuidorService.findById(id);
        model.addAttribute("distribuidor", distribuidor);
        model.addAttribute("contenido", "Modificar Proovedor");
        return "/distribuidor/agregar-distribuidor";
    }

    // para eliminar producto
    @GetMapping("/eliminar-distribuidor/{id}")
    public String eliminarDistribuidor(@PathVariable("id") Long id) {
        distribuidorService.deleteById(id);
        return "redirect:/distribuidor/listar_distribuidor";
    }
}
