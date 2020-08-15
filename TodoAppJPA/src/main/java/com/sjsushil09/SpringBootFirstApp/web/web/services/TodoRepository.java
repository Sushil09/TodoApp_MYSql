package com.sjsushil09.SpringBootFirstApp.web.web.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sjsushil09.SpringBootFirstApp.web.web.model.Todo;

public interface TodoRepository extends JpaRepository<Todo,Integer>{
//Retrieving list via names, not present by default
List<Todo> findByUser(String user);	
}
