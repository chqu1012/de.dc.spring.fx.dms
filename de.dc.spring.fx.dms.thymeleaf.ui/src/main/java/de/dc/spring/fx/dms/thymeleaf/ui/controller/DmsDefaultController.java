package de.dc.spring.fx.dms.thymeleaf.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import de.dc.spring.fx.dms.thymeleaf.ui.service.TicketDtoService;

@Controller
public class DmsDefaultController {

	@Autowired TicketDtoService ticketDtoService;
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("tickets", ticketDtoService.getTickets());
		return "/index";
	}

    @GetMapping("/home")
    public String home() {
        return "/home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/user";
    }

    @GetMapping("/about")
    public String about() {
        return "/about";
    }
    
    @GetMapping("/login")
    public String login() {
    	return "/login";
    }

    @GetMapping("/view")
    public String view(Model model) {
    	model.addAttribute("tickets", ticketDtoService.getTickets());
        return "/view";
    }

    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }

}
