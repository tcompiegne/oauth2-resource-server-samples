package controllers;

import java.util.ArrayList;
import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import security.CheckOAuthToken;

@CheckOAuthToken
public class RestTodo extends Controller {

	public static Result findAll() {
		List<models.Todo> todos = new ArrayList<models.Todo>();
		models.Todo todo1 = new models.Todo("todo1");
		models.Todo todo2 = new models.Todo("todo2");
		todos.add(todo1);
		todos.add(todo2);
		return ok(Json.toJson(todos)).as("application/json");
	}
}
