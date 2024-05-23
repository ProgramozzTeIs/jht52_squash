package pti.sb_squash_mvc.controller;

import java.time.LocalDateTime;

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
	
	@PostMapping("/login/change")
	public String changePassword(
			Model model,
			@RequestParam("userid") int userId,
			@RequestParam("newpsw") String newPsw) {
		
		service.changePassword(userId, newPsw);
		GameDtoList gameDtoList = service.getGameDtoList(userId, null, null);

		model.addAttribute("gamedtolist", gameDtoList);
		
		return "game.html";
	}
	
	@GetMapping("/game/search/location")
	public String getAllGameByLocataion(Model model,
			@RequestParam("userid") int userId,
			@RequestParam("locationname") String locationName) {
		String targetPage = "";
		
		GameDtoList gameDtoList = service.getGameDtoList(userId, locationName, null);
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
	

	
	@PostMapping("/logout") 
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
				service.loginUser(user.getId());
				
				
				/**Ha nem változtatta meg a jelszavát akkor a megváltoztató oldal a cél*/
				if(user.isChangedPwd() == false) {
					
					UserDto userDto = new UserDto(user.getId(), user.getName());
					model.addAttribute("userDto", userDto);
					targetPage = "changepwd.html";
				}
				/**Ha megváltoztatta már akkor pedig game.html*/
				else if(user.isChangedPwd() == true){
					
					GameDtoList gameDtoList = service.getGameDtoList(user.getId(), null, null);
					
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
	
	@GetMapping("/game/search/player")
	public String getAllGameByPlayer(
				Model model, 
				@RequestParam("userid") int userId,
				@RequestParam("searchedplayer") int searchedPlayerId
			) {
		
		String targetPage = "";
		
		GameDtoList gameDtoList = service.getGameDtoList(userId, null, searchedPlayerId);
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
	
	
	
	/** ------------------------------------------------------------------------------------- */
	/** -----------------------------------ADMIN--------------------------------------------- */
	/** ------------------------------------------------------------------------------------- */
	
	
	
	@PostMapping("/admin/reg/location")
	public String registerNewLocation(
			Model model,
			@RequestParam("adminid") int adminId,
			@RequestParam("locname") String locName,
			@RequestParam("address") String locAddress,
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

	@PostMapping("/admin/reg/game")
	public String registerNewGame(
				Model model,
				@RequestParam("adminid") int adminId,
				@RequestParam("player1id") int player1_id,
				@RequestParam("player1score") int score_player1,
				@RequestParam("player2id") int player2_id,
				@RequestParam("player2score") int score_player2,
				@RequestParam("locationid") int locationId,
				@RequestParam("localdate") LocalDateTime date
			
			) {
		
		String targetPage = "";
		
		AdminDto adminDto = service.registerNewGame(
				adminId, player1_id, 
				score_player1, 
				player2_id, 
				score_player2, 
				locationId, 
				date);
		
		ErrorDto errorDto = new ErrorDto("Sorry You need to log in!!");
		
		if(adminDto != null) {
			
			model.addAttribute("admindto", adminDto);
			targetPage = "admin.html";
		
		}else {
			
			model.addAttribute("errordto", errorDto);
			targetPage = "login.html";
		}
		
		return targetPage;
		
	}
	
	@PostMapping("/admin/reg/player")
	public String regNewPlayer(
			Model model,
			@RequestParam("adminid") int adminId,
			@RequestParam("playername") String playerName
			) {
		
		String targetPage = "";
		
		AdminDto adminDto = service.regPlayer(adminId, playerName);
		ErrorDto errorDto = new ErrorDto("Sorry You need to log in!!");
		
		if(adminDto != null) {
			
			model.addAttribute("admindto", adminDto);
			targetPage = "admin.html";
		}
		else {
			
			model.addAttribute("error", errorDto);
			targetPage = "login.html";
		}
		
		
		return targetPage;
	}
	
}









