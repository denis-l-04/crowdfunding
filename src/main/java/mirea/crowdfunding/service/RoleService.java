package mirea.crowdfunding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import mirea.crowdfunding.entity.Role;
import mirea.crowdfunding.repository.RoleRepository;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
	@Bean("ROLE_USER")
	public Role getUserRole(){
		Role ur = roleRepository.findByName("ROLE_USER").orElse(null);
		if (ur != null)
			return ur;
		ur = new Role();
		ur.setName("ROLE_USER");
		roleRepository.save(ur);
		return ur;
	}
}
