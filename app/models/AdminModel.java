package models;

import io.ebean.*;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Version;

@Entity
public class AdminModel extends Model {
	@Id
	 private long id;
	 public static Finder<Long, AdminModel> find = new Finder<>(AdminModel.class);
	 
	 @Column(unique = true)
	 private long adminName;
	 
	 @Version
	 private Date timeStamp;
	 
	 private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAdminName() {
		return adminName;
	}

	public void setAdminName(long adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	 
}
