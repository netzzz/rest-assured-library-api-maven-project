package pojo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookResponseBody {
	@JsonProperty("Msg")
	private String responseMessage;
	@JsonProperty("ID")
	private String addedBookId;
	
	public AddBookResponseBody() {
	}
	
	public String getResponseMessage() {
		return responseMessage;
	}

	public String getAddedBookId() {
		return addedBookId;
	}
}
