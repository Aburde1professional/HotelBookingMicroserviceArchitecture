package com.synergy.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.synergy.domain.Booking;

@Service
public interface BookingService {
	
	public Booking saveBooking(Booking b);
	public List<Booking> getGuestBookings(String userName);
}
