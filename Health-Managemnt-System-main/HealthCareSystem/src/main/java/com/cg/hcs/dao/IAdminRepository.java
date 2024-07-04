package com.cg.hcs.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.hcs.model.User;
@Repository
public interface IAdminRepository extends JpaRepository<User, Integer> {
	@Query("Select u from User u where u.username=?1 and u.role=?2")
	public User findUserByName(String uname, String role);
	

}
