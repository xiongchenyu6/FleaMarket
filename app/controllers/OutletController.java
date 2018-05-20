package controllers;

import Infrastructure.Authentication;
import Infrastructure.ImgTools;
import com.google.inject.Inject;
import models.Booking;
import models.Outlet;
import models.User;
import play.Logger;
import play.data.FormFactory;
import play.mvc.*;
import org.webjars.play.WebJarsUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OutletController extends Controller{

	@Inject
	private WebJarsUtil webJarsUtil;

	@Inject
	private AssetsFinder assetsFinder;

	@Inject
	private FormFactory formFactory;

	@Authentication(role = User.MEMBER)
	public Result outletBookingPage() {
		return ok(views.html.bookOutlet.render(assetsFinder, webJarsUtil));
	}

	@Authentication(role = User.MEMBER)
	public Result book() {
		Http.MultipartFormData<File> body = request().body().asMultipartFormData();
		Http.MultipartFormData.FilePart<File> picture = body.getFile("photo");
		String outletName = body.asFormUrlEncoded().get("outletName")[0];
		String outletIntro = body.asFormUrlEncoded().get("outletIntro")[0];
		String qqNo = body.asFormUrlEncoded().get("qqNo")[0];
		String phone = body.asFormUrlEncoded().get("phone")[0];
		long outletId = Long.parseLong(body.asFormUrlEncoded().get("outletId")[0]);
		Booking booking =new Booking();
		booking.setOutletName(outletName);
		booking.setOutletIntro(outletIntro);
		booking.setQqNo(qqNo);
		booking.setUserModel(User.getUserFromSession());
		booking.setPhone(phone);
		booking.setOutletModel(Outlet.find.byId(outletId));
		booking.save();
		if (picture != null) {
			File file = picture.getFile();

			BufferedImage img = null;
			try {
				img = ImageIO.read(file);
			} catch (IOException e) {
			}

			String filePath = "public/upload/" + booking.getId() + ".jpg";
			File f = new File(filePath);
			f.getParentFile().mkdirs();
			try {
				f.createNewFile();
				FileOutputStream fos = new FileOutputStream(filePath);
				if (fos == null)
					Logger.info("fos is null");
				if (img == null)
					Logger.info("img is null");
				ImgTools.thumbnail_w_h(img, 512, 512, fos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return redirect(routes.HomeController.showHomePage());
		} else {
			flash("error", "没有图片");
			return redirect(routes.HomeController.showHomePage());
		}
	}

	@Authentication(role = User.MEMBER)
	public Result myBookingPage() {
		return ok(views.html.myBookings.render(assetsFinder, webJarsUtil));
	}

	@Authentication(role = User.MEMBER)
	public Result cancel(Long id) {
	    Booking booking = Booking.find.byId(id);
	    booking.delete();
		return redirect(routes.OutletController.myBookingPage());
	}

	@Authentication(role = User.ADMIN)
	public Result create(String outletName) {
		Outlet outlet = new Outlet();
		outlet.setOutletName(outletName);
		outlet.save();
		return redirect(routes.OutletController.outletManagementPage());
	}
	@Authentication(role = User.ADMIN)
	public Result accept(Long id) {
		Booking booking = Booking.find.byId(id);
		booking.setStatus(Booking.APPROVED);
		booking.save();
		return redirect(routes.OutletController.bookingsPage());
	}
	@Authentication(role = User.ADMIN)
	public Result reject(Long id) {
		Booking booking = Booking.find.byId(id);
		booking.setStatus(Booking.REJECTED);
		booking.save();
		return redirect(routes.OutletController.bookingsPage());
	}

	@Authentication(role = User.ADMIN)
	public Result bookingsPage() {
		return ok(views.html.bookings.render(assetsFinder, webJarsUtil));
	}

	@Authentication(role = User.ADMIN)
	public Result forbid(long id){
		Outlet outlet = Outlet.find.byId(id);
		outlet.setAvailable(false);
		outlet.save();
		return redirect(routes.OutletController.outletManagementPage());
	}
	@Authentication(role = User.ADMIN)
	public Result active(long id){
		Outlet outlet = Outlet.find.byId(id);
		outlet.setAvailable(true);
		outlet.save();
		return redirect(routes.OutletController.outletManagementPage());
	}

	@Authentication(role = User.ADMIN)
	public Result outletManagementPage(){
		return ok(views.html.outletManagement.render(assetsFinder, webJarsUtil));
	}
}
