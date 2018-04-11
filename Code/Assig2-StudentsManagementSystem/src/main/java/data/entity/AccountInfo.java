package data.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

//@Entity
//@Table(name = "account_info")
@Embeddable
public class AccountInfo {

	@Column(name = "username")
	protected String username;
	
	@Column(name = "password")
	protected String password;
	
	@Column(name = "status")
	protected String status;
	
	@Column(name = "registration_date")
	protected Date registrationDate;

	public AccountInfo() {
		super();
	}

	public AccountInfo(String username, String password, String status, Date registrationDate) {
		super();
		this.username = username;
		this.password = password;
		this.status = status;
		this.registrationDate = registrationDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
}
