package helperfunctions;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Reporter;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class HelperFunctions {
	private static final Logger logger = LogManager.getLogger(HelperFunctions.class);

	public static JsonPath convertRestAssuredResponseToJson(Response response) {
		JsonPath responseJson = response.jsonPath();
		return responseJson;
	}

	public static JSONObject readJsonFromFile(String jsonFilePath) {
		JSONObject jsonObject = null;

		try {
			JSONParser jsonParser = new JSONParser();
			FileReader fileReader = new FileReader(jsonFilePath);
			jsonObject = (JSONObject) jsonParser.parse(fileReader);
		} catch (FileNotFoundException e) {
			logger.error("Json File is not found: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Json File is not read successfully." + e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			logger.error("Json File is not parsed successfully." + e.getMessage());
			e.printStackTrace();
		}

		return jsonObject;
	}

	public static int getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min + 1) + min;
	}
	
	public static void logToReportAndLog4j(String message) {
		logger.info(message);
		Reporter.log("INFO: " + message);
	}
	
	public static void logToReportAndLog4j(String message, Throwable t) {
		logger.error(message, t);
		Reporter.log("ERROR: " + message + "; "+ t.getMessage());
	}
}
