package com.cg.hcs.service;

import org.springframework.http.ResponseEntity;

import com.cg.hcs.exception.UserException;
import com.cg.hcs.model.User;

public interface IAdminService {
	public ResponseEntity<User> registerUser(String username, String password) throws UserException;

}

