package com.synergy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergy.domain.Review;
import com.synergy.repository.HotelRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	HotelRepository hotelRepository;
	
	@Override
	public Review saveReview(Review review) {
		return hotelRepository.save(review);
	}

	@Override
	public int getHotelReviewAverage(int hotelId) {
		return hotelRepository.findAvgReviewByHotelId(hotelId);
	}

	@Override
	public List<String> getHotelReviewText(int hotelId) {
		return hotelRepository.getReviewTextByHotelId(hotelId);
	}
	
}
