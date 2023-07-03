package com.synergy.controller;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.synergy.component.BookingClient;
import com.synergy.component.HotelComponent;
import com.synergy.component.ReviewClient;
import com.synergy.domain.Hotel;
import com.synergy.dto.EmailDetails;
import com.synergy.service.EmailServiceImpl;


@RestController
public class GatewayController {
	
	@Autowired
	HotelComponent hotelComponent;
	
	@Autowired
	EmailServiceImpl emailService;
	
	@Autowired
	BookingClient bookingClient;
	
	@Autowired
	ReviewClient reviewClient;
	
	@RequestMapping(value = "/searchHotel/travelGig", method = RequestMethod.GET)
	public JsonNode searchHotel(@PathVariable String searchString,Principal principal){
		System.out.println("Welcome------------------"+principal.getName());
		return hotelComponent.findHotelBySearchText(searchString);
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(@RequestBody EmailDetails emailDetails){
		return emailService.sendSimpleMail(emailDetails);
	}
	
	@RequestMapping(value = "/saveBooking", method = RequestMethod.POST)
	public JsonNode saveBooking(@RequestBody JsonNode booking) {
		return bookingClient.saveBooking(booking);
	}
	
	@RequestMapping(value = "/getGuestBookingsByUsername/{userName}", method = RequestMethod.GET)
	public JsonNode getGuestBookings(@PathVariable String userName) {
		return bookingClient.getGuestBookingsByUsername(userName);
	}
	
	@RequestMapping(value = "/getGuestBookingById/{bookingId}", method = RequestMethod.GET)
	public JsonNode getGuestBookingById(@PathVariable String bookingId) {
		return bookingClient.getSingleBookingById(bookingId);
	}
	
	@RequestMapping(value = "/saveReviewForHotel", method = RequestMethod.POST)
	public JsonNode saveReview(@RequestBody JsonNode review) {
		return reviewClient.saveReview(review);
	}
	
	@RequestMapping(value = "/getReviewForHotel/{hotelId}", method = RequestMethod.GET)
	public JsonNode getReviewsForHotel(@PathVariable int hotelId) {
		return reviewClient.getHotelReviews(hotelId);
	}
	
	@RequestMapping(value = "/getReviewsForHotel/{hotelId}", method = RequestMethod.GET)
	public JsonNode getReviewTextForHotel(@PathVariable int hotelId) {
		return reviewClient.getHotelReviewText(hotelId);
	}
}