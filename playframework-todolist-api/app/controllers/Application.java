package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result preflight(String all) {
		response().setHeader("Access-Control-Allow-Origin", "*");
		response().setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
		response().setHeader("Access-Control-Allow-Headers", "x-requested-with, Authorization, Content-Type");
		response().setHeader("Access-Control-Max-Age", "3600");
		return ok();
	}
}
