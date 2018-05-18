package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import models.UserModel;
import play.data.*;
import play.mvc.*;

@Singleton
public class UserController extends Controller{
	@Inject FormFactory formFactory;
	public Result login() {
		return ok(views.html.login.render());
	}
	
	public Result sendLogin() {
		  DynamicForm in = formFactory.form().bindFromRequest();
		  String matricNo=in.get("matricNo");
		  String password=in.get("password");
		  UserModel userModel=UserModel.find.query().where().eq("matricNo", matricNo).and().eq("password", password).findOne();
		  if(userModel==null)
			  return ok("na"+matricNo+password);
		  else
			  return ok("ok");
	 }
}
