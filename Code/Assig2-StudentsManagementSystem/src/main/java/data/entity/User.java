package data.entity;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class User extends DBRecord {

	@Embedded
	protected UserInfo userInfo;
	
	
	@Embedded
	protected AccountInfo accountInfo;
}
