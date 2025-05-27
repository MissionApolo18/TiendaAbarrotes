package mx.unam.aragon.zorrito.security;

import mx.unam.aragon.zorrito.modelo.Usuarios;
import mx.unam.aragon.zorrito.service.Usuario.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UsuariosService usuariosService;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Buscar al usuario en la base de datos
//        Usuarios usuario = usuariosService.findByUsername(username);
//
//        if (usuario == null) {
//            throw new UsernameNotFoundException("Usuario no encontrado");
//        }
//
//        // Cargar roles y devolver el objeto User de Spring Security
//        return User.builder()
//                .username(usuario.getUsername())
//                .password(usuario.getPassword())
//                .roles(usuario.getRolUsuario().getNombreRol().toUpperCase()) // Aquí asignas el rol
//                .build();
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuarios = usuariosService.findByUsername(username);
        if (usuarios == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Verifica el rol (por ejemplo, "ADMIN", "CAJERO", etc.)
        String rol = usuarios.getRolUsuario().getNombreRol().toUpperCase(); // Ajusta si el atributo se llama diferente

        // Imprime en consola
        System.out.println("🟢 Usuario logueado: " + username);
        System.out.println("🔒 Rol asignado: " + rol);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + rol));

        return new org.springframework.security.core.userdetails.User(usuarios.getUsername(), usuarios.getPassword(), authorities);
    }

}
