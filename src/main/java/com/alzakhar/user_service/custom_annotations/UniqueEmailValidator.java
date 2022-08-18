package com.alzakhar.user_service.custom_annotations;

import com.alzakhar.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userService.emailExists(email);
    }
}
