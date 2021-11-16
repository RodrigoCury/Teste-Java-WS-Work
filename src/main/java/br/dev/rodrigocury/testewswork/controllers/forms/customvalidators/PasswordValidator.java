package br.dev.rodrigocury.testewswork.controllers.forms.customvalidators;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Pattern(
    regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
    message = "Por favor, retorne uma senha forte, maior que 8 digitos, pelo menos uma letra maiuscula, 1 minuscula, 1 numero e 1 caractere especial"
)
@NotNull(message = "Senha não deve ser nulo")
@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface PasswordValidator {
  String message() default "Por favor, retorne uma senha forte, maior que 8 digitos, pelo menos uma letra maiuscula, 1 minuscula, 1 numero e 1 caractere especial";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
