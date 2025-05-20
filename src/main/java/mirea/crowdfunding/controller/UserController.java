package mirea.crowdfunding.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mirea.crowdfunding.entity.User;
import mirea.crowdfunding.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserController {
	@Autowired
	private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

	@GetMapping("/test")
	public @ResponseBody Iterable<User> getAllUsers() {
		return userService.allUsers();
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
}