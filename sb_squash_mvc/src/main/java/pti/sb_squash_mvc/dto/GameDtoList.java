package pti.sb_squash_mvc.dto;

import java.util.List;

public class GameDtoList {

	private List<GameDto> gameDtos;
	private List<UserDto> allPlayers;
	private List<LocationDto> allLocations;
	private UserDto loggedInUser;
	
	
	public GameDtoList(List<GameDto> gameDtos, List<UserDto> allPlayers, List<LocationDto> allLocations,
			UserDto loggedInUser) {
		super();
		this.gameDtos = gameDtos;
		this.allPlayers = allPlayers;
		this.allLocations = allLocations;
		this.loggedInUser = loggedInUser;
	}


	public List<GameDto> getGameDtos() {
		return gameDtos;
	}


	public void setGameDtos(List<GameDto> gameDtos) {
		this.gameDtos = gameDtos;
	}


	public List<UserDto> getAllPlayers() {
		return allPlayers;
	}


	public void setAllPlayers(List<UserDto> allPlayers) {
		this.allPlayers = allPlayers;
	}


	public List<LocationDto> getAllLocations() {
		return allLocations;
	}


	public void setAllLocations(List<LocationDto> allLocations) {
		this.allLocations = allLocations;
	}


	public UserDto getLoggedInUser() {
		return loggedInUser;
	}


	public void setLoggedInUser(UserDto loggedInUser) {
		this.loggedInUser = loggedInUser;
	}


	@Override
	public String toString() {
		return "GameDtoList [gameDtos=" + gameDtos + ", allPlayers=" + allPlayers + ", allLocations=" + allLocations
				+ ", loggedInUser=" + loggedInUser + "]";
	}
	
}
