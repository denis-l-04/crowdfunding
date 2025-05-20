package mirea.crowdfunding.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import mirea.crowdfunding.repository.CategoryRepository;
import mirea.crowdfunding.entity.Fundraising;

@JsonComponent
public class FundraisingJSON {
	public static class Deserializer extends JsonDeserializer<Fundraising>{
		@Autowired
		private CategoryRepository categoryRepository;

		@Override
		public Fundraising deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
			ObjectCodec codec = p.getCodec();
			JsonNode tree = codec.readTree(p);
			String name = tree.get("name").textValue();
			long targetMoney = tree.get("targetMoney").asLong();
			int categoryId = tree.get("category").asInt();
			Fundraising fr = new Fundraising();
			fr.setCategory(categoryRepository.findById(categoryId).get());
			fr.setName(name);
			fr.setTargetMoney(targetMoney);
			return fr;
		}
	}
}
