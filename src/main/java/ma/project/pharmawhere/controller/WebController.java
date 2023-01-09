package ma.project.pharmawhere.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WebController {
	@GetMapping("pharmacien")
	RedirectView showMain(Model model) {
		return new RedirectView("../th-index.html");
	}
	@GetMapping("test")
	String showTest(Model model) {
		return "grid";
	}
	
}
