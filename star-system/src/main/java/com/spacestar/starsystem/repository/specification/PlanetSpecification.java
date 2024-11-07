package com.spacestar.starsystem.repository.specification;

import com.spacestar.starsystem.model.entity.Planet;
import com.spacestar.starsystem.model.entity.Star;
import jakarta.persistence.criteria.Join;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class PlanetSpecification {

    public static Specification<Planet> findAllByStarId(Long starId) {
        return (planet, criteriaQuery, criteriaBuilder) -> {
            Join<Planet, Star> star = planet.join("star");
            return criteriaBuilder.equal(star.get("id"), starId);
        };
    }
}
