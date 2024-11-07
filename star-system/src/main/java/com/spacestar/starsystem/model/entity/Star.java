package com.spacestar.starsystem.model.entity;

import com.spacestar.starsystem.model.entity.parent.TypeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "star")
public class Star extends TypeEntity {

    @OneToMany(mappedBy = "star")
    private List<Planet> planetList;
}
