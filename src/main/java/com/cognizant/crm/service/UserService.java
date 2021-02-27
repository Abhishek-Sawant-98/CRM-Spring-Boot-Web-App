package com.cognizant.crm.service;

import org.springframework.validation.BindingResult;

import com.cognizant.crm.model.User;

public interface UserService {

	public boolean isUserAuthentic(User user);

	public boolean signUp(User user);

	public void validate(User user, BindingResult results);

	public User getUserById(String userId);

}
