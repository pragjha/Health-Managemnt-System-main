package com.cg.hcs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.hcs.dao.IUserRepository;
import com.cg.hcs.exception.UserException;
import com.cg.hcs.model.User;

@Service
public class IUserServiceImpl implements IUserService {

	@Autowired
	IUserRepository repo;
	@Override
	public ResponseEntity<User> validateUser(String username, String password) throws UserException {
		User u = repo.validateUser(username, password);
		if(u==null) {
			throw new UserException("Invalid username and password");
		}
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> addUser(User user) throws UserException {
		if(repo.existsById(user.getId())) {
			throw new UserException("user with the given details already exists");
		}
		repo.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> removeUser(User user) throws UserException {
		if(!repo.existsById(user.getId())) {
			throw new UserException("user with the given details doesn't exists");
		}
		repo.delete(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

}
