package models;
import io.ebean.*;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Version;

@Entity
public class UserModel extends Model {
	 @Id
	 private long id;
	 public static Finder<Long, UserModel> find = new Finder<>(UserModel.class);
	 
	 @Column(unique = true)
	 private long matricNo;
	 
	 @Version
	 private Date timeStamp;
	 
	 private String school;
	 
	 private String name;
	 
	 private String phone;
	 
	 private long qqNo;
	 
	 private String password;

	 private String role = "member";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMatricNo() {
		return matricNo;
	}

	public void setMatricNo(long matricNo) {
		this.matricNo = matricNo;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getQqNo() {
		return qqNo;
	}

	public void setQqNo(long qqNo) {
		this.qqNo = qqNo;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
