package com.nadeul.ndj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nadeul.ndj.entity.Point;

public interface PointRepository extends JpaRepository<Point, Integer> {
	
}
