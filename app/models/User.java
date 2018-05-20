package models;
import io.ebean.*;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.*;
import javax.persistence.Version;


@Entity
@Constraints.Validate
public class User extends Model implements Constraints.Validatable<List<ValidationError>>{
	 @Id
	 private long id;
	 public static Finder<Long, User> find = new Finder<>(User.class);
	 
	 @Column(unique = true)
     @Constraints.Required
	 private String matricNo;
	 
	 @Version
	 private Date timeStamp = new Date();

	 private String school;

	 private String name;

	 private String phone;

	 private long qqNo;

	 private String password;

	 private String role = MEMBER;

	public final static String SUPERADMIN = "SUPERADMIN";
	public final static String ADMIN = "ADMIN";
	public final static String MEMBER = "MEMBER";

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMatricNo() {
		return matricNo;
	}

	public void setMatricNo(String matricNo) {
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

    public static Optional<User> authenticate(String matricNo, String password) {

        User partner = User.find.query().where().eq("matricNo", matricNo).findOne();
        if (partner != null && password.equals(partner.getPassword())) {
            return Optional.of(partner);
        } else {
            return Optional.empty();
        }
    }
	public static User getUserFromSession(){
	    String idS = Controller.session().get("id");
                if(idS == null){
                   return null;
                }else{
		long id = Long.parseLong(idS);
		return find.byId(id);}
	}

	public static User getUser(String matricNo, String password) {
		if(matricNo == null || matricNo.equals(""))
			return null;
		User user = User.find.query().where().eq("matricNo", matricNo).findOne();
		if(user == null || password.equals(user.getPassword()))
			return null;
		return user;
	}

    public Boolean checkNewUser() {
	   Optional<User> maticNoOpt = User.find.query().where().eq("matricNo",matricNo).findOneOrEmpty();
        return !maticNoOpt.isPresent()    ;
	}

    @Override
    public List<ValidationError> validate() {
        final List<ValidationError> errors = new ArrayList<>();

        if ( !checkNewUser()) {
            errors.add(new ValidationError("matricNo", "学号重复了"));
        }
        return errors;
    }
}
