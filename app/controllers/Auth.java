package controllers;

import models.User;
import org.webjars.play.WebJarsUtil;

import play.data.DynamicForm;
import play.data.Form;
import play.data.FormFactory;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Optional;

public class Auth extends Controller {

    @Inject
    private WebJarsUtil webJarsUtil;

    @Inject
    private AssetsFinder assetsFinder;

    @Inject
    private FormFactory formFactory;

    public Result loginPage() {

        Form<User> form = formFactory.form(User.class);
        return ok(views.html.login.render(form, assetsFinder, webJarsUtil));
    }

    public Result signUpPage() {

        Form<User> form = formFactory.form(User.class);
        return ok(views.html.signup.render(form, assetsFinder, webJarsUtil));
    }

    public Result signUp() {
        Form<User> userForm = formFactory.form(User.class).bindFromRequest();

        if (userForm.hasErrors()) {
            return ok(views.html.signup.render(userForm, assetsFinder, webJarsUtil));
        } else {
            User user = userForm.get();
            user.save();
            session().put("id", user.getId() + "");
            session().put("role", user.getRole());
            return redirect(routes.HomeController.showHomePage());
        }
    }

    public Result signIn() {

        DynamicForm form = formFactory.form().bindFromRequest();
        Optional<User> userOpt = User.authenticate(form.get("matricNo"),
                form.get("password"));
        return userOpt.map((user) -> {
            session().clear();
            session().put("id", user.getId() + "");
            session().put("role", user.getRole());
            return redirect(routes.HomeController.showHomePage());
        }).orElseGet(() ->{
            flash("error", "学号和密码不匹配，请重新输入");
            return redirect(routes.Auth.loginPage());
        });
    }
    public Result logout(){
        session().clear();
        return redirect(routes.Auth.loginPage());
    }
}
