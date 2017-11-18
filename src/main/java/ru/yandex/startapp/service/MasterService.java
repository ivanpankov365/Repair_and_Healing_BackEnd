package ru.yandex.startapp.service;

import java.sql.SQLException;
import java.util.List;

import ru.yandex.startapp.domain.Master;

public interface MasterService {
	
	
	public void addMaster(Master master) throws SQLException;

	public List<Master> listMaster();

	public void removeMaster(Integer id);
	

}
