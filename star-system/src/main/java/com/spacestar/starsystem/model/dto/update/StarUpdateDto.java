package com.spacestar.starsystem.model.dto.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class StarUpdateDto {

    @NotNull(message = "Поле не может быть null")
    @Positive(message = "Значения только от 1")
    @Min(value = 1, message = "Значения не ниже 1")
    private Long id;

    @Length(min = 2, message = "Имя не должно быть меньше двух символов")
    private String name;

    @Length(min = 2, message = "Тип не должен быть меньше двух символов")
    private String type;
}
