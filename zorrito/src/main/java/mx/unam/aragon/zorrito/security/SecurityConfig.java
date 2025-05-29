package mx.unam.aragon.zorrito.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // 1) El PasswordEncoder que usarás para comparar la contraseña encriptada
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2) El proveedor de autenticación que usará tu UserDetailsService
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    // 3) La configuración de seguridad HTTP
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(authenticationProvider())

                .authorizeHttpRequests(auth -> auth
                        // Rutas para el ADMIN:
                        .requestMatchers(
                                "/producto/agregar_producto",
                                "/corte/**",
                                "/usuario/**",
                                "/cliente/**",
                                "/distribuidor/**",
                                "/venta/listar_venta",
                                "pedidos/**"
                        ).hasRole("ADMIN")
                        // Rutas para CAJERO:
                        .requestMatchers(
                                "/venta/agregar_venta"
                        ).hasRole("CAJERO")
                        // Ambas roles pueden acceder:
                        .requestMatchers("/index").authenticated()
                        // Recursos estáticos y login:
                        .requestMatchers("/", "/login", "/css/**", "/js/**", "/images/**").permitAll()
                        // Todo lo demás requiere autenticación:
                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/index", true)
                        .failureUrl("/login?error")
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                )

                // 👇 Esto redirige a /index si el usuario intenta acceder a algo sin permiso
                .exceptionHandling(exception ->
                        exception.accessDeniedPage("/index")
                );

        return http.build();
    }

}


