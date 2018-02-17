package ru.yandex.startapp.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.yandex.startapp.domain.Master;
import ru.yandex.startapp.domain.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public User findByUserName(String username) {

		System.out.println("USERS findByUserName DAOIMPL!!!");

		Query queryLogin = sessionFactory.getCurrentSession().createQuery("from User where username = :paramLogin");
		queryLogin.setParameter("paramLogin", username);
		List loginList = queryLogin.list();

		if (!loginList.isEmpty()) {
			System.out.println(loginList + "users from DAOIMPL");
			return (User) loginList.get(0);
		} else {
			System.out.println("wrong PASSWORD");
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<User> listUser() {
		return sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public void addUser(User user) {
		Session tmpSession = sessionFactory.getCurrentSession();
		tmpSession.save(user);
	}

	public void addUser(String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole("MASTER");
		Session tmpSession = sessionFactory.getCurrentSession();
		tmpSession.save(user);
	}

}