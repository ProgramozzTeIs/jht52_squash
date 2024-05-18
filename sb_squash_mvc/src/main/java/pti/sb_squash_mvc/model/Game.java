package pti.sb_squash_mvc.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="games")
public class Game {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="player1_id")
	private int player1_id;
	
	@Column(name="player2_id")
	private int player2_id;
	
	@Column(name="player1_score")
	private int player1_score;
	
	@Column(name="player2_score")
	private int player2_score;
	
	@Column(name="location_id")
	private int location_id;
	
	@Column(name="date")
	private LocalDateTime date;
	
	public Game() {
		
	}

	public Game(int id, int player1_id, int player2_id, int player1_score, int player2_score, int location_id,
			LocalDateTime date) {
		super();
		this.id = id;
		this.player1_id = player1_id;
		this.player2_id = player2_id;
		this.player1_score = player1_score;
		this.player2_score = player2_score;
		this.location_id = location_id;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPlayer1_id() {
		return player1_id;
	}

	public void setPlayer1_id(int player1_id) {
		this.player1_id = player1_id;
	}

	public int getPlayer2_id() {
		return player2_id;
	}

	public void setPlayer2_id(int player2_id) {
		this.player2_id = player2_id;
	}

	public int getPlayer1_score() {
		return player1_score;
	}

	public void setPlayer1_score(int player1_score) {
		this.player1_score = player1_score;
	}

	public int getPlayer2_score() {
		return player2_score;
	}

	public void setPlayer2_score(int player2_score) {
		this.player2_score = player2_score;
	}

	public int getLocation_id() {
		return location_id;
	}

	public void setLocation_id(int location_id) {
		this.location_id = location_id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	
}
