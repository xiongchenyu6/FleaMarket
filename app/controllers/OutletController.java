package controllers;

import play.mvc.*;

public class OutletController extends Controller{

	public Result outletBooking() {
		return ok("OK");
	}
	public Result myBooking() {
		return ok("OK DOKY");
	}
}
