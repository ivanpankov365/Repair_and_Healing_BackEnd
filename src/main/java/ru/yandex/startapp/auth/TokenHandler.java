package ru.yandex.startapp.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface TokenHandler {

	UserDetails parseUserFromToken(String token);

	String createTokenForUser(UserDetails user);

}
