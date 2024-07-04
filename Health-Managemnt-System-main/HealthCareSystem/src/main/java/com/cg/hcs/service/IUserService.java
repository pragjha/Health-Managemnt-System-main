package com.cg.hcs.service;

import org.springframework.http.ResponseEntity;

import com.cg.hcs.exception.UserException;
import com.cg.hcs.model.User;

public interface IUserService {
	public ResponseEntity<User> validateUser(String username, String password) throws UserException;
	public ResponseEntity<User> addUser(User user) throws UserException;
	public ResponseEntity<User> removeUser(User user) throws UserException;

}