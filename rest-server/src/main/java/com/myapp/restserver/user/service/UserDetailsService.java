package com.myapp.restserver.user.service;

import com.myapp.restserver.user.model.UserDetails;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserDetailsService {

	@Autowired
	DataFactory dataFactory;

	private AtomicInteger ID_GENERATOR = new AtomicInteger();

	public UserDetails generateUsers() {
		return generateUserDetails();
	}

	private UserDetails generateUserDetails() {
		UserDetails userDetails = new UserDetails();
		userDetails.setCity(dataFactory.getCity());
		userDetails.setId(UUID.randomUUID().toString());
		userDetails.setLastName(dataFactory.getLastName());
		userDetails.setNumericId(ID_GENERATOR.getAndIncrement());
		userDetails.setFirstName(dataFactory.getFirstName());
		return userDetails;
	}
}