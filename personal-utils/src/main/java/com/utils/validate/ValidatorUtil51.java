package com.utils.validate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 51的验证工具类
 */
public class ValidatorUtil51 {
    public static Validator validator;

    static {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    public static <T> String validate(T t) {
        Set<ConstraintViolation<T>> set = validator.validate(t);
        StringBuilder errors = new StringBuilder();
        if (set.size() > 0) {
            for (ConstraintViolation<T> val : set) {
                errors.append(val.getMessage());
                break;
            }
        }
        return errors.toString();
    }

}
