package com.cg.hcs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hcs.model.User;
@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u where u.username=?1 and u.password=?2")
	public User validateUser(String username, String password);
	

}
