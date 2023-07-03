package com.synergy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergy.domain.Hotel;
import com.synergy.repository.HotelRepository;

@Service
public class IHotelService implements HotelService {
	
	@Autowired
	HotelRepository hotelRepository;
	
	@Override
	public List<Hotel> searchHotel(String searchString) {
		searchString= "%"+searchString+"%";
		return hotelRepository.findByHotelNameLikeOrAddressLikeOrCityLikeOrStateLike(searchString, searchString, searchString, searchString);
	}
	
	@Override
	public int getHotelReviewAverage(int hotelId) {
		return hotelRepository.findAvgReviewByHotelId(hotelId);
	}

	@Override
	public Hotel saveHotel(Hotel hotel) {
		return hotelRepository.save(hotel);
	}
}