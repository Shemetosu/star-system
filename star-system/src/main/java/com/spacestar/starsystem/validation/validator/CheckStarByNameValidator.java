package com.spacestar.starsystem.validation.validator;

import com.spacestar.starsystem.model.dto.create.StarCreateDto;
import com.spacestar.starsystem.repository.StarRepository;
import com.spacestar.starsystem.validation.CheckStarByName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CheckStarByNameValidator implements ConstraintValidator<CheckStarByName, StarCreateDto> {

    private final StarRepository repository;

    private Boolean isActive;

    @Override
    public void initialize(CheckStarByName annotation) {
        this.isActive = annotation.active();
    }

    @Override
    public boolean isValid(StarCreateDto dto, ConstraintValidatorContext constraintValidatorContext) {
        if (isActive) {
            // если включена, то идем в БД
            // если такой объект уже существует, возвращаем false и ломаем запись в БД
            return !repository.existsStarByName(dto.getName());
        } else {
            // если отключена, глушим проверку
            return true;
        }
    }
}
