package mirea.crowdfunding.controller;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import mirea.crowdfunding.entity.User;

@JsonComponent
public class UserJSON {
	public static class Deserializer extends JsonDeserializer<User>{

		@Override
		public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
			ObjectCodec codec = p.getCodec();
			JsonNode tree = codec.readTree(p);
			String name = tree.get("name").textValue();
			String email = tree.get("email").textValue();
			String password = tree.get("password").textValue();
			User u = new User();
			u.setName(name);
			u.setEmail(email);
			u.setPassword(password);
			return u;
		}
		
	}
}
