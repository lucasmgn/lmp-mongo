package com.escola.alunos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdiminController {
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
}
