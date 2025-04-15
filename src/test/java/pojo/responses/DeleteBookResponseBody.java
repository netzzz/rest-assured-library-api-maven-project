package pojo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookResponseBody {
	@JsonProperty("msg")
	private String message;
	
	public DeleteBookResponseBody() {
	}

	public String getMessage() {
		return message;
	}
}
