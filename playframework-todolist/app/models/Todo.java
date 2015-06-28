package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Todo extends Model {

	private static final long serialVersionUID = 1285157975949950997L;

	public static Finder<Long, Todo> find = new Finder<Long, Todo>(Long.class, Todo.class);

	@Id
	@GeneratedValue
	private Long id;

	private String description;

	private String username;

	public Todo() {
	}

	public Todo(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static List<Todo> findByUsername(String username) {
		return find.where().eq("username", username).findList();
	}
}
