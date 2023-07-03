package com.synergy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.synergy.domain.Hotel;
import com.synergy.domain.Review;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	
	public List<Hotel> findByHotelNameLikeOrAddressLikeOrCityLikeOrStateLike(String hotelName, String address, String city, String state);
	public Review save(Review review);
	
	@Query(value = "SELECT ROUND(avg(reviews.avgRating)) FROM REVIEWS WHERE hotelId = :hotelId", nativeQuery = true)
	public int findAvgReviewByHotelId(int hotelId);
	
	@Query(value = "SELECT REVIEWTEXT FROM REVIEWS WHERE hotelId = :hotelId", nativeQuery = true)
	public List<String> getReviewTextByHotelId(int hotelId);
}