package ru.yandex.startapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.startapp.domain.Admin;
import ru.yandex.startapp.dao.*;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
    private AdminDao adminDao;

	@Transactional
	public boolean verifyAdmin(Admin admin) {
		return adminDao.verifyAdmin(admin);
	}

	
}
