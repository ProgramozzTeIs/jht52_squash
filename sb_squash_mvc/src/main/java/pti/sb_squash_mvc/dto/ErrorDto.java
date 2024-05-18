package pti.sb_squash_mvc.dto;

public class ErrorDto {

	private String message;

	public ErrorDto(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorDto [message=" + message + "]";
	}
	
}
