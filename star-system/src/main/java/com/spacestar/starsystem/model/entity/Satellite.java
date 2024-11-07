package com.spacestar.starsystem.model.entity;

import com.spacestar.starsystem.model.entity.parent.NamedEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Satellite extends NamedEntity {

    @Column(name = "planet_id", insertable = false, updatable = false)
    private Long planetId;

    @ManyToOne
    @JoinColumn(
            name = "planet_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_satellite_to_planet")
    )
    private Planet planet;
}
