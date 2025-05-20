package mirea.crowdfunding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mirea.crowdfunding.entity.Fundraising;
import mirea.crowdfunding.repository.FundraisingRepository;

@Service
public class FundraisingService {
	@Autowired
	FundraisingRepository fundraisingRepository;
    public List<Fundraising> all() {
        return fundraisingRepository.findAll();
    }

	public boolean save(Fundraising fr){
		if (fr.getCategory() == null ||
			fr.getTargetMoney()<1 ||
			fr.getOwner() == null ||
			fr.getCollectedMoney() < 0)
			return false;
		fundraisingRepository.save(fr);
		return true;
	}
}
