package models;
import io.ebean.*;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Version;

@Entity
public class BookingModel extends Model {

	@Id
	private long id;
	
	public static Finder<Long, BookingModel> find = new Finder<>(BookingModel.class);
	
	@Version
	private Date timeStamp;
	@ManyToOne(cascade = CascadeType.ALL)
	private UserModel userModel;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private OutletModel outletModel;
	
	private String outletName;
	private String outletIntro;
	private String qqNo;
	private String phone;
	private String status = PENDING;
	
	public static final String APPROVED="approved";
	public static final String REJECTED="rejected";
	public static final String PENDING="pending";
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public OutletModel getOutletModel() {
		return outletModel;
	}
	public void setOutletModel(OutletModel outletModel) {
		this.outletModel = outletModel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public UserModel getUserModel() {
		return userModel;
	}
	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	public String getOutletName() {
		return outletName;
	}
	public void setOutletName(String outletName) {
		this.outletName = outletName;
	}
	public String getOutletIntro() {
		return outletIntro;
	}
	public void setOutletIntro(String outletIntro) {
		this.outletIntro = outletIntro;
	}
	public String getQqNo() {
		return qqNo;
	}
	public void setQqNo(String qqNo) {
		this.qqNo = qqNo;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
