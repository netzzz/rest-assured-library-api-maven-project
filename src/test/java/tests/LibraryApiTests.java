package tests;

import org.testng.annotations.Test;

import data.HttpResponseStatusCodes;
import io.restassured.response.Response;
import testmethods.LibraryApiTestMethods;


public class LibraryApiTests {
	@Test
	public void validateLibraryApi() {
		Response addBookResponse = LibraryApiTestMethods.addBook("Some Book 111", "someisbn111", "1111", "Test Author111");
		LibraryApiTestMethods.validateResponseStatusCode("Add Book", addBookResponse, HttpResponseStatusCodes.OK);
		LibraryApiTestMethods.validateAddBookResponseMessage(addBookResponse);

		String addedBookId = LibraryApiTestMethods.getAddedBookId(addBookResponse);
		Response getBookById = LibraryApiTestMethods.getBookById(addedBookId);
		LibraryApiTestMethods.validateResponseStatusCode("Get Book by Id", getBookById, HttpResponseStatusCodes.OK);

		String addedBookAuthor = LibraryApiTestMethods.getAuthorOfBook(addedBookId);
		Response getBookByAuthor = LibraryApiTestMethods.getBookByAuthor(addedBookAuthor);
		LibraryApiTestMethods.validateResponseStatusCode("Get Book by Author", getBookByAuthor, HttpResponseStatusCodes.OK);

		Response deleteBook = LibraryApiTestMethods.deleteBook(addedBookId);
		LibraryApiTestMethods.validateResponseStatusCode("Delete Book", deleteBook, HttpResponseStatusCodes.OK);
		LibraryApiTestMethods.validateDeleteBookResponseMessage(deleteBook);
	}	
}
