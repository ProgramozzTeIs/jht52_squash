package pti.sb_squash_mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.db.Database;
import pti.sb_squash_mvc.dto.AdminDto;
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
				List<GameDto> allGamesByLocation = new ArrayList<>();
				
				for(int index = 0; index < allGames.size(); index++) {
					Game currentGame = allGames.get(index);
					if(currentGame.getLocation_id() == locationId ){
						GameDto gameDto = convertGameToGameDto(currentGame);
						
						allGamesByLocation.add(gameDto);
					}
				}
				
				
				gameDtoList = new GameDtoList(gameDtos,userDtos,locationDtos,userDto );
				
				
			}else {
				gameDtoList = null;
			}
		
		return gameDtoList;
	}

	private GameDto convertGameToGameDto(Game game) {
		GameDto gameDto = null;
		User user1 = db.getPlayerById(game.getPlayer1_id());
		User user2 = db.getPlayerById(game.getPlayer2_id());
		Location location = db.getLocationById(game.getLocation_id());
		
		gameDto = new GameDto(
				user1.getName(),
				game.getPlayer1_score(),
				user2.getName(),
				game.getPlayer2_score(),
				location.getName(),
				game.getDate());
		
		return gameDto;
	}

	private UserDto convertUserToUserDto(User user) {
		UserDto userDto = new UserDto(user.getId(),user.getName());
		
		return userDto;
	}

	public User getUserByNameAndPassword(String name, String password) {
		
		User user = null;
		
		user = db.getPlayerByNameAndPwd(name, password);
		
		return user;
	}

	public AdminDto getAdminDto(User user) {
		
		AdminDto adminDto = null;
		
		List<LocationDto> allLocations = new ArrayList<>();
		List<UserDto> allPlayers = new ArrayList<>();
		
		List<User> usersFromDb = db.getAllPlayer();
		List<Location> locationsFromDb = db.getAllLocations();
		
		for(int index = 0; index < usersFromDb.size(); index++) {
			
			User currentUser = usersFromDb.get(index);
			UserDto userDto = new UserDto(currentUser.getId(),currentUser.getName());
			allPlayers.add(userDto);
		}
		
		for(int index = 0; index < locationsFromDb.size(); index++) {
			
			Location currentLocation = locationsFromDb.get(index);
			LocationDto locationDto = new LocationDto(currentLocation.getId(), currentLocation.getName());
			allLocations.add(locationDto);
		}
		
		adminDto = new AdminDto(
				user.getId(),
				allPlayers,
				allLocations
				);
		
		return adminDto;
	}

	public void logoutUser(int userId) {
		
		User user = db.getPlayerById(userId);
		user.setLoggedin(false);
		db.updatePlayer(user);
		
	}

	public GameDtoList getGameDtoList(UserDto userDto) {
		
		GameDtoList gameDtoList = null;

		List<GameDto> gameDtos = new ArrayList<>();
		List<LocationDto> allLocations = new ArrayList<>();
		List<UserDto> allPlayers = new ArrayList<>();
		
		List<Game> gamesFromDb = db.getAllGames();
		List<User> usersFromDb = db.getAllPlayer();
		List<Location> locationsFromDb = db.getAllLocations();
		
		for(int index = 0; index < gamesFromDb.size(); index++) {
			
			Game currentGame = gamesFromDb.get(index);
			User player1 = db.getPlayerById(currentGame.getPlayer1_id());
			User player2 = db.getPlayerById(currentGame.getPlayer2_id());
			Location location = db.getLocationById(currentGame.getLocation_id());
			
			GameDto gameDto = new GameDto(
					player1.getName(),
					currentGame.getPlayer1_score(),
					player2.getName(),
					currentGame.getPlayer2_score(),
					location.getName(),
					currentGame.getDate()
					);
			gameDtos.add(gameDto);
		}
		
		for(int index = 0; index < usersFromDb.size(); index++) {
			
			User currentUser = usersFromDb.get(index);
			UserDto currentUserDto = new UserDto(currentUser.getId(),currentUser.getName());
			allPlayers.add(currentUserDto);
		}
		
		for(int index = 0; index < locationsFromDb.size(); index++) {
			
			Location currentLocation = locationsFromDb.get(index);
			LocationDto locationDto = new LocationDto(currentLocation.getId(), currentLocation.getName());
			allLocations.add(locationDto);
		}
		
		return gameDtoList;
	}

	public void loginUser(int userId) {
		
		User user = db.getPlayerById(userId);
		user.setLoggedin(true);
		db.updatePlayer(user);
		
	}
	
}












