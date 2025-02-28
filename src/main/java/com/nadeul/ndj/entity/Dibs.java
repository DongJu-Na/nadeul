package com.nadeul.ndj.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dibs {
	
  @Id
  @GeneratedValue
  private Integer diId;
  
  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "memId", insertable = true, updatable = true)
  public Member member;
  
  private LocalDateTime dibsDate;
  
  private String contentId;
  
  private String contentTypeId;
  
  private String contentName;
  
  private String contentImageUrl;
  
  
}
