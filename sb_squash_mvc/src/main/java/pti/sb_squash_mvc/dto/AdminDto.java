package pti.sb_squash_mvc.dto;

import java.util.List;

public class AdminDto {
	


	private int userid;
	private List<UserDto> allPlayers;
	private List<LocationDto> allLocations;
	
	
	public AdminDto(int userid, List<UserDto> allPlayers, List<LocationDto> allLocations) {
		super();
		this.userid = userid;
		this.allPlayers = allPlayers;
		this.allLocations = allLocations;
	}


	public int getUserid() {
		return userid;
	}


	public void setUserid(int userid) {
		this.userid = userid;
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
		return "AdminDto [userid=" + userid + ", allPlayers=" + allPlayers + ", allLocations=" + allLocations + "]";
	}
	
}
