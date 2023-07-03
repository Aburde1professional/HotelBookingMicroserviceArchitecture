package com.synergy.component;


import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class BookingClient {
	public JsonNode saveBooking(JsonNode booking) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);        
        HttpEntity<String> request = new HttpEntity<String>(booking.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity("http://localhost:8484/saveBookingInfo", request, JsonNode.class);
        JsonNode object = responseEntity.getBody();
        return object;
    }
	
	public JsonNode getGuestBookingsByUsername(String userName) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/getGuestBookingByUsername/" + userName, Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}

	public JsonNode getSingleBookingById(String bookingId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8484/getSingleBookingById/" + bookingId, Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
}
