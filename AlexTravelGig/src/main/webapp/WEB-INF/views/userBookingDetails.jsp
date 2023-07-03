<head>
    <meta charset="ISO-8859-1">
    <title>Home Page of Travel Gig</title>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="./js/guestBookings.js"></script>
</head>

<body>
  <p hidden id="userName">${userName}</p>
    <div class="tab">
        <button class="tablinks" id="UpcomingTab">Upcoming</button>
        <button class="tablinks" id="CompletedTab">Completed</button>
        <button class="tablinks" id="CancelledTab">Cancelled</button>
      </div>
      
      <!-- Tab content -->
      <div id="Upcoming" class="tabcontent">
        <table id="upcomingBookings">
          <tr id="columnNamesUpcoming">
            <th id="bookingIdUpcoming">Booking Id</th>
            <th id="roomTypeUpcoming">Room Type</th>
            <th id="checkInDateUpcoming">Check In Date</th>
            <th id="checkOutDateUpcoming">Check Out Date</th>
            <th id="statusUpcoming">Booking Status</th>
            <th id="cancelBooking">Cancel Booking</th>
            <th id="completeBooking">Complete Booking</th>
          </tr>
        </table>
      </div>
      
      <div id="Completed" class="tabcontent">
        <table id = "completedBookings">
          <tr id="columnNamesCompleted">
            <th id="bookingIdCompleted">Booking Id</th>
            <th id="roomTypeCompleted">Room Type</th>
            <th id="checkInDateCompleted">Check In Date</th>
            <th id="checkOutDateCompleted">Check Out Date</th>
            <th id="statusCompleted">Booking Status</th>
            <th id="reviewLink">Leave a Review</th>
          </tr>
        </table>
      </div>
      
      <div id="Cancelled" class="tabcontent">
        <table id="cancelledBookings">
          <tr id="columnNamesCancelled">
            <th id="bookingIdCancelled">Booking Id</th>
            <th id="roomTypeCancelled">Room Type</th>
            <th id="checkInDateCancelled">Check In Date</th>
            <th id="checkOutDateCancelled">Check Out Date</th>
            <th id="statusCancelled">Booking Status</th>
          </tr>
        </table>
      </div>

      <div id="reviewModal" class="modal">
        <div class="modal-content" id="reviewModalContent">
          <h1>Please rate each question from 1 - 5; 1 being poor, and 5 being exemplary.</h1>
          <label for ="amenitiesRating">How were the Amenities at your hotel?</label>
          <select name="amenitiesRating" id="amenitiesRating">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select>

          <label for ="parkingRating">How would you rate the parking?</label>
          <select name="parkingRating" id="parkingRating">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select>

          <label for ="serviceRating">Overall, how was the service provided by hotel staff?</label>
          <select name="serviceRating" id="serviceRating">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select>

          <label for ="cleanlinesssRating">How clean were your rooms and the hotel in general?</label>
          <select name="cleanlinessRating" id="cleanlinessRating">
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
          </select><br>
          <label for="textReview">Is there anything else you'd like to tell us about your experience?</label>
          <textarea id="textReview">Write your other thoughts here!</textarea><br><br>
          <input id="submitReview" type="submit" value="Submit Review">
        </div>
      </div>
</body>