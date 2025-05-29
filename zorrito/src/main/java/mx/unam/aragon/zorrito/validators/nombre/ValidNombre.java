package mx.unam.aragon.zorrito.validators.nombre;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NombreValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNombre {
}
