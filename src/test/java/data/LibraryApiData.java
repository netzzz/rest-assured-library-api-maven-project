package data;

public class LibraryApiData {
	public static String addBookRequestBody(String bookName, String bookIsbn, String bookAisle, String bookAuthor) {
		return "{\r\n"
				+ "\r\n"
				+ "\"name\":\""+bookName+"\",\r\n"
				+ "\"isbn\":\""+bookIsbn+"\",\r\n"
				+ "\"aisle\":\""+bookAisle+"\",\r\n"
				+ "\"author\":\""+bookAuthor+"\"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public static String deleteBookRequestBody(String bookId) {
		return "{\r\n"
				+ " \r\n"
				+ "\"ID\" : \""+bookId+"\"\r\n"
				+ " \r\n"
				+ "} \r\n"
				+ "";
	}
	
	public static enum LibraryApiResponseMessages {
	    SUCCESSFULLYADDED("successfully added"),
	    SUCCESSFULLYDELETED("book is successfully deleted");
		

	    private final String message;

	    // Constructor
	    LibraryApiResponseMessages(String message) {

	        this.message = message;
	    }

	    // Getter methods

	    public String getMessage() {
	        return message;
	    }
	}
}
