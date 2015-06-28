package controllers;

import java.util.ArrayList;
import java.util.List;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import security.CheckOAuthToken;

import com.fasterxml.jackson.databind.JsonNode;

@CheckOAuthToken
public class RestTodo extends Controller {

	public static Result findAll() {
		List<models.Todo> todos = models.Todo.find.all();
		return ok(Json.toJson(todos)).as("application/json");
	}

	public static Result findByUsername(String username) {
		List<models.Todo> todos = models.Todo.findByUsername(username);
		return ok(Json.toJson(todos)).as("application/json");
	}
	
	public static Result add() {
		JsonNode json = request().body().asJson();
	  String description = json.findPath("description").textValue();
    String username = json.findPath("username").textValue();
		
    models.Todo todo = new models.Todo();
		todo.setDescription(description);
		todo.setUsername(username);
		todo.save();
		
		return ok(Json.toJson(todo)).as("application/json");
	}
	
	public static Result edit() {
		JsonNode json = request().body().asJson();
	  Long id = json.findPath("id").longValue();
    String description = json.findPath("description").textValue();
		
    models.Todo todo = models.Todo.find.byId(id);
		todo.setDescription(description);
		todo.update();
		
		return ok(Json.toJson(todo)).as("application/json");
	}
	
	public static Result delete(Long id) {
		models.Todo todo = models.Todo.find.byId(id);
		todo.delete();
		return ok(Json.toJson("Todo with id " + id + " successfully deleted")).as("application/json");
	}
	
	
}
