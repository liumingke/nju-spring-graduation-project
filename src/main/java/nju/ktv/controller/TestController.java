package nju.ktv.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

	public TestController() {
		System.out.println(">>>>>>>>>>");
	}
	
	@RequestMapping("/")
	public String start() {
		return "staff/add";
	}
}
