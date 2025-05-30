package mirea.crowdfunding.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	String name;

	String slug;

	public Category(){}
	
	public Category(String name, String slug){
		this.name = name;
		this.slug = slug;
	}
}
