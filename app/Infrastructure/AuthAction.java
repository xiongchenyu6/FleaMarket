package Infrastructure;

import models.User;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Controller.*;

public class AuthAction extends Action<Authentication> {

    public CompletionStage<Result> call(Http.Context ctx) {
        //Time out mechanism START******************************
        long currTime = new Date().getTime();
        if(session("previousTime") != null){ // Have a previous timestamp
            long SESSION_TIMEOUT_PERIOD = 1800000; //30 minutes
            //long SESSION_TIMEOUT_PERIOD = 120000; //2 min
            System.out.println("Previous: " + session("previousTime"));
            System.out.println("Current Time: " + currTime);
            if(currTime - Long.parseLong(session("previousTime")) > SESSION_TIMEOUT_PERIOD) {
                System.out.println("Haha , Too Slow, Timeout!");
                session().clear(); //clear the session
                return failAuth(ctx); //log out and redirect to login page
            }
        }
        session().put("previousTime", String.valueOf(currTime));

        Map<String, Integer> roles = new HashMap<String, Integer> ();
        roles.put(User.MEMBER, 1);
        roles.put(User.ADMIN, 2);
        roles.put(User.SUPERADMIN, 3);

        String requiredRole = configuration.role();
        String currRole = session().get("role").toUpperCase();

        if(currRole==null)
            return failAuth(ctx);

        if(roles.get(requiredRole)>roles.get(currRole))
            return failAuth(ctx);
        else
            return delegate.call(ctx);


    }

    private CompletionStage<Result> failAuth(Http.Context ctx) {

        flash("error", "没有足够的权限使用这项服务，请登录");
        return CompletableFuture.supplyAsync(() -> redirect("/login"));

    }
}
