package com.synergy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synergy.domain.Booking;
import com.synergy.service.BookingServiceImpl;

@RestController
public class BookingController {
	
	@Autowired
	BookingServiceImpl bookingService;
	
	@RequestMapping(value = "/saveBookingInfo", method = RequestMethod.POST)
	public Booking saveBooking(@RequestBody Booking booking) {
		return bookingService.saveBooking(booking);
	}
	
	@RequestMapping(value = "/getGuestBookingByUsername/{userName}", method = RequestMethod.GET)
	public List<Booking> getGuestBooking(@PathVariable String userName) {
		return bookingService.getGuestBookings(userName);
	}
	
	@RequestMapping(value="getSingleBookingById/{bookingId}", method = RequestMethod.GET)
	public Booking getBookingById(@PathVariable String bookingId) {
		int bookingIdDb = Integer.parseInt(bookingId);
		return bookingService.getBookingById(bookingIdDb);
	}
}
