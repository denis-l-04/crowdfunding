package mirea.crowdfunding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import mirea.crowdfunding.entity.Category;
import mirea.crowdfunding.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	@PostConstruct
	public void makeCategories(){
		if (categoryRepository.count() != 0)
			return;
		categoryRepository.save(new Category("Творчество", "creativity"));
		categoryRepository.save(new Category("Социальное", "social"));
		categoryRepository.save(new Category("Инновации", "innovations"));
		categoryRepository.save(new Category("Помощь животным", "help-animals"));
		categoryRepository.save(new Category("Помощь людям", "help-people"));
		categoryRepository.save(new Category("Другое", "other"));
	}
}
