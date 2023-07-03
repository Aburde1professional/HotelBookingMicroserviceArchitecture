package com.synergy.service;

import com.synergy.dto.EmailDetails;

public interface EmailService {

	public String sendSimpleMail(EmailDetails details);
	public String sendMailWithAttachment(EmailDetails details);
	
}