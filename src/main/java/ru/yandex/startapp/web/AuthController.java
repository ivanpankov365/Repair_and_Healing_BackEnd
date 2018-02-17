package ru.yandex.startapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ru.yandex.startapp.auth.TokenHandler;
import ru.yandex.startapp.config.SecurityConfig;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final TokenHandler tokenHandler;
	private final UserDetailsService userDetailsService;
	

	@Autowired
	AuthController(AuthenticationManager authenticationManager, TokenHandler tokenHandler,
			UserDetailsService userDetailsService) {
		this.authenticationManager = authenticationManager;
		this.tokenHandler = tokenHandler;
		this.userDetailsService = userDetailsService;
	}

	@RequestMapping(method = RequestMethod.POST) // , produces = "application/json")
	@ResponseBody
	public AuthResponse auth(@RequestBody AuthParams params) throws AuthenticationException {

	
		final UsernamePasswordAuthenticationToken loginToken = params.toAuthenticationToken();
		final Authentication authentication = authenticationManager.authenticate(loginToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails user = userDetailsService
				.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

		System.out.println("User " + user.getUsername());
		final String token = tokenHandler.createTokenForUser(user);
		System.out.println("OK");

		String authRole = user.getAuthorities().toString();
		return new AuthResponse(token, authRole);
	}

	private static final class AuthParams {
		private String username;
		private String password;

		public AuthParams() {
		}

		public AuthParams(String username, String password) {
			this.username = username;
			this.password = password;
		}

		public String getEmail() {
			return username;
		}

		public void setEmail(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		UsernamePasswordAuthenticationToken toAuthenticationToken() {
			return new UsernamePasswordAuthenticationToken(username, password);
		}
	}

	private static final class AuthResponse {
		private String token;
		private String role;

		public AuthResponse() {
		}

		public AuthResponse(String token) {
			this.token = token;
		}

		public AuthResponse(String token, String role) {
			this.token = token;
			this.role = role;
		}

		public String getToken() {
			return token;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}
	}

}
