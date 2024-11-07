package com.spacestar.starsystem.model.entity;

import com.spacestar.starsystem.model.entity.parent.TypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Planet extends TypeEntity {

    @Column(name = "star_id", insertable = false, updatable = false)
    private Long starId;

    private Boolean populated;
    private Long population;

    @ManyToOne
    @JoinColumn(
            name = "star_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_planet_to_star")
    )
    private Star star;

    @OneToMany(mappedBy = "planet")
    private List<Satellite> satelliteList;
}
