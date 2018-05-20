package models;

import controllers.routes;
import io.ebean.*;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Version;

@Entity
public class Outlet extends Model {
	@Id
	private long id;
	public static Finder<Long, Outlet> find = new Finder<>(Outlet.class);
	
	@Column(unique = true)
	private String outletName;
	
	@Version
	private Date timeStamp = new Date();
	
	private boolean available=true;

	public static final boolean AVAILABLE=true;
	
	public static final boolean UNAVAILABLE=false;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOutletName() {
		return outletName;
	}

	public void setOutletName(String outletName) {
		this.outletName = outletName;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatus(){
		if(available == false){
			return "不可用";
		}else{
			return "可用";
		}
	}
	public String getDeleteUrl(){
	 return controllers.routes.OutletController.forbid(id ).url();
	}
	public String getActiveUrl(){
		return controllers.routes.OutletController.active(id ).url();
	}

}
