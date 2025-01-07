package com.springthymeleaf.service;

import com.springthymeleaf.user.User;

public interface UserService {

	public boolean addUser(User user);
	public User loginUser(String email,String password);
}
