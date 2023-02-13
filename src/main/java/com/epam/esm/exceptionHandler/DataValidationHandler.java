package com.epam.esm.exceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author orest uzhytchak
 * A class that handle errors catched from hibernate
 * validator
 * */
public class DataValidationHandler<T> {

    /**
     * A method that make a String representation of errors
     * @param elementForValidation takes an object of class that a going to be handled
     * @return string with errors that will visible for user
     * */
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
