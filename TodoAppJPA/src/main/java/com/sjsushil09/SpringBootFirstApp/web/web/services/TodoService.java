package com.sjsushil09.SpringBootFirstApp.web.web.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.sjsushil09.SpringBootFirstApp.web.web.model.Todo;
@Component
public class TodoService {
	
	//To get static data
	static List<Todo> todos=new ArrayList<>();
	static int totalCount=3;
//	static {
//		todos.add(new Todo(1,"Sushil","Spring Boot",new Date(),false));
//		todos.add(new Todo(2,"Sushil","LLD",new Date(),false));
//		todos.add(new Todo(3,"Varun","HLD",new Date(),false));
//	}
	public List<Todo> getTodoUser(String user){
		List<Todo> content=new ArrayList<>();
		for(Todo x:todos)
			if(x.getUser().equalsIgnoreCase(user))
				content.add(x);
		return content;
	}
	public void addTodo(String user,String description,Date date,boolean isPending) {
		todos.add(new Todo(++totalCount,user,description,date,isPending));
	}
	public void deleteTodo(int id){
		for(int i=0;i<todos.size();i++) {
			if(todos.get(i).getId()==id) {
				todos.remove(i);
				break;
			}
		}
	}
	public Todo retrieveTodo(int id) {
		for(Todo todo:todos) {
			if(todo.getId()==id)
				return todo;
		}
		return null;
	}
	public void updateTodo(Todo todo) {
		todos.remove(todo);
		todos.add(todo);
	}
}
