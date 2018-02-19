package ru.yandex.startapp.dao;

import java.util.List;

import ru.yandex.startapp.domain.Admin;
import ru.yandex.startapp.domain.User;
import ru.yandex.startapp.service.MasterService;
import ru.yandex.startapp.service.UserService;
import ru.yandex.startapp.domain.Master;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MasterDaoImpl implements MasterDao {
	@Autowired
	private SessionFactory sessionFactory;

	public void addMaster(Master master) {
		Session tmpSession = sessionFactory.getCurrentSession();
		tmpSession.save(master);
	}

	@SuppressWarnings("unchecked")
	public List<Master> listMaster() {
		return sessionFactory.getCurrentSession().createQuery("from Master").list();
	}

	@Override
	public void removeMaster(Integer id) {
		Master master = (Master) sessionFactory.getCurrentSession().load(Master.class, id);
		if (null != master) {
			sessionFactory.getCurrentSession().delete(master);
		}

	}

	@SuppressWarnings("unchecked")
	public Master verifyMaster(Master master) {

		Query queryLogin = sessionFactory.getCurrentSession().createQuery("from Master where login = :paramLogin");
		queryLogin.setParameter("paramLogin", master.getLogin());
		List loginList = queryLogin.list();

		if (!loginList.isEmpty()) {
			Query queryPassword = sessionFactory.getCurrentSession()
					.createQuery("from Master where password = :paramPassword");
			queryPassword.setParameter("paramPassword", master.getPassword());
			List passwordList = queryPassword.list();

			if (!passwordList.isEmpty()) {
				Master foundMaster = (Master) passwordList.get(0);
				System.out.println(foundMaster);
				return foundMaster;
			} else {
				System.out.println("wrong PASSWORD");
				return null;
			}
		} else {
			System.out.println("wrong LOGIN");
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	public Master verifyMaster(String login) {

		Query queryLogin = sessionFactory.getCurrentSession().createQuery("from Master where login = :paramLogin");
		queryLogin.setParameter("paramLogin", login);
		List loginList = queryLogin.list();

		if (!loginList.isEmpty()) {
			
				return (Master) loginList.get(0);
			
		} else {
			System.out.println("wrong LOGIN");
			return null;
		}

	}

}
