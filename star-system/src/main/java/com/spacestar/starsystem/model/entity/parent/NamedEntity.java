package com.spacestar.starsystem.model.entity.parent;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class   NamedEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;
}
