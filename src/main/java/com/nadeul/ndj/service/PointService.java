package com.nadeul.ndj.service;

import org.springframework.stereotype.Service;

import com.nadeul.ndj.repository.PointRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointService {
	private static final int EARTH_RADIUS = 6371; // 지구의 반지름 (단위: km)
	private final PointRepository pointRepository;
	private final pointhi temp;
	
	
  // 위도 경도 계산
  public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    double dLat = Math.toRadians(lat2 - lat1);
    double dLon = Math.toRadians(lon2 - lon1);
    double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = EARTH_RADIUS * c * 1000.0; // km를 m로 변환
    return distance;
  }
  
}
