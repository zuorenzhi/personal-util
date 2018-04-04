package com.utils.validate;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
* Description : [对象验证工具类,如果验证的类中套用类，需在被引用的类上加注解@Valid,依赖1|3(容器需要2)jar]
* Created on : 2017-08-16 19:49
* author : [池猛]
* version : 1.0
* Copyright (c) 2017 国美金控-美借
*/
public class ValidateUtil {

    /**
     * Discription: [验证某个bean的参数]
     * Created on: 2017-08-16 19:48
     * param : [t] 被校验的参数
     * return : java.lang.String  验证信息
     * author : [池猛]
     */
    public static <T> String validate(T t){

        StringBuilder errorMsg = new StringBuilder();
        //获得验证器
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //执行验证
        Set<ConstraintViolation<T>> errors = validator.validate(t);

        errors.stream().forEach(x->errorMsg.append(x.getMessage()).append(";"));

        return errorMsg.toString();
    }
}
