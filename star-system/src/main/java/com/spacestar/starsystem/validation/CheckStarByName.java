package com.spacestar.starsystem.validation;

import com.spacestar.starsystem.validation.validator.CheckStarByNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckStarByNameValidator.class)
public @interface CheckStarByName {

    String message() default "Звезда с таким именем уже существует";

    boolean active() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
