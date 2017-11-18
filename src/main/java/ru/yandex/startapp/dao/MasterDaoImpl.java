package ru.yandex.startapp.dao;

import java.util.List;
import java.sql.SQLException;
import javax.imageio.spi.ServiceRegistry;
import javax.security.auth.login.Configuration;
import javax.swing.JOptionPane;
import ru.yandex.startapp.domain.Master;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;




@Repository
public class MasterDaoImpl implements MasterDao {	
	//@Autowired
	//private SessionFactory sessionFactory;
	
	private Session getHibernateSession() {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Ошибка при вставке", JOptionPane.OK_OPTION);
		}
		
		return session;
		
	}
		
	public void addMaster(Master master) throws SQLException {
		// TODO Auto-generated method stub
		Session session = getHibernateSession();
		Transaction tx = session.beginTransaction();
		session.save(master);
		tx.commit();
		
			if (session != null && session.isOpen()) {
				session.close();
			}
		

	}

	@SuppressWarnings("unchecked")
	public List<Master> listMaster() {
		Session sessionMaster = getHibernateSession();
		return sessionMaster.createQuery("from Master")
				.list();
	}

	@Override
	public void removeMaster(Integer id) {
		
		Session sessionMaster = getHibernateSession();
		Master master = (Master) sessionMaster.load(
				Master.class, id);
		if (null != master) {
			sessionMaster.delete(master);
		}
		
	}
	
	/*public void addMaster(Master master) {
		Session tmpSession = sessionFactory.getCurrentSession();
		tmpSession.save(master);
	}

	@SuppressWarnings("unchecked")
	public List<Master> listMaster() {
		return sessionFactory.getCurrentSession().createQuery("from Master")
				.list();
	}

	@Override
	public void removeMaster(Integer id) {
		Master master = (Master) sessionFactory.getCurrentSession().load(
				Master.class, id);
		if (null != master) {
			sessionFactory.getCurrentSession().delete(master);
		}
		
	}
	
	*/

}
