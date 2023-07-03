package com.synergy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.domain.Hotel;
import com.synergy.domain.Review;
import com.synergy.service.IHotelService;
import com.synergy.service.ReviewServiceImpl;

@RestController
public class HotelController {
	
	@Autowired
	IHotelService hotelService;
	
	@Autowired
	ReviewServiceImpl reviewService;
	
	@RequestMapping(value = "/searchHotel/{searchString}", method = RequestMethod.GET)
	public List<Hotel> searchHotel(@PathVariable String searchString) {
		return hotelService.searchHotel(searchString);
	}
	
	@RequestMapping(value = "/saveReview", method = RequestMethod.POST)
	public Review saveReview(@RequestBody Review review) {
		return reviewService.saveReview(review);
	}
	
	@RequestMapping(value = "/getHotelReviewAverage/{hotelId}", method = RequestMethod.GET)
	public int getHotelReviewAverage(@PathVariable int hotelId) {
		return reviewService.getHotelReviewAverage(hotelId);
	}
	
	@RequestMapping(value = "getHotelReviews/{hotelId}", method = RequestMethod.GET)
	public List<String> getHotelReviewText(@PathVariable int hotelId) {
		return reviewService.getHotelReviewText(hotelId);
	}
}