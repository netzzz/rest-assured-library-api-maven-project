package testmethods;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import helperfunctions.HelperFunctions;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import data.HttpResponseStatusCodes;
import data.LibraryApiData;

public class LibraryApiTestMethods {
	private static final Logger logger = LogManager.getLogger(LibraryApiTestMethods.class.getName());
	
	private static String baseURI = "http://216.10.245.166";
	
	
	// Add Book Methods
	
	public static Response addBook(String bookName, String bookIsbn, String bookAisle, String bookAuthor) {
		RestAssured.baseURI = baseURI;
		return given().header("Content-Type", "application/json")
				.body(LibraryApiData.addBookRequestBody(bookName,bookIsbn,bookAisle,bookAuthor))
				.when().post("Library/Addbook.php")
				.then().extract().response();
	}
	
	
	public static String getAddBookMessage(Response addBookResponse) {
		JsonPath addBookResponseJson = HelperFunctions.convertRestAssuredResponseToJson(addBookResponse);
		return addBookResponseJson.getString("Msg");
	}
	
	public static String getAddBookId(Response addBookResponse){
		JsonPath addBookResponseJson = HelperFunctions.convertRestAssuredResponseToJson(addBookResponse);
		return addBookResponseJson.getString("ID");
	}
	
	public static void validateAddBook(Response addBookResponse){
		int addBookStatusCode = addBookResponse.getStatusCode();
		
		Assert.assertEquals(addBookStatusCode, HttpResponseStatusCodes.OK.getCode(),
				String.format("Add Book Response Status Code is not %d as not Expected", HttpResponseStatusCodes.OK.getCode()));
		logger.info(String.format("Add Book Response Status Code is %d as Expected", HttpResponseStatusCodes.OK.getCode()));
			
		String addBookMessage = getAddBookMessage(addBookResponse);
		Assert.assertEquals(addBookMessage, LibraryApiData.LibraryApiResponseMessages.SUCCESSFULLYADDED.getMessage(), 
				String.format("Add Book Response Message is '%s' as Not Expected", addBookMessage));
		logger.info(String.format("Add Book Response Message is: '%s' as Expected", LibraryApiData.LibraryApiResponseMessages.SUCCESSFULLYADDED.getMessage()));
	}
	
	
	// --------------------------------------------
	// Get Book by Id Methods
	
	public static Response getBookById(String bookId) {
		return given()
				.when().post("Library/GetBook.php?ID="+bookId)
				.then().extract().response();
	}
	
	public static void validateGetBookById(String bookId) {
		Response getBookById = getBookById(bookId);
		int getBookByIdStatusCode = getBookById.getStatusCode();
		
		Assert.assertEquals(getBookByIdStatusCode, HttpResponseStatusCodes.OK.getCode(), 
				String.format("Get Book by Id Status Code is %d as Not Expected,", getBookByIdStatusCode));
		logger.info(String.format("Get Book by ID Response Status Code is %d as Expected", HttpResponseStatusCodes.OK.getCode()));
	}
	
	// --------------------------------------------
	// Get Book by Author Methods
	
	public static String getAuthorOfBook(String bookId) {
		Response getBookResponse = getBookById(bookId);
		JsonPath getBookResponseJson = HelperFunctions.convertRestAssuredResponseToJson(getBookResponse);
		return getBookResponseJson.getString("author[0]");
	}
	
	public static Response getBookByAuthor(String authorName) {
		return given()
			.when().post("Library/GetBook.php?AuthorName="+authorName)
			.then().extract().response();
	}
	
	public static void validateGetBookByAuthor(String authorName) {
		Response getBookByAuthor = getBookByAuthor(authorName);
		int getBookByAuthorStatusCode = getBookByAuthor.getStatusCode();
		
		Assert.assertEquals(getBookByAuthorStatusCode, HttpResponseStatusCodes.OK.getCode(), 
				String.format("Get Book by Author Status Code is %d as Not Expected,", getBookByAuthorStatusCode));
		logger.info(String.format("Get Book by Author Response Status Code is %d as Expected", HttpResponseStatusCodes.OK.getCode()));
	}
	
	// --------------------------------------------
	// Delete Book Methods
	
	public static Response deleteBook(String bookId) {
		RestAssured.baseURI = baseURI;
		return given().header("Content-Type", "application/json")
				.body(LibraryApiData.deleteBookRequestBody(bookId))
				.when().post("Library/DeleteBook.php")
				.then().extract().response();
	}
	
	public static String getDeleteBookMessage(Response deleteBookResponse) {
		JsonPath deleteBookResponseJson = HelperFunctions.convertRestAssuredResponseToJson(deleteBookResponse);
		return deleteBookResponseJson.getString("msg");
	}
	
	public static void validateDeleteBook(String bookId) {
		Response deleteBook = deleteBook(bookId);
		int deleteBookStatusCode = deleteBook.getStatusCode();
		
		Assert.assertEquals(deleteBookStatusCode, HttpResponseStatusCodes.OK.getCode(),
				String.format("Delete Book Response Status Code is %d as Not Expected", deleteBookStatusCode));
		logger.info(String.format("Delete Book Response Status Code is %d as Expected", HttpResponseStatusCodes.OK.getCode()));
		
		String deleteBookMessage = getDeleteBookMessage(deleteBook);
		Assert.assertEquals(deleteBookMessage, LibraryApiData.LibraryApiResponseMessages.SUCCESSFULLYDELETED.getMessage(),
				String.format("Delete Book Response Message is '%s' as Not Expected", deleteBookMessage));
		logger.info(String.format("Delete Book Response Message is: '%s' as Expected", 
				LibraryApiData.LibraryApiResponseMessages.SUCCESSFULLYDELETED.getMessage()));
	}

}
