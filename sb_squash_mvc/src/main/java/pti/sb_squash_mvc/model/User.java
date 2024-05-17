package pti.sb_squash_mvc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="players")
public class User {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="role")
	private String role;
	
	@Column(name="changedPwd")
	private boolean changedPwd;
	
	@Column(name="loggedin")
	private boolean loggedin;
	
	public User() {
		
	}

	public User(int id, String name, String password, String role, boolean changedPwd, boolean loggedin) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.role = role;
		this.changedPwd = changedPwd;
		this.loggedin = loggedin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isChangedPwd() {
		return changedPwd;
	}

	public void setChangedPwd(boolean changedPwd) {
		this.changedPwd = changedPwd;
	}

	public boolean isLoggedin() {
		return loggedin;
	}

	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}
	
	
	
	
}
