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
package com.tce.oauth2.spring.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tce.oauth2.spring.api.model.Todo;
import com.tce.oauth2.spring.api.model.TodoResponse;
import com.tce.oauth2.spring.api.repository.TodoRepository;

/**
 * 
 * @author Titouan COMPIEGNE
 *
 */
@Controller
@RequestMapping("/rest")
public class TodoController {

	@Autowired
	private TodoRepository todoRepository;

	@RequestMapping("/todos")
	public @ResponseBody Iterable<Todo> findAll() {
		return todoRepository.findAll();
	}

	@RequestMapping("/todos/{username}")
	public @ResponseBody List<Todo> findByUsername(@PathVariable("username") String username) {
		return todoRepository.findByUsername(username);
	}

	@RequestMapping(value = "/todos/add", method = RequestMethod.POST)
	public @ResponseBody Todo add(@RequestBody Todo todo) {
		return todoRepository.save(todo);
	}

	@RequestMapping(value = "/todos/edit", method = RequestMethod.POST)
	public @ResponseBody Todo edit(@RequestBody Todo todo) {
		Todo todoToUpdate = todoRepository.findOne(todo.getId());
		todoToUpdate.setDescription(todo.getDescription());
		return todoRepository.save(todoToUpdate);
	}

	@RequestMapping(value = "/todos/{todoId}/delete", method = RequestMethod.POST)
	public @ResponseBody TodoResponse delete(@PathVariable("todoId") Long id) {
		todoRepository.delete(id);
		TodoResponse todoResponse = new TodoResponse();
		todoResponse.setStatus("OK");
		todoResponse.setMessage("Todo with id " + id + " successfully deleted");
		return todoResponse;
	}

}
