package ru.yandex.startapp.service;

import java.util.List;

import ru.yandex.startapp.domain.User;

public interface UserService {

	public User findByUserName(String username);

	public List<User> listUser();

	public void addUser(User user);

	public void addUser(String username, String password);
}
