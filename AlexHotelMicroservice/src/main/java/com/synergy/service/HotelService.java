package com.synergy.service;

import java.util.List;

import com.synergy.domain.Hotel;

public interface HotelService {
	public List<Hotel> searchHotel(String searchString);
	public int getHotelReviewAverage(int hotelId);
	public Hotel saveHotel(Hotel hotel);
}