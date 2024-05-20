package pti.sb_squash_mvc.dto;

import java.util.List;

public class AdminDto {
	
	private int userId;
	private List<UserDto> allPlayers;
	private List<LocationDto> allLocations;
	
	
	public AdminDto(int userId, List<UserDto> allPlayers, List<LocationDto> allLocations) {
		super();
		this.userId = userId;
		this.allPlayers = allPlayers;
		this.allLocations = allLocations;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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


	@Override
	public String toString() {
		return "AdminDto [userId=" + userId + ", allPlayers=" + allPlayers + ", allLocations=" + allLocations + "]";
	}
	
	
	

}
