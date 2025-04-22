package testmethods;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.requests.AddBookRequestBody;
import pojo.requests.DeleteBookRequestBody;
import pojo.responses.AddBookResponseBody;
import pojo.responses.DeleteBookResponseBody;
import pojo.responses.GetBookByIdResponseBody;
import data.HttpResponseStatusCodes;
import data.LibraryApiData;
import helperfunctions.HelperFunctions;

public class LibraryApiTestMethods {
	private static String baseURI = "http://216.10.245.166";
	
	
	// Add Book Methods
	
	public static Response addBook(String bookName, String bookIsbn, String bookAisle, String bookAuthor) {
		RestAssured.baseURI = baseURI;
		AddBookRequestBody bookToAdd = new AddBookRequestBody(bookName,bookIsbn,bookAisle,bookAuthor);
		
		return given().header("Content-Type", "application/json")
				.body(bookToAdd)
				.when().post("Library/Addbook.php")
				.then().extract().response();
	}
	
	public static String getAddedBookId(Response addBookResponse){
		AddBookResponseBody addBookResponseBody = addBookResponse.as(AddBookResponseBody.class);
		return addBookResponseBody.getAddedBookId();
	}

	public static void validateAddBookResponseMessage(Response addBookResponse){
		AddBookResponseBody addBookResponseBody = addBookResponse.as(AddBookResponseBody.class);
		String addBookMessage = addBookResponseBody.getResponseMessage();
		
		Assert.assertEquals(addBookMessage, LibraryApiData.LibraryApiResponseMessages.SUCCESSFULLYADDED.getMessage(), 
				String.format("Add Book Response Message is '%s' as Not Expected", addBookMessage));
		HelperFunctions.logToReportAndLog4j(String.format("Add Book Response Message is: '%s' as Expected", 
				LibraryApiData.LibraryApiResponseMessages.SUCCESSFULLYADDED.getMessage()));
	}

	// --------------------------------------------
	// Get Book by Id Methods
	
	public static Response getBookById(String bookId) {
		return given()
				.when().post("Library/GetBook.php?ID="+bookId)
				.then().extract().response();
	}
	
	// --------------------------------------------
	// Get Book by Author Methods
	
	public static String getAuthorOfBook(String bookId) {
		GetBookByIdResponseBody[] getBookByIdResponseBody =  getBookById(bookId).as(GetBookByIdResponseBody[].class);		
		return getBookByIdResponseBody[0].getAuthor();
	}
	
	public static Response getBookByAuthor(String authorName) {
		return given()
			.when().post("Library/GetBook.php?AuthorName="+authorName)
			.then().extract().response();
	}
	
	// --------------------------------------------
	// Delete Book Methods
	
	public static Response deleteBook(String bookId) {
		RestAssured.baseURI = baseURI;
		DeleteBookRequestBody deleteBookRequestBody = new DeleteBookRequestBody(bookId);
		
		return given().header("Content-Type", "application/json")
				.body(deleteBookRequestBody)
				.when().post("Library/DeleteBook.php")
				.then().extract().response();
	}
	

	public static void validateDeleteBookResponseMessage(Response deleteBook) {
		DeleteBookResponseBody deleteBookResponseBody = deleteBook.as(DeleteBookResponseBody.class);
		
		Assert.assertEquals(deleteBookResponseBody.getMessage(), LibraryApiData.LibraryApiResponseMessages.SUCCESSFULLYDELETED.getMessage(),
				String.format("Delete Book Response Message is '%s' as Not Expected", deleteBookResponseBody.getMessage()));
		HelperFunctions.logToReportAndLog4j(String.format("Delete Book Response Message is: '%s' as Expected", 
				LibraryApiData.LibraryApiResponseMessages.SUCCESSFULLYDELETED.getMessage()));
	}
	
	// --------------------------------------------
	// Response Validation Methods
	
	public static void validateResponseStatusCode(String requestName, Response response, 
			HttpResponseStatusCodes responseStatusCode) {
		Assert.assertEquals(response.getStatusCode(), responseStatusCode.getCode(),
				String.format("%s Response Status Code is %d as Not Expected", requestName, response.getStatusCode()));
		HelperFunctions.logToReportAndLog4j(String.format("%s Response Status Code is %d as Expected", requestName,
				responseStatusCode.getCode()));
	}

}
