package controllers;

import play.mvc.*;

public class HomeController extends Controller{

	public Result showHomePage() {
		return ok(views.html.home.render());
	}
	
}
