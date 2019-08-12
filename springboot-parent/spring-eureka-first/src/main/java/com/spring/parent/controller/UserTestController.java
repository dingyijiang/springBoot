package com.spring.parent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.parent.entity.model.User;
import com.spring.parent.service.UserService;

@RestController
@RequestMapping("/user/test")
public class UserTestController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/getById")
	public User getById(@RequestParam("id") int id) {
		return userService.getById(id);
	}
}
