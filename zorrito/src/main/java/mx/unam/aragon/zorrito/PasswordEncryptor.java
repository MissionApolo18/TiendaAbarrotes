package mx.unam.aragon.zorrito;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncryptor {
    public static void main(String[] args) {
        String rawPassword = "admin123";  // Cambia aquí la contraseña que quieras encriptar

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println("Password original: " + rawPassword);
        System.out.println("Password encriptada: " + encodedPassword);
    }
}

