package com.springthymeleaf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springthymeleaf.repository.UserRespository;
import com.springthymeleaf.user.User;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRespository userRepository;
	@Override
	public boolean addUser(User user) {

		boolean status=false;
		try {
			userRepository.save(user);
			status =true;
		} catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	@Override
	public User loginUser(String email, String password) {
		try {
			User loggedUser = userRepository.findByEmail(email);
			if(loggedUser!=null && loggedUser.getPassword().equals(password)) {
				return loggedUser;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
