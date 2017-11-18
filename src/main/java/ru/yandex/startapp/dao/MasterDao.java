package ru.yandex.startapp.dao;

import ru.yandex.startapp.domain.Master;
import ru.yandex.startapp.domain.Task;

import java.sql.SQLException;
import java.util.List;


public interface MasterDao {

	public void addMaster(Master master) throws SQLException;

	public List<Master> listMaster();

	public void removeMaster(Integer id);
	
}
