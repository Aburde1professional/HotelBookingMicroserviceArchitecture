package com.synergy.component;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ReviewClient {

	public JsonNode saveReview(JsonNode review) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);        
        HttpEntity<String> request = new HttpEntity<String>(review.toString(), headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity("http://localhost:8383/saveReview", request, JsonNode.class);
        JsonNode object = responseEntity.getBody();
        return object;
	}
	
	public JsonNode getHotelReviews(int hotelId){
		System.out.println("Inside rest client");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/getHotelReviewAverage/" + hotelId, Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
	public JsonNode getHotelReviewText(int hotelId){
		System.out.println("Inside rest client");
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Object> responseEntity = restTemplate.getForEntity("http://localhost:8383/getHotelReviews/" + hotelId, Object.class);
		
		Object objects = responseEntity.getBody();
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode returnObj = mapper.convertValue(objects, JsonNode.class);
		return returnObj;
	}
	
}
