package mx.unam.aragon.zorrito.validators.correo;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CorreoValidoValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CorreoValido {
    String message() default "Correo invalido. Debe ser un correo registrado @gmail.com, @outlook.com o @yahoo.com";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}