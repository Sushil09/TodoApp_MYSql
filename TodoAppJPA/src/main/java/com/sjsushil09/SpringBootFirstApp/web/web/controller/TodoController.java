package com.sjsushil09.SpringBootFirstApp.web.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.sjsushil09.SpringBootFirstApp.web.web.model.Todo;
import com.sjsushil09.SpringBootFirstApp.web.web.services.TodoRepository;
import com.sjsushil09.SpringBootFirstApp.web.web.services.TodoService;

@Controller
//@SessionAttributes("name")
public class TodoController {
	//To provide SpringBean(Dependancy Injection)
	@Autowired
	TodoService todoService;
	
	@Autowired
	TodoRepository repository;
	
	private String getLoggedInUser(ModelMap model) {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		
		return principal.toString();
	}
	//To provide the consistent date across the application
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	@RequestMapping(value="/list-todos",method=RequestMethod.GET)//to map to particular url routing & only applicable for get method
	//@ResponseBody//When we don't have view to render
	public String showTodos(ModelMap map) {
		String name=getLoggedInUser(map);
		map.put("todos", repository.findByUser(name));
//		map.put("todos", todoService.getTodoUser(name));
		return "list-todos";
	}
	
	@RequestMapping(value="/getTodo",method=RequestMethod.GET)
	public String GetTodo(ModelMap map) {
		map.addAttribute("to-do", new Todo(0,getLoggedInUser(map),"",new Date(),false));
		return "add-Todo"; 
	}
	
	
	@RequestMapping(value="/getTodo",method=RequestMethod.POST)
	public String addTodo(ModelMap map, @Valid Todo todo,BindingResult results) {
		if(results.hasErrors())
			return "redirect:/getTodo";
		
		todo.setUser(getLoggedInUser(map));
		repository.save(todo);
		//todoService.addTodo(getLoggedInUser(map),todo.getDescription(), new Date(), false);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/updateTodo",method=RequestMethod.POST)
	public String updateTodo(ModelMap map, @Valid Todo todo,BindingResult results) {
		if(results.hasErrors())
			return "add-Todo";
		todo.setUser(getLoggedInUser(map));
		repository.save(todo);
//		todoService.updateTodo(todo);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/delete-todos",method=RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		repository.deleteById(id);
//		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}
	
	@RequestMapping(value="/updateTodo",method=RequestMethod.GET)
	public String updateTodo(@RequestParam int id, ModelMap model) {
		Optional<Todo> todo=repository.findById(id);
//		Todo todo=todoService.retrieveTodo(id);
		model.put("to-do",todo);	
		return "add-Todo";
	}
	
}
