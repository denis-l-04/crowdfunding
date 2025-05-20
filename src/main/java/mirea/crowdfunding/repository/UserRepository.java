package mirea.crowdfunding.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mirea.crowdfunding.entity.User;


public interface UserRepository extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
	public Optional<User> findById(int id);
	public List<User> findAll();
}