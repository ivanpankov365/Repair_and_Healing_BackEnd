package ru.yandex.startapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.yandex.startapp.dao.UserDao;
import ru.yandex.startapp.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Transactional
	public List<User> listUser() {
		return userDao.listUser();
	}

	@Transactional
	public void addUser(User user) {
		userDao.addUser(user);
	}

	public void addUser(String username, String password) {
		userDao.addUser(username, password);
	}
}
