package ma.project.pharmawhere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {
	@GetMapping("pharmacien")
	String showMain(Model model) {
		return "main";
	}
	@GetMapping("test")
	String showTest(Model model) {
		return "grid";
	}
	
}
