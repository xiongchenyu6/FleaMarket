package models;

import io.ebean.*;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Version;

@Entity
public class OutletModel extends Model {
	@Id
	private long id;
	public static Finder<Long, OutletModel> find = new Finder<>(OutletModel.class);
	
	@Column(unique = true)
	private String outletName;
	
	@Version
	private Date timeStamp;
	
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
}
