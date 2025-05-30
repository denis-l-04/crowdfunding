package mirea.crowdfunding.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers("/api/test").permitAll()
						.requestMatchers("/api/register").permitAll()
						.requestMatchers("/api/login").permitAll()
						.requestMatchers("/api/logout").authenticated()
						.requestMatchers("/api/me").authenticated()
						.requestMatchers("/api/fundraisings").permitAll()
						.requestMatchers("/api/fundraisings/*").permitAll()
						.requestMatchers("/api/fundraisings/*/comments").authenticated()
						.requestMatchers("/api/up-balance").authenticated()
						.requestMatchers("/api/fund/*").authenticated()
						.anyRequest().denyAll())
				.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.httpBasic(it -> {
				})
				.build();
	}
}