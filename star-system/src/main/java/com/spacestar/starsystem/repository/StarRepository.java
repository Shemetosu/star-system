package com.spacestar.starsystem.repository;

import com.spacestar.starsystem.model.entity.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends JpaRepository<Star, Long>, JpaSpecificationExecutor<Star> {

    boolean existsStarByName(String name);
}
