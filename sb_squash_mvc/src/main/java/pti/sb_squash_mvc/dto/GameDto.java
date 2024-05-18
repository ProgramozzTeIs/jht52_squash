package pti.sb_squash_mvc.dto;

import java.time.LocalDate;

public class GameDto {

	private String player1Name;
	private int player1Score;
	private String player2Name;
	private int player2Score;
	private String locationName;
	private LocalDate date;
	
	
	public GameDto(String player1Name, int player1Score, String player2Name, int player2Score, String locationName,
			LocalDate date) {
		super();
		this.player1Name = player1Name;
		this.player1Score = player1Score;
		this.player2Name = player2Name;
		this.player2Score = player2Score;
		this.locationName = locationName;
		this.date = date;
	}


	public String getPlayer1Name() {
		return player1Name;
	}


	public void setPlayer1Name(String player1Name) {
		this.player1Name = player1Name;
	}


	public int getPlayer1Score() {
		return player1Score;
	}


	public void setPlayer1Score(int player1Score) {
		this.player1Score = player1Score;
	}


	public String getPlayer2Name() {
		return player2Name;
	}


	public void setPlayer2Name(String player2Name) {
		this.player2Name = player2Name;
	}


	public int getPlayer2Score() {
		return player2Score;
	}


	public void setPlayer2Score(int player2Score) {
		this.player2Score = player2Score;
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "GameDto [player1Name=" + player1Name + ", player1Score=" + player1Score + ", player2Name=" + player2Name
				+ ", player2Score=" + player2Score + ", locationName=" + locationName + ", date=" + date + "]";
	}
	
}
