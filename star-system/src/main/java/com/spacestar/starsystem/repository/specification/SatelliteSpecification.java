package com.spacestar.starsystem.repository.specification;

import com.spacestar.starsystem.model.entity.Planet;
import com.spacestar.starsystem.model.entity.Satellite;
import com.spacestar.starsystem.model.entity.Star;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class SatelliteSpecification {

    public static Specification<Satellite> findAllByPlanetId(Long planetId) {
        return (satellite, criteriaQuery, criteriaBuilder) -> {
            Join<Satellite, Planet> planet = satellite.join("planet");
            return criteriaBuilder.equal(planet.get("id"), planetId);
        };
    }

    public static Specification<Satellite> findAllByStarId(Long starId) {
        return (satellite, criteriaQuery, criteriaBuilder) -> {
            Join<Satellite, Planet> planet = satellite.join("planet");
            Join<Planet, Star> star = planet.join("star");
            return criteriaBuilder.equal(star.get("id"), starId);
        };
    }
}
