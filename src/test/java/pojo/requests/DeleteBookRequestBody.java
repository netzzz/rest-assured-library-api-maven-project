package pojo.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteBookRequestBody {
	@JsonProperty("ID")
	private String bookId;
	
	public DeleteBookRequestBody() {
	}
	
	public DeleteBookRequestBody(String bookId) {
		this.bookId = bookId;
	}
		
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
}
