$(document).ready(function() {
	
	// Search database for a hotel.
	$("#searchBtn").click(function(){
		let rowNum = 1;
        var searchText = $("#searchLocation").val();
        
        $('#tblHotel tr').not(':first').remove();
        $.get("http://localhost:8282/searchHotel/"+searchText, {
            }, function(responseText) {
                $.each(responseText, function(key1, value1) {
	                $("#tblHotel").append("<tr><td>"+value1.hotelName+"</td><td><img class='hotelImage' src='"+value1.imageURL+"' width='300' heigth='300'/></td><td id='hotelPrice"+rowNum+"'>"+value1.averagePrice+"</td><td id= 'hotelStar"+rowNum+"'>"+value1.starRating+"</td><td id='guestReviewScore'><a href='http://localhost:8282/getReviewForHotel/"+value1.hotelId+"' id='getGuestReviewLink'>Get Guest Review Score</a></td><td id='guestReviewText'><a href='http://localhost:8282/getReviewsForHotel/"+value1.hotelId+"' id='getGuestReviewTextLink'>Get Guest Reviews</a></td></tr>");
	                $("#tblHotel").append("<p hidden id='hotelId"+rowNum+"'>"+value1.hotelId+"</p>");
	                $("#tblHotel").append("<p hidden id='hotelDiscount"+rowNum+"'>"+value1.discount+"</p>");
	                rowNum += 1;
            	});                
         	});   
    });
    
    // Filter hotel results based on filters chosen.
    $("#filterBtn").click(function(){
		let price = parseInt($("#priceRange").val());
		let filterStarRating;
		let searchText = $("#searchLocation").val();
		
		let hotelAmenities;
		
		$.get("http://localhost:8282/searchHotel/"+searchText, function(responseText) {
			hotelAmenities = responseText[0].amenities;
				if ($("#1_star_rating")[0].checked) {
				filterStarRating = 1;
			} else if ($("#2_star_rating")[0].checked) {
				filterStarRating = 2;
			} else if ($("#3_star_rating")[0].checked) {
				filterStarRating = 3;
			} else if ($("#4_star_rating")[0].checked) {
				filterStarRating = 4;
			} else if ($("#5_star_rating")[0].checked) {
				filterStarRating = 5;
			};
		
		$("#tblHotel tr").not(":first").each(function() {
			let rowId = 1;
			$(this).each(function() {
				let hotelPrice = parseInt($("#hotelPrice" + rowId).text());
				let hotelStarRating = parseInt($("#hotelStar" + rowId).text());
				rowId += 1;
				
				if(hotelPrice > price) {
					$(this).hide();
				};
				if (hotelStarRating < filterStarRating) {
					$(this).hide();
				};
			});
		  });
		  
		  $("#tblHotel tr").not(":first").each(function(){
			  let row = $(this);
			  
			  if($("#amenity_parking")[0].checked && !(hotelAmenities.some(obj => obj.name == "Parking"))) {
				row.hide();
			  };
			  
			  if ($("#amenity_checkin_checkout")[0].checked && !(hotelAmenities.some(obj => obj.name == "Check In Check Out"))) {
				row.hide();
			  };
			  
			  if ($("#amenity_breakfast")[0].checked && !(hotelAmenities.some(obj => obj.name == "Breakfast"))) {
				row.hide();
			  };
			  
			  if($("#amenity_bar_lounge")[0].checked && !(hotelAmenities.some(obj => obj.name == "Bar" || obj.name == "Lounge"))) {
			  	row.hide();
			  };
			  
			  if ($("#amenity_fitness_center")[0].checked && !(hotelAmenities.some(obj => obj.name == "Fitness Center"))) {
			  	row.hide();
			  };
		});
	  });
	});
	
	// Display hotel room option information in modal.
	$("#tblHotel").on('click', '.hotelImage', function() {
		let hotelName = $(this).parent().parent().children("td").eq(0).text();
		let hotelRoomTypes;
		let searchText = $("#searchLocation").val();
		
		$.get("http://localhost:8282/searchHotel/"+searchText, function(responseText) {
			hotelRoomTypes = responseText[0].hotelRooms;
		
		$("#myModal").modal();
		$("#modal_hotelName").val(hotelName);
		$("#modal_noGuests").val($("#noGuests").val());
		$("#modal_checkInDate").val($("#checkInDate").val());
		$("#modal_checkOutDate").val($("#checkOutDate").val());
		$("#modal_noRooms").val($("#noRooms").val());
		
		if($("#select_roomTypes")[0].childElementCount == 0) {
			for(let i = 0; i < hotelRoomTypes.length; i += 1) {
			$("#select_roomTypes").append($("<option />").val(hotelRoomTypes[i].type.name).text(hotelRoomTypes[i].type.name));
			}
		  }
		});
	  });
	 
	// Autofill modal information for booking modal.
	$("#myModal").on('click','.btn-searchHotelRooms',function() {
    	let hotelName = $("#modal_hotelName").val();
    	
    	$("#bookingHotelRoomModal").modal();
    	$("#booking_hotelName").val(hotelName);
	    $("#booking_noGuests").val($("#noGuests").val());
	    $("#booking_noRooms").val($("#noRooms").val());
		$("#booking_checkInDate").val($("#checkInDate").val());
		$("#booking_checkOutDate").val($("#checkOutDate").val());
		$("#booking_roomType").val($("#select_roomTypes").val());
		
		let searchText = $("#searchLocation").val();
		$.get("http://localhost:8282/searchHotel/"+searchText, function(responseText) {
						
			if ($("#booking_roomType")[0].value == "Standard") {
				responseText[0].hotelRooms.forEach(roomType => {
					if (roomType.type.name == "Standard") {
						$("#booking_price").text(roomType.price - (roomType.price * (roomType.discount * 0.01)));
						$("#booking_discount").text(roomType.price * (roomType.discount * 0.01));
					}
				});
			}
			else if ($("#booking_roomType")[0].value == "Deluxe") {
				responseText[0].hotelRooms.forEach(roomType => {
					if(roomType.type.name == "Deluxe") {
						$("#booking_price").text(roomType.price - (roomType.price * (roomType.discount * 0.01)));
						$("#booking_discount").text(roomType.price * (roomType.discount * 0.01));
					}
				});
			}	
			 else if ($("#booking_roomType")[0].value == "Executive") {
				responseText[0].hotelRooms.forEach(roomType => {
					if (roomType.type.name == "Executive") {
						$("#booking_price").text(roomType.price - (roomType.price * (roomType.discount * 0.01)));
						$("#booking_discount").text(roomType.price * (roomType.discount * 0.01));	
					}
				});
				}
			});
		});
	
	// Confirm Booking and save to database.
	$("#confirmBookingButton").click(function(event) {
		event.preventDefault();
		$("#bookingGuestModal").modal();
		
		$("#confirmGuest").click(function() {
			let searchText = $("#searchLocation").val();
			
		    $.get("http://localhost:8282/searchHotel/"+searchText, function(responseText) {
				let userName = $("#userName")[0].textContent.trim();
				let hotelId = responseText[0].hotelId;
				let hotelRoomId; 
				let noRooms = $("#modal_noRooms").val();
				let checkInDate = $("#modal_checkInDate").val();
				let checkOutDate = $("#modal_checkOutDate").val();
				let bookedOnDate = new Date().toJSON().slice(0, 10);
				let status;
				if (Date.parse(bookedOnDate) < (Date.parse(checkInDate))) {
					status = "Upcoming";
				} else if (Date.parse(bookedOnDate) > (Date.parse(checkInDate))) {
					status = "Cancelled";
				} else {
					status = "Completed"
				}
				let price;
				let discount;
				let customerMobile = $("#booking_customerMobile").val();
				let roomType;
				let email = "alexsession49@gmail.com";
				let guestInfo = {
					firstName: $("#guestFirstName").val(),
					lastName: $("#guestLastName").val(),
					gender: $("#guestGender").val(),
					age: $("#guestAge").val()
				};
				
				if ($("#select_roomTypes").val() == "Standard") {
					price = $("#booking_price")[0].textContent;
					discount = $("#booking_discount")[0].textContent;
					
					responseText[0].hotelRooms.forEach(roomTypeObj => {
						if (roomTypeObj.type.name == "Standard") {
							hotelRoomId = roomTypeObj.hotelRoomId;
							roomType = roomTypeObj.type.name;
						}
				});
				} else if ($("#select_roomTypes").val() == "Deluxe") {
					price = $("#booking_price")[0].textContent;
					discount = $("#booking_discount")[0].textContent;
					
					responseText[0].hotelRooms.forEach(roomTypeObj => {
						if (roomTypeObj.type.name == "Deluxe") {
							hotelRoomId = roomTypeObj.hotelRoomId;
							roomType = roomTypeObj.type.name;
						}
				});
				}else if ($("#select_roomTypes").val() == "Executive") {
					price = $("#booking_price")[0].textContent;
					discount = $("#booking_discount")[0].textContent;
					
					responseText[0].hotelRooms.forEach(roomTypeObj => {
						if (roomTypeObj.type.name == "Executive") {
							hotelRoomId = roomTypeObj.hotelRoomId;
							roomType = roomTypeObj.type.name;
						}
				});
				};
				
				let bookingData = {
					"userName": userName,
					"hotelId": hotelId,
					"hotelRoomId": hotelRoomId,
					"noRoom": noRooms,
					"checkInDate": checkInDate,
					"checkOutDate": checkOutDate,
					"bookedOnDate": bookedOnDate,
					"status": status,
					"price": price,
					"discount": discount,
					"customerMobile": customerMobile,
					"roomType": roomType,
					"email": email,
					"guests": [guestInfo]
				};
				
				$.ajax({
					url: 'http://localhost:8282/saveBooking',
					method: 'POST',
					contentType: 'application/json',
					dataType: 'json',
					data: JSON.stringify(bookingData)
				});
				
				let emailObj = {
					"recipient": email,
					"msgBody": "Thank you for booking with us!",
					"subject": "Thank you for booking!"
				};
				
				$.ajax({
					url: 'http://localhost:8282/sendMail',
					method: 'POST',
					contentType: 'application/json',
					dataType: 'json',
					data: JSON.stringify(emailObj)
				});
			});
		});
	});
	
	$("#tblHotel").click(function(event) {
		event.preventDefault();
		let rowNum = 0;
		
		if ($(event.target)[rowNum] == $("#getGuestReviewLink")[rowNum]) {
			rowNum += 1;
			let hotelId = $(`#hotelId${rowNum}`)[0].textContent;
			$(event.target).hide();
			
			$.ajax({
				url:`http://localhost:8282/getReviewForHotel/` + hotelId,
				method: "GET",
				success: function(response) {
					$("#guestReviewScore").append(`<p>${response}</p>`)
				}
			})
		}
	});
	
	$("#tblHotel").click(function(event) {
		event.preventDefault();
		let rowNum = 0;
		
		if ($(event.target)[rowNum] == $("#getGuestReviewTextLink")[rowNum]) {
			rowNum += 1;
			let hotelId = $(`#hotelId${rowNum}`)[0].textContent;
			$(event.target).hide();
			
			$.ajax({
				url:`http://localhost:8282/getReviewsForHotel/` + hotelId,
				method: "GET",
				success: function(response) {
					let count = 1;
					response.forEach(review => {
						$("#guestReviewText").append(`<p>${count}. ${review}</p><br>`)
						count += 1;
					});
				}
			})
		}
	});
});