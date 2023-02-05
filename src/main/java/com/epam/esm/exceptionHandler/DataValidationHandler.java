package com.epam.esm.exceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class DataValidationHandler<T> {

    public String errorsRepresentation(T elementForValidation){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(elementForValidation);
        StringBuilder errors = new StringBuilder();
        for (ConstraintViolation<T> violation : violations) {
            errors.append(violation.getMessage()).append("; ");
        }
        return errors.toString();
    }
}
