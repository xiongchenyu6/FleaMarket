package controllers;

import com.google.inject.Inject;
import play.data.FormFactory;
import play.mvc.*;
import org.webjars.play.WebJarsUtil;

public class HomeController extends Controller{

	@Inject
	private WebJarsUtil webJarsUtil;

	@Inject
	private AssetsFinder assetsFinder;

	public Result showHomePage() {
		return ok(views.html.home.render(assetsFinder, webJarsUtil));
	}

}
