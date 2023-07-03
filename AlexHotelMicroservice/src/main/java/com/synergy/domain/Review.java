package com.synergy.domain;

import jakarta.persistence.*;

@Entity
@Table(name="Reviews")
public class Review {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reviewId;
	
	private int hotelId;
	private String userName;
	private int bookingId;
	
	private int amenitiesRating;
	private int parkingRating;
	private int serviceRating;
	private int cleanlinessRating;
	public float avgRating;
	private String reviewText;

	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	
	public int getAmenitiesRating() {
		return amenitiesRating;
	}
	public void setAmenitiesRating(int amenitiesRating) {
		this.amenitiesRating = amenitiesRating;
	}
	
	public int getParkingRating() {
		return parkingRating;
	}
	public void setParkingRating(int parkingRating) {
		this.parkingRating = parkingRating;
	}
	
	public int getServiceRating() {
		return serviceRating;
	}
	public void setServiceRating(int serviceRating) {
		this.serviceRating = serviceRating;
	}
	
	public int getCleanlinessRating() {
		return cleanlinessRating;
	}
	public void setCleanlinessRating(int cleanlinessRating) {
		this.cleanlinessRating = cleanlinessRating;
	}
	
	public float getAverageRating() {
		return avgRating;
	}
	public void setAverageRating(float averageRating) {
		this.avgRating = averageRating;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
}
