package com.nadeul.ndj.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nadeul.ndj.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
	 List<Review> findByContentId(String contentId);
	 
	 @Query("SELECT AVG(rg.rating) FROM Review r JOIN r.reviewGrade rg WHERE r.contentId = :contentId")
	 BigDecimal findAverageRatingByContentId(@Param("contentId") String contentId);
	 
	 Optional<Review> findByRvIdAndContentIdAndMember_MemId(Integer rvId, String contentId, Integer memId);
}
