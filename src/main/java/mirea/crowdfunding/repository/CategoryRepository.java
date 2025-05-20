package mirea.crowdfunding.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mirea.crowdfunding.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer>  {
	public Optional<Category> findById(int id);
}
