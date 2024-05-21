package pti.sb_squash_mvc.service;

import java.time.LocalDateTime;
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
	

	public void loginUser(int userId) {
		
		User user = db.getPlayerById(userId);
		user.setLoggedin(true);
		db.updatePlayer(user);
	}
	
	public void logoutUser(int userId) {
		
		User user = db.getPlayerById(userId);
		user.setLoggedin(false);
		db.updatePlayer(user);
	}

	public void changePassword(int userId, String newPsw) {
		
		User user = db.getPlayerById(userId);
		
		if( user != null && user.isChangedPwd() == false) {
			user.setPassword(newPsw);
			user.setChangedPwd(true);
			
			db.updatePlayer(user);
		}
	}
	
	public User getUserByNameAndPassword(String name, String password) {
		
		User user = null;
		
		user = db.getPlayerByNameAndPwd(name, password);
		
		return user;
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

	
	public GameDtoList getGameDtoList(int userId, String locationName, Integer playerId) {
		
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
			
			
			if( ((location.getName().equals(locationName)) || (location == null)) &&
					((player1.getId() == playerId) || (player2.getId() == playerId) || (playerId == null)) ) {
				
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
		
		User user = db.getPlayerById(userId);
		UserDto userDto = convertUserToUserDto(user);
		gameDtoList = new GameDtoList(gameDtos,allPlayers,allLocations,userDto );
		
		gameDtoList.sortGameDates();
		
		return gameDtoList;
	}
	
	
	
	/** ------------------------------------------------------------------------------------- */
	/** -----------------------------------ADMIN--------------------------------------------- */
	/** ------------------------------------------------------------------------------------- */
	
	
	
	public AdminDto registerNewLocation(int adminId, String locName, String locAddress, int fee) {
		AdminDto adminDto = null;
		
		User user = db.getPlayerById(adminId);
		if(user.getRole().equals("admin") && user.isLoggedin()) {
			Location locatoin = new Location(0,locName,locAddress,fee);
			db.saveLocation(locatoin);
			adminDto = getAdminDto(user);
		}
		else {
			adminDto = null;
		}
		
		return adminDto;
	}
	
	
	public AdminDto registerNewGame(int adminId, int player1_id, int score_player1, int player2_id, int score_player2,
			int locationId, LocalDateTime date) {
		
		AdminDto adminDto = null;
		
		User user = db.getPlayerById(adminId);
		if(user.getRole().equals("admin") && user.isLoggedin()) {
			
			Game game = new Game(0, player1_id, score_player1, player2_id, score_player2, locationId, date);
			db.saveGame(game);
			
			adminDto = getAdminDto(user);
			
		}
		
		return adminDto;
	}

	

	
	
}












