package mx.unam.aragon.zorrito.validators.correo;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CorreoValidoValidator implements ConstraintValidator<CorreoValido, String> {

    private static final String CORREO_REGEX =
            "^[A-Za-z0-9._%+-]+@(gmail\\.com|outlook\\.com|yahoo\\.com)$";

    private static final Pattern CORREO_PATTERN = Pattern.compile(CORREO_REGEX);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.trim().isEmpty()) {
            return false;
        }
        return CORREO_PATTERN.matcher(value).matches();
    }
}