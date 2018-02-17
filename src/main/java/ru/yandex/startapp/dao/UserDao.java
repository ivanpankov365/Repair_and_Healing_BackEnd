package ru.yandex.startapp.dao;

import java.util.List;

import org.hibernate.Session;

import ru.yandex.startapp.domain.Master;
import ru.yandex.startapp.domain.User;

public interface UserDao {

	public User findByUserName(String username);

	public List<User> listUser();

	public void addUser(User user);

	public void addUser(String username, String password);
}