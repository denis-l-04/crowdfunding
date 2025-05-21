package mirea.crowdfunding.controller;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mirea.crowdfunding.entity.Category;
import mirea.crowdfunding.entity.User;
import mirea.crowdfunding.repository.CategoryRepository;
import mirea.crowdfunding.repository.UserRepository;
import mirea.crowdfunding.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
	@Autowired
	private CategoryRepository categoryRepository;

	@GetMapping("/test")
	public @ResponseBody Category getAllUsers() {
		return categoryRepository.findById(Integer.valueOf(6)).get();
	}

	@PostMapping("/register")
	public @ResponseBody String addNewUser (@RequestBody String payload){
		User user;
		try {
			user = objectMapper.readValue(payload, User.class);
		} catch (Exception e) {
			return "Invalid object, not saved.";
		}
		if (userService.saveUser(user))
			return "Saved.";
		else
			return "Invalid parameters, not saved.";
	}

	@PostMapping("/login")
	public @ResponseBody String loginUser(@RequestBody String payload){
		return "Wellcome";
	}

	@GetMapping("/me")
	public @ResponseBody User getMe() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof User) {
			return (User)principal;
		} else {
			return null;
		}
	}

	@PostMapping("/up-balance")
	public @ResponseBody String upBalance(@RequestBody HashMap<String, Long> payload){
		User u = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!payload.containsKey("money") || payload.get("money") < 0){
			return "Invalid input.";
		}
		u.setMoney(u.getMoney()+payload.get("money"));
		userRepository.save(u);
		return "Success";
	}
}