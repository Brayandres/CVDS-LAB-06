package edu.eci.cvds.servlet.model;

public class Todo {
	int userId, id;
	String title;
	boolean completed;

	public Todo() {

	}

	public void setAll(int userId, int id, String title, boolean completed) {
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.completed = completed;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getUserId() {
		return this.userId;
	}

	public int getId() {
		return this.id;
	}

	public String getTitle() {
		return this.title;
	}

	public boolean isCompleted() {
		return this.completed;
	}
}