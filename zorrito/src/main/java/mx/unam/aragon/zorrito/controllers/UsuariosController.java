package mx.unam.aragon.zorrito.controllers;

import jakarta.validation.Valid;
import mx.unam.aragon.zorrito.modelo.Usuarios;
import mx.unam.aragon.zorrito.service.Rol.RolService;
import mx.unam.aragon.zorrito.service.Usuario.UsuariosService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuariosController {
    private  static final Logger logger =
            LoggerFactory.getLogger(UsuariosController.class);

    @Autowired
    private UsuariosService usuariosService;
    @Autowired
    private RolService rolService;

    // inyectando bean de encriptado
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/agregar_usuario")
    public String altaUsuario(Model model) {
        Usuarios usuarios =  new Usuarios();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", rolService.findAll());
        model.addAttribute("contenido", "Alta Usuarios");
        return "/usuario/agregar-usuario";
    }

    // guardar producto
    @PostMapping("/guardar-usuario")
    public String guardarUsuario(@Valid @ModelAttribute("usuarios") Usuarios usuarios,
                                      BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("roles", rolService.findAll());
            model.addAttribute("contenido", "Alta Usuarios");
            return "/usuario/agregar-usuario";
        }

        // Si es edición (tiene ID), debemos comparar con el original
        if (usuarios.getId_usuario() != null) {
            Usuarios usuarioExistente = usuariosService.findById(usuarios.getId_usuario());

            // Si la contraseña fue cambiada (no está igual que la de la BD), la encriptamos
            if (!usuarios.getPassword().equals(usuarioExistente.getPassword())) {
                String passwordEncriptada = passwordEncoder.encode(usuarios.getPassword());
                usuarios.setPassword(passwordEncriptada);
            }
        } else {
            // Si es nuevo usuario, siempre encriptamos
            String passwordEncriptada = passwordEncoder.encode(usuarios.getPassword());
            usuarios.setPassword(passwordEncriptada);
        }

        usuariosService.save(usuarios);
        model.addAttribute("contenido","Se almaceno con exito");
        return "/usuario/agregar-usuario";
    }

    @GetMapping("/listar_usuario")
    public String listarUsuario(Model model) {
        List<Usuarios> usuarios = usuariosService.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("contenido", "Lista de Usuarios");
        return "/usuario/lista-usuario";
    }

    // para modificar metodo get
    @GetMapping("/modificar-usuario/{id}")
    public String editarUsuario(@PathVariable(value = "id") Long id,
                                     ModelMap model){
        Usuarios usuarios = usuariosService.findById(id);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("roles", rolService.findAll());
        model.addAttribute("contenido", "Modificar Usuarios");

        return "/usuario/agregar-usuario"; // Nombre correcto del HTML
    }

    // para eliminar producto
    @GetMapping("/eliminar-usuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id) {
        usuariosService.deleteById(id);
        return "redirect:/usuarios/listar_usuario";
    }
}