package com.synergy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergy.domain.Booking;
import com.synergy.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Override
	public Booking saveBooking(Booking b) {
		return bookingRepository.save(b);
	}
	
	@Override
	public List<Booking> getGuestBookings(String userName) {
		return bookingRepository.findBookingByUsername(userName);
	}

	public Booking getBookingById(int bookingIdDb) {
		return bookingRepository.findBookingBybookingId(bookingIdDb);
	}
}