/*******************************************************************************
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Titouan COMPIEGNE
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *******************************************************************************/
package controllers;

import java.util.List;

import models.TodoResponse;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import security.CheckOAuthToken;
import actions.CORSAction;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
@CheckOAuthToken
public class RestTodo extends Controller {

	@With(CORSAction.class)
	public static Result findAll() {
		List<models.Todo> todos = models.Todo.find.all();
		return ok(Json.toJson(todos)).as("application/json");
	}

	@With(CORSAction.class)
	public static Result findByUsername(String username) {
		List<models.Todo> todos = models.Todo.findByUsername(username);
		return ok(Json.toJson(todos)).as("application/json");
	}

	@With(CORSAction.class)
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

	@With(CORSAction.class)
	public static Result edit() {
		JsonNode json = request().body().asJson();
		Long id = json.findPath("id").longValue();
		String description = json.findPath("description").textValue();

		models.Todo todo = models.Todo.find.byId(id);
		todo.setDescription(description);
		todo.update();

		return ok(Json.toJson(todo)).as("application/json");
	}

	@With(CORSAction.class)
	public static Result delete(Long id) {
		models.Todo todo = models.Todo.find.byId(id);
		todo.delete();
		TodoResponse todoResponse = new TodoResponse();
		todoResponse.setStatus("OK");
		todoResponse.setMessage("Todo with id " + id + " successfully deleted");
		return ok(Json.toJson(todoResponse)).as("application/json");
	}

}
