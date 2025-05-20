package mirea.crowdfunding.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.annotation.PostConstruct;
import mirea.crowdfunding.repository.CategoryRepository;
import mirea.crowdfunding.entity.Fundraising;

@JsonComponent
@Component
public class FundraisingJSON {
	@Autowired
	CategoryRepository categoryRepository0;

	static CategoryRepository categoryRepository;

	@PostConstruct
	public void init(){
		categoryRepository = categoryRepository0;
	}

	public static class Deserializer extends JsonDeserializer<Fundraising> {

		@Override
		public Fundraising deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
			ObjectCodec codec = p.getCodec();
			JsonNode tree = codec.readTree(p);
			String name = tree.get("name").textValue();
			long targetMoney = tree.get("targetMoney").asLong();
			String categorySlug = tree.get("category").textValue();
			Fundraising fr = new Fundraising();
			fr.setCategory(categoryRepository.findBySlug(categorySlug).get());
			fr.setName(name);
			fr.setTargetMoney(targetMoney);
			return fr;
		}
	}
}
