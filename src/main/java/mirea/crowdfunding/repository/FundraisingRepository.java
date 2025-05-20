package mirea.crowdfunding.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import mirea.crowdfunding.entity.Fundraising;

public interface FundraisingRepository extends CrudRepository<Fundraising, Integer> {
	public List<Fundraising> findAll();
	public Optional<Fundraising> findById(Integer id);
}
