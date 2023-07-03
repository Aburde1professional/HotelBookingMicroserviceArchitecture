package com.synergy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergy.domain.Review;

@Service
public interface ReviewService {
	public Review saveReview(Review review);
	int getHotelReviewAverage(int hotelId);
	List<String> getHotelReviewText(int hotelId);
}
