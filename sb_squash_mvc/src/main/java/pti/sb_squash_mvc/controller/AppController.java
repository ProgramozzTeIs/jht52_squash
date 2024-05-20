package pti.sb_squash_mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pti.sb_squash_mvc.dto.AdminDto;
import pti.sb_squash_mvc.dto.ErrorDto;
import pti.sb_squash_mvc.dto.GameDtoList;
import pti.sb_squash_mvc.dto.UserDto;
import pti.sb_squash_mvc.model.User;
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
		ErrorDto errorDto = new ErrorDto("Sorry You need to log in!!");
		
		
		if(gameDtoList != null) {
			
			model.addAttribute("gamedtolist", gameDtoList);
			targetPage = "game.html";
			
		}else {
			 
			model.addAttribute("error", errorDto);
			
			targetPage = "login.html";  
		}
		
		
		
		return targetPage;
	}
	

	
	@GetMapping("/logout") 
	public String logout(
			Model model,
			@RequestParam("userid") int userId
			) {
		
		service.logoutUser(userId);
		ErrorDto errorDto = new ErrorDto("Succsessfull logout!");
		model.addAttribute("error", errorDto);
		
		return "login.html";
	}
	
	@PostMapping("/login")
	public String login(
			Model model,
			@RequestParam("name") String name,
			@RequestParam("pwd") String password
			) {
		
		String targetPage = "";
		ErrorDto errorDto = new ErrorDto("Not valid name or password, try again!");
		
		User user = service.getUserByNameAndPassword(name,password);
		
		/**Ha megvan a User akkor folytatjuk hogy melyik oldalon kössünk ki*/
		if(user != null) {
			
			/**Elsőnek megnézem adminnal van-e dolgunk és ha igen akkor admin.html a cél*/
			if(user.getRole().equals("admin")) {
				
				AdminDto adminDto = service.getAdminDto(user);
				model.addAttribute("admindto", adminDto);
				targetPage = "admin.html";
			}
			else {
				
				/**Ha nem változtatta meg a jelszavát akkor a megváltoztató oldal a cél*/
				if(user.isChangedPwd() == false) {
					
					UserDto userDto = new UserDto(user.getId(), user.getName());
					model.addAttribute("userDto", userDto);
					targetPage = "changepwd.html";
				}
				/**Ha megváltoztatta már akkor pedig game.html*/
				else if(user.isChangedPwd() == true){
					
					UserDto userDto = new UserDto(user.getId(), user.getName());
					GameDtoList gameDtoList = service.getGameDtoList(userDto);
					gameDtoList.sortGameDates();
					service.loginUser(user.getId());
					
					model.addAttribute("gamedtolist", gameDtoList);
					targetPage = "game.html";
				}
				
			}
			
		}
		/**Ha nincs meg a User akkor Hibás Név vagy Pwd*/
		else {
			
			model.addAttribute("error", errorDto);
			targetPage = "login.html";
		}
		
		
		return targetPage;
	}
	
	@PostMapping("/admin/reg/location")
	public String registerNewLocaton(
			Model model,
			@RequestParam("adminid") int adminId,
			@RequestParam("locname") String locName,
			@RequestParam("adress") String locAddress,
			@RequestParam("fee") int fee) {
		
		String targetPage = "";
		
		AdminDto adminDto = service.registerNewLocation( adminId,locName,locAddress,fee);
		ErrorDto errorDto = new ErrorDto("Sorry You need to log in!!");
		
		if(adminDto != null) {
			model.addAttribute("admindto", adminDto);
			targetPage = "admin.html";
			
		}else {
			 
			model.addAttribute("error", errorDto);
			
			targetPage = "login.html";  
		}
		
		
		
		return targetPage;
	}
	
	
}









