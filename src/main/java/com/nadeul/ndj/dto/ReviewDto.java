package com.nadeul.ndj.dto;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.nadeul.ndj.entity.Review;
import com.nadeul.ndj.entity.ReviewGrade;
import com.nadeul.ndj.entity.ReviewLike;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Schema(description = "리뷰 DTO")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
	
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class ListRequest {
    	  @Schema(description = "리뷰 콘텐츠 번호" , example = "1")
    	  private String contentId;

    }

    @Getter
    public static class ListResponse {
	  @Schema(description = "여행 리뷰 목록" , example = "객체 반환")
	  private List<ReviewDto> review;
	  
	  public ListResponse(List<ReviewDto> review) {
	        this.review = review;
	  }
	  
	  

    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class CreateUpdateDto {
    	@Schema(description = "리뷰 번호" , example = "1")
	    private String rvId;
    	@Schema(description = "리뷰 콘텐츠 번호" , example = "1")
	    private String contentId;
    	@Schema(description = "리뷰 내용" , example = "1")
	    private String content;
    	@Schema(description = "리뷰 사진" , example = "")
    	private MultipartFile reviewImage;
    	@Schema(description = "리뷰 평점" , example = "0.0" , defaultValue = "0.0")
    	private String reviewRating;
    	
    	// 포인트 적립
    	@Schema(description = "tourAPI 위도")
    	private Float posX;
    	@Schema(description = "tourAPI 경도")
    	private Float posY;
    	@Schema(description = "실제 위도")
    	private Float realPosX;
    	@Schema(description = "실제 경도")
    	private Float realPosY;
    	@Schema(description = "관광지 타입 ID" , example = "17")
        private String contentTypeId;
    	@Schema(description = "지역코드" , example = "1")
        private String areaCode;
    	

    }
    
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class reviewLikeDto {
    	@Schema(description = "리뷰 번호" , example = "1")
	    private Integer rvId;
    }
    
    private Integer rvId;
    private String contentId;
    private String content;
    private String reviewImageUrl;
    private LocalDateTime createDate;
    private String createBy;
    private LocalDateTime updateDate;
    private String updateBy;
    private MemberDto member;
    private List<ReviewGrade> reviewGrade;
    private List<ReviewLike> reviewLike;
    private int like;
    
    public ReviewDto(Review review) {
        this.rvId = review.getRvId();
        this.contentId = review.getContentId();
        this.content = review.getContent();
        this.reviewImageUrl = review.getReviewImageUrl();
        this.createDate = review.getCreateDate();
        this.createBy = review.getCreateBy();
        this.updateDate = review.getUpdateDate();
        this.updateBy = review.getUpdateBy();
        this.reviewGrade = review.getReviewGrade();
        this.reviewLike = review.getReviewLike();
        this.member = new MemberDto(review.getMember());
        if (review.getReviewLike() != null) {
            this.like = review.getReviewLike().stream()
              .mapToInt(reviewLike -> reviewLike.getLikes())
              .sum();
          } else {
            this.like = 0;
          }
      }

    
}