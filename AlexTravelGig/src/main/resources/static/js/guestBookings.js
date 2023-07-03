$(document).ready(function() {
	$("#Completed").hide();
	$("#Cancelled").hide();
	$("#reviewModal").hide();
		
	$("#CancelledTab").click(function() {
		$("#Upcoming").hide();
		$("#Completed").hide();
		$("#Cancelled").show();
	});
	
	$("#UpcomingTab").click(function() {
		$("#Cancelled").hide();
		$("#Completed").hide();
		$("#Upcoming").show();
	});
	
	$("#CompletedTab").click(function() {
		$("#Upcoming").hide();
		$("#Cancelled").hide();
		$("#Completed").show();
	});
	
	let userName = $("#userName")[0].textContent;
		
	$.ajax({
		url: "http://localhost:8282/getGuestBookingsByUsername/" + userName,
		method: "GET",
		success: function(response) {
			for (let i = 0; i < response.length; i += 1) {
				if (response[i].status == "Upcoming") {
					$("#upcomingBookings").append(`<tr><td id="bookingId">${response[i].bookingId}</td><td>${response[i].roomType}</td><td>${response[i].checkInDate}</td>
					<td>${response[i].checkOutDate}</td><td>${response[i].status}</td><td><input id="cancelBookingButton" type="submit" value="Cancel"></td>
					<td><input type="submit" id="completeBookingButton" value="Complete"></td></tr>`);
				} else if (response[i].status == "Cancelled") {
					$("#cancelledBookings").append(`<tr><td id="bookingId">${response[i].bookingId}</td><td>${response[i].roomType}</td><td>${response[i].checkInDate}</td>
					<td>${response[i].checkOutDate}</td><td>${response[i].status}</td></tr>`);
				} else if (response[i].status == "Completed") {
					$("#completedBookings").append(`<tr><td id="bookingId">${response[i].bookingId}</td><td>${response[i].roomType}</td><td>${response[i].checkInDate}</td>
					<td>${response[i].checkOutDate}</td><td>${response[i].status}</td><td><input type="submit" id="reviewButton" value ="Leave Review"></td></input></tr>`);
				}
			}
		}
	});
	
	$("#upcomingBookings").click(function(event) {
		if (event.target.id == "completeBookingButton") {
			let bookingId = ($(event.target).parent().siblings()[0].textContent);
		$.ajax({
			url: "http://localhost:8282/getGuestBookingById/" + bookingId,
			method: "GET",
			success: function(response){
					let updateBookingData = {
					"bookingId": response.bookingId,
					"userName": response.userName,
					"hotelId": response.hotelId,
					"hotelRoomId": response.hotelRoomId,
					"noRoom": response.noRooms,
					"checkInDate": response.checkInDate,
					"checkOutDate": response.checkOutDate,
					"bookedOnDate": response.bookedOnDate,
					"status": "Completed",
					"price": response.price,
					"discount": response.discount,
					"customerMobile": response.customerMobile,
					"roomType": response.roomType,
					"email": response.email,
					"guests": response.guestInfo
				};
				$.ajax({
					url: 'http://localhost:8282/saveBooking',
					method: 'POST',
					contentType: 'application/json',
					dataType: 'json',
					data: JSON.stringify(updateBookingData)
				})
			}
		});
		} else if (event.target.id == "cancelBookingButton") {
			let bookingId = ($(event.target).parent().siblings()[0].textContent);
			$.ajax({
				url: "http://localhost:8282/getGuestBookingById/" + bookingId,
				method: "GET",
				success: function(response){
						let updateBookingData = {
						"bookingId": response.bookingId,
						"userName": response.userName,
						"hotelId": response.hotelId,
						"hotelRoomId": response.hotelRoomId,
						"noRoom": response.noRooms,
						"checkInDate": response.checkInDate,
						"checkOutDate": response.checkOutDate,
						"bookedOnDate": response.bookedOnDate,
						"status": "Cancelled",
						"price": response.price,
						"discount": response.discount,
						"customerMobile": response.customerMobile,
						"roomType": response.roomType,
						"email": response.email,
						"guests": response.guestInfo
					};
				$.ajax({
					url: 'http://localhost:8282/saveBooking',
					method: 'POST',
					contentType: 'application/json',
					dataType: 'json',
					data: JSON.stringify(updateBookingData)
				})
			}
		});
		};
	});
	
	$("#completedBookings").click(function(event) {
		if(event.target.value == "Leave Review") {
			let bookingId = $(event.target).parent().siblings()[0].textContent;
			$("#reviewModal").show();
			$("#completedBookings").hide();
			$("#reviewModalContent").append(`<h1 hidden id="hiddenBookingId">${bookingId}</h1>`)
		}
	});
	
	$("#submitReview").click(function(){
		let amenitiesRating = $("#amenitiesRating").val();
		let parkingRating = $("#parkingRating").val();
		let serviceRating = $("#serviceRating").val();
		let cleanlinessRating = $("#cleanlinessRating").val();
		let bookingId = $("#hiddenBookingId")[0].textContent;
		let hotelId;
		let userName;
		let avgRating = ((parseInt(amenitiesRating) + parseInt(parkingRating) + parseInt(serviceRating) + parseInt(cleanlinessRating)) / 4);
		let reviewText = $("#textReview").val();
		
			$.ajax({
				url: "http://localhost:8282/getGuestBookingById/" + bookingId,
				method: "GET",
				success: function(response){
						hotelId = response.hotelId;
						userName = response.userName;

						let reviewData = {
							"amenitiesRating": amenitiesRating,
							"parkingRating": parkingRating,
							"serviceRating": serviceRating,
							"cleanlinessRating": cleanlinessRating,
							"bookingId": bookingId,
							"hotelId": hotelId,
							"userName": userName,
							"avgRating": avgRating,
							"reviewText": reviewText
						};
						
						$.ajax({
							url: "http://localhost:8282/saveReviewForHotel",
							method: "POST",
							contentType: "application/json",
							dataType: "json",
							data: JSON.stringify(reviewData)
						});
					}
			});
			$("#reviewModal").hide();
			$("#completedBookings").show();
	})
});

