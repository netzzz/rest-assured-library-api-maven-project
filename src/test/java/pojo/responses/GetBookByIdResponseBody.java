package pojo.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBookByIdResponseBody {
	@JsonProperty("book_name")
	private String bookName;
	private String isbn;
	private String aisle;
	private String author;
	
	public GetBookByIdResponseBody() {
	}
	
	public String getBookName() {
		return bookName;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getAisle() {
		return aisle;
	}

	public String getAuthor() {
		return author;
	}
}
