package com.crm.repository;

import com.crm.model.User;

public interface UserDao {

	public boolean signUp(User user);

	public boolean isUserAuthentic(User user);

	public boolean isUserAlreadyRegistered(String userId);

	public User getUserById(String userId);

}
