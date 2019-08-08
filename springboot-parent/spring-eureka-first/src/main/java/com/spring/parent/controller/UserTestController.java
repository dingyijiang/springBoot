package com.spring.parent.controller;

import org.spring.entity.model.User;
import org.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
