package mx.unam.aragon.zorrito.validators.nombre;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NombreValidator implements ConstraintValidator<ValidNombre, String> {

    // Acepta letras mayúsculas/minúsculas, espacios y tildes (acentos)
    private static final String NAME_PATTERN = "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$";

    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context) {
        return nombre != null && nombre.matches(NAME_PATTERN);
    }
}