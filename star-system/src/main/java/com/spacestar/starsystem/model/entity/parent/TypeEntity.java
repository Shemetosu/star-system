package com.spacestar.starsystem.model.entity.parent;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class TypeEntity extends NamedEntity {

    @Column(name = "type")
    private String type;
}
