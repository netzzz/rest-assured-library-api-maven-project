package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import testmethods.LibraryApiTestMethods;


public class LibraryApiTests {
	@Test
	public void validateLibraryApi() {
		Response addBookResponse = LibraryApiTestMethods.addBook("Some Book Example 123", "someisbncodeexample", "18346875", "Test Author 19346");
		LibraryApiTestMethods.validateAddBook(addBookResponse);
		
		String addedBookId = LibraryApiTestMethods.getAddBookId(addBookResponse);
		LibraryApiTestMethods.validateGetBookById(addedBookId);
		
		String addedBookAuthor = LibraryApiTestMethods.getAuthorOfBook(addedBookId);
		LibraryApiTestMethods.validateGetBookByAuthor(addedBookAuthor);
				
		LibraryApiTestMethods.validateDeleteBook(addedBookId);
	}
	

}
