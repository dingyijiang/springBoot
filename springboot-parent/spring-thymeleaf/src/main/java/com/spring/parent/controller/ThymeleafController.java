package com.spring.parent.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.parent.entity.model.User;
//@Controller不能用@RestController 后者会直接输出json格式的/index,而不是跳转页面
@Controller
@RequestMapping("/thymeleaf")
public class ThymeleafController {

	/**
	     * 测试Thymeleaf
	     * @return
	     */

	@RequestMapping("/index")
	public String hello(Map<String,Object> model) {
		model.put("test","Dear");
		return "/thymeleaf/index";
	}
	
	@GetMapping("/test")
	public String test(@RequestBody User user) {
		System.out.println("user="+user.getName());
		return "success";
	}

}
