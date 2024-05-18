package pti.sb_squash_mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.dto.GameDto;
import pti.sb_squash_mvc.dto.GameDtoList;
import pti.sb_squash_mvc.dto.LocationDto;
import pti.sb_squash_mvc.dto.UserDto;
import pti.sb_squash_mvc.model.Game;
import pti.sb_squash_mvc.model.Location;
import pti.sb_squash_mvc.model.User;

@Service
public class AppService {
	
	private Database db;
	
	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}

	public UserDto changePassword(int userId, String newPsw) {
		UserDto userDto = null;
		
		User user = db.getPlayerById(userId);
		
		if( user != null && user.isChangedPwd() == false) {
			user.setPassword(newPsw);
			user.setChangedPwd(true);
			userDto = convertUserToUserDto(user);
		}else {
			userDto = null;
		}
		
		 
		
		return userDto;
	}

	public GameDtoList getAllGameByLocation(int userId, String searchedLocation) {
		
		GameDtoList gameDtoList = null;
		
		List<UserDto> userDtos = new ArrayList<>();
		List<User> allUsers = db.getAllPlayer();
		for(int index_allPlayers = 0; index_allPlayers < allUsers.size();index_allPlayers++) {
			User currentUser = allUsers.get(index_allPlayers);
			UserDto currUserDto = new UserDto(currentUser.getId(), currentUser.getName());
			userDtos.add(currUserDto);
		}
		
		UserDto userDto = null;
		User user = db.getPlayerById(userId);
			if(user.isLoggedin() == true && user.isChangedPwd()== true) {
				
				userDto = convertUserToUserDto(user);
				
				List<Location> locations = db.getAllLocations();
				List<LocationDto> locationDtos = new ArrayList<>();
				
				int locationId = 0;
				for(int index_loc = 0; index_loc < locations.size(); index_loc++) {
					Location currLocation = locations.get(index_loc);
					
					LocationDto currLocationDto = new LocationDto(currLocation.getId(),currLocation.getName());
					locationDtos.add(currLocationDto);
					if(searchedLocation.equals(currLocation.getName())) {
						locationId = currLocation.getId();
						
					}
					
				}
				List<GameDto> gameDtos = new ArrayList<>();
				List<Game> allGames = db.getAllGames();
				List<Game> allGamesByLocation = new ArrayList<>();
				
				for(int index = 0; index < allGames.size(); index++) {
					Game currentGame = allGames.get(index);
					if(currentGame.getLocation_id() == locationId ){
						GameDto gameDto = convertGameToGameDto(currentGame);
						gameDtos.add(gameDto);
					}
				}
				
				
				//gameDtoList = new GameDtoList(gameDtos,userDtos,locationDtos,userDto )
				
				
			}else {
				gameDtoList = null;
			}
		
		return gameDtoList;
	}
	
	
	

}
