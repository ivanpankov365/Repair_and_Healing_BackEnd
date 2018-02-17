package ru.yandex.startapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "masters")
public class Master {

	@Id
	@Column(name = "master_id")
	@GeneratedValue
	private Integer masterId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "telephon_number")
	private String telephonNumber;

	@Column(name = "login")
	private String login;

	@Column(name = "password")
	private String password;

	@Column(name = "department_id")
	private String departmentId;

	// Getters

	public Integer getMasterId() {
		return masterId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getTelephonNumber() {
		return telephonNumber;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	// Setters

	public void setMasterId(int masterId) {
		this.masterId = masterId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setTelephonNumber(String telephonNumber) {
		this.telephonNumber = telephonNumber;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	@Override
	public String toString() {
		return "Master{" + "id=" + masterId + ", Name=" + firstName + ", Soname=" + lastName + '}';
	}

}
