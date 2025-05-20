package mirea.crowdfunding.entity;

import java.util.Set;

import jakarta.persistence.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

@Entity
public class Role implements GrantedAuthority {
	@Id
	private Long id;
	@Column(nullable = false, unique = true)
	private String name;
	@JsonIgnore
	@Transient
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;
	public Role() {
	}

	public Role(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String getAuthority() {
		return getName();
	}
}
