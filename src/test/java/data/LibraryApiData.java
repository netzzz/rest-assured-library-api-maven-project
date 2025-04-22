package data;

public class LibraryApiData {
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
