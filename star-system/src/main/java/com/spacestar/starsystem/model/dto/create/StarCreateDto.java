package com.spacestar.starsystem.model.dto.create;

import com.spacestar.starsystem.validation.CheckStarByName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@CheckStarByName
public class StarCreateDto {

    @Length(min = 2, message = "Имя не должно быть меньше двух символов")
    private String name;

    @Length(min = 2, message = "Тип не должен быть меньше двух символов")
    private String type;
}
