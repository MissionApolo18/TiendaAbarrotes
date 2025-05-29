package mx.unam.aragon.zorrito.controllers;

import jakarta.validation.Valid;
import mx.unam.aragon.zorrito.modelo.Cliente;
import mx.unam.aragon.zorrito.modelo.Producto;
import mx.unam.aragon.zorrito.service.Cliente.ClienteService;
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

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private  static final Logger logger =
            LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/agregar_cliente")
    public String altaCliente(Model model) {
        Cliente cliente =  new Cliente();
        model.addAttribute("cliente", cliente);
        model.addAttribute("contenido", "Alta cliente");
        return "/cliente/agregar-cliente";
    }

    // guardar producto
    @PostMapping("/guardar-cliente")
    public String guardarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                                  BindingResult result, Model model) {
        if(result.hasErrors()){
            return "/cliente/agregar-cliente";
        }

        // Verifica si ya existe un cliente con ese correo
        Cliente existente = clienteService.findByTelefono(cliente.getTelefonoCliente());
        Cliente existeCorreo = clienteService.findByCorreoCliente(cliente.getCorreoCliente());
        if (existente != null && !existente.getIdCliente().equals(cliente.getIdCliente())) {
            result.rejectValue("telefonoCliente", "error.cliente", "Ya existe un cliente con ese número telefónico");
        }

        if (existeCorreo != null && !existeCorreo.getIdCliente().equals(cliente.getIdCliente())) {
            result.rejectValue("correoCliente", "error.cliente", "Ya existe un cliente con ese correo");
        }

        if (result.hasErrors()) {
            return "/cliente/agregar-cliente";
        }



        clienteService.save(cliente);
        model.addAttribute("contenido","Se almaceno con exito");
        return "/cliente/agregar-cliente";
    }

    @GetMapping("/listar_cliente")
    public String listarCliente(Model model) {
        List<Cliente> clientes = clienteService.findAll();
        model.addAttribute("clientes", clientes);
        model.addAttribute("contenido", "Lista de clientes");
        return "/cliente/lista-cliente";
    }

    // para modificar metodo get
    @GetMapping("/modificar-cliente/{id}")
    public String editarCliente(@PathVariable(value = "id") Long id,
                                 ModelMap model){
        Cliente cliente = clienteService.findById(id);
        model.addAttribute("cliente", cliente);
        model.addAttribute("contenido", "Modificar Cliente");
        return "/cliente/agregar-cliente";
    }

    // para eliminar producto
    @GetMapping("/eliminar-cliente/{id}")
    public String eliminarCliente(@PathVariable("id") Long id) {
        clienteService.deleteById(id);
        return "redirect:/lista-cliente"; // importante usar redirect
    }
}
