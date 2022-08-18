package com.alzakhar.user_service.custom_annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniquePhoneValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePhoneNumber {
    String message() default "User with this phone number has already been registered";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
