package pti.sb_squash_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_mvc.dto.ErrorDto;
import pti.sb_squash_mvc.dto.GameDtoList;
import pti.sb_squash_mvc.dto.UserDto;
import pti.sb_squash_mvc.service.AppService;

@Controller
public class AppController {
	
	private AppService service;
	
	@Autowired
	public AppController(AppService service) {
		super();
		this.service = service;		
	}
	
	@GetMapping("/")
	public String loadLoginPage() {
		
		return "login.html";
	}
	
	@GetMapping("/login/change")
	public String changePassword(
			Model model,
			@RequestParam("userid") int userId,
			@RequestParam("newpsw") String newPsw) {
		
		UserDto userDto = service.changePassword(userId, newPsw);
		
		model.addAttribute("userdto", userDto);
		
		return "game.html";
	}
	
	@GetMapping("/game/search/location")
	public String getAllGameByLocataion(Model model,
			@RequestParam("userid") int userId,
			@RequestParam("location") String location) {
		String targetPage = "";
		
		GameDtoList gameDtoList = service.getAllGameByLocation(userId, location);
		
		if(gameDtoList != null) {
			
			model.addAttribute("gamedtolist", gameDtoList);
			targetPage = "game.html";
			
		}else {
			//ErrorDto errordto = new ErrorDto("Sorry you need to log in!!!);
			//model.addAttribute("error", errorDto);
			
			targetPage = "login.html";  
		}
		
		 
		
		return targetPage;
	}
	
	@GetMapping("/game/search/player")
	public String getAllGameByPlayer(
				Model model, 
				@RequestParam("userid") int userId,
				@RequestParam("searchedplayerid")
			
			)
	
	
	@GetMapping("/logout") 
	public String logout(
			Model model,
			@RequestParam("userid") int userId
			) {
		
		ErrorDto errorDto = new ErrorDto("Succsessfull logout!");
		model.addAttribute("errordto", errorDto);
		
		return "login.html";
	}
	
	
	
	
	
}
