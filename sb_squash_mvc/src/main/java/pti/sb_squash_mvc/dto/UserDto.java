package pti.sb_squash_mvc.dto;

public class UserDto {

	private int id;
	private String name;
	
	
	public UserDto(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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


	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + "]";
	}
	
}
