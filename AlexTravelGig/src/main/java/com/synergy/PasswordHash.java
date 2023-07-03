package com.synergy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHash {
	
	public static void main(String []args) { 
		testByCryptHash();
	}
	
	public static void testByCryptHash() {
		String password = "Alex";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
	}
}
