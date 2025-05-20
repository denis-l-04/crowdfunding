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

import mirea.crowdfunding.entity.Fundraising;
import mirea.crowdfunding.entity.User;
import mirea.crowdfunding.service.FundraisingService;

@RestController
@RequestMapping(value = "/api")
public class FundController {
	@Autowired
	FundraisingService fundraisingService;
    @Autowired
    private ObjectMapper objectMapper;

	@GetMapping("/fundraisings")
	public @ResponseBody Iterable<Fundraising> getAllFundraisings() {
		return fundraisingService.all();
	}

	@PostMapping("/fundraisings")
	public @ResponseBody String addFundraising(@RequestBody String payload){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof User)) {
			return "Invalid user.";
		}
		User user = (User)principal;
		Fundraising fr;
		try {
			fr = objectMapper.readValue(payload, Fundraising.class);
			fr.setOwner(user);
			if (!fundraisingService.save(fr))
				return "Incorrect values.";
			return "Saved.";
		} catch (Exception e) {
			return "Incorrect formatting.";
		}
	}
}
