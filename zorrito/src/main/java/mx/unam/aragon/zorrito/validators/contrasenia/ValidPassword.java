package mx.unam.aragon.zorrito.validators.contrasenia;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
public @interface ValidPassword {
    String message() default "La contraseña debe tener al menos 8 caracteres y 2 números";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
