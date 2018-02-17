package ru.yandex.startapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import ru.yandex.startapp.domain.Master;
import ru.yandex.startapp.dao.*;

@Service
public class MasterServiceImpl implements MasterService {

	@Autowired
	private MasterDao masterDao;

	@Autowired
	private UserDao userDao;

	@Transactional
	public void addMaster(Master master) {
		masterDao.addMaster(master);
		userDao.addUser(master.getLogin(), master.getPassword());
	}

	@Transactional
	public List<Master> listMaster() {
		return masterDao.listMaster();
	}

	@Transactional
	public void removeMaster(Integer id) {
		masterDao.removeMaster(id);

	}

	@Transactional
	public Master verifyMaster(Master master) {
		return masterDao.verifyMaster(master);

	}

}
