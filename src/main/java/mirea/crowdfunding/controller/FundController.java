package mirea.crowdfunding.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mirea.crowdfunding.entity.Fundraising;
import mirea.crowdfunding.entity.User;
import mirea.crowdfunding.repository.FundraisingRepository;
import mirea.crowdfunding.repository.UserRepository;
import mirea.crowdfunding.service.FundraisingService;

@RestController
@RequestMapping(value = "/api")
public class FundController {
	@Autowired
	FundraisingService fundraisingService;
	@Autowired
	FundraisingRepository fundraisingRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private ObjectMapper objectMapper;

	@GetMapping("/fundraisings")
	public @ResponseBody Iterable<Fundraising> getAllFundraisings() {
		return fundraisingService.all();
	}

	@PostMapping("/fundraisings")
	public @ResponseBody String addFundraising(@RequestBody String payload) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!(principal instanceof User)) {
			return "Invalid user.";
		}
		User user = (User) principal;
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

	@Transactional
	@PostMapping("/fund/{id}")
	public @ResponseBody String fund(
			@PathVariable(value = "id") Integer fundraisingId,
			@RequestBody HashMap<String, Long> payload) {
		var frOption = fundraisingRepository.findById(fundraisingId);
		if (frOption.isEmpty()){
			return "Such fundraising doesn't exist.";
		}
		Fundraising fundraising = frOption.get();
		long frMoney = fundraising.getCollectedMoney();
		
		if (!payload.containsKey("money"))
			return "Must specify money amount.";
		long donation = payload.get("money");
		if (donation < 0)
			return "Nice try.";

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long balance = user.getMoney();
		if (donation > balance) {
			return "Not enough money.";
		}
		user.setMoney(balance - donation);
		fundraising.setCollectedMoney(frMoney+donation);
		userRepository.save(user);
		fundraisingRepository.save(fundraising);
		return "Success.";
	}
}
