package com.alzakhar.user_service.custom_annotations;

import com.alzakhar.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniquePhoneValidator implements ConstraintValidator<UniquePhoneNumber, String>{

    @Autowired
    UserService userService;

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        return !userService.phoneNumberExists(phoneNumber);
    }
}
