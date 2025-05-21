package mirea.crowdfunding.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mirea.crowdfunding.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment, Integer>{
	public Optional<Comment> findById(Integer id);
}
