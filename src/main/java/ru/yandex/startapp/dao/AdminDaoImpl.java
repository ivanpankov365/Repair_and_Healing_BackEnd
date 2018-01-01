package ru.yandex.startapp.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.yandex.startapp.domain.Admin;
import ru.yandex.startapp.domain.Master;

@Repository
public class AdminDaoImpl implements AdminDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public boolean verifyAdmin(Admin admin) {
		
		Query queryLogin = sessionFactory.getCurrentSession().createQuery("from Admin where login = :paramLogin");
		queryLogin.setParameter("paramLogin", admin.getLogin());
		List loginList = queryLogin.list();
		
	 if(!loginList.isEmpty()) {
		 Query queryPassword = sessionFactory.getCurrentSession().createQuery("from Admin where password = :paramPassword");
		 queryPassword.setParameter("paramPassword", admin.getPassword());
		 List passwordList = queryPassword.list();
		
		 if(!passwordList.isEmpty()) {
			 return true;
		 }
		 else {
			 System.out.println("wrong PASSWORD");
			 return false; 
		 }
	 }
	 else {
		 System.out.println("wrong LOGIN");
		 return false;
	 }
		
	}

}
