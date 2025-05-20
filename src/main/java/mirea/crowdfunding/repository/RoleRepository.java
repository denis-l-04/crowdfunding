package mirea.crowdfunding.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mirea.crowdfunding.entity.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	public Optional<Role> findById(Integer id);
}
