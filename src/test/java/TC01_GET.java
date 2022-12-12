import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import java.io.File;

import org.json.simple.JSONObject;

public class TC01_GET {


	@Test
	void testCase_01_GET() {

		given().get("https://reqres.in/api/users?page=2").
		then().statusCode(200).log().all();

	}
	
	@Test
	void testCase_02_GET() {

		given().
		get("https://reqres.in/api/users?page=2").
		then().
		body("total_pages", equalTo(2));

	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCase_03_POST() {
		
		JSONObject jObj = new JSONObject();
		jObj.put("name", "Mak");
		jObj.put("age", "22");

		given().
		header("Content-Type", "application/json").
		body(jObj.toJSONString()).
		when().
		post("https://reqres.in/api/users").
		then().
		statusCode(201);

	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCase_04_POST() {
		
		JSONObject jObj = new JSONObject();
		jObj.put("name", "morpheus");
		jObj.put("job", "leader");
		File schema = new File("C:/Users/vinit.kumar/Desktop/Int/API_Automation/schema.json");

		given().
		header("Content-Type", "application/json").
		body(jObj.toJSONString()).
		when().
		post("https://reqres.in/api/users").
		then().
		statusCode(201).
		log().all().
        body(matchesJsonSchema(schema));
//		body("name", equalTo("morpheus"));
//		Assert.assertEquals(true, true);
		

	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCase_05_PUT() {
		
		JSONObject jObj = new JSONObject();
		jObj.put("name", "morpheus");
		jObj.put("job", "zion resident");

		given().
		header("Content-Type", "application/json").
		body(jObj.toJSONString()).
		when().
		put("https://reqres.in/api/users/2").
		then().
		statusCode(200).
		log().all().
		body("name", equalTo("morpheus"));

	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCase_06_DELETE() {
		
		given().
		header("Content-Type", "application/json").
		when().
		delete("https://reqres.in/api/users/2").
		then().
		statusCode(204).
		log().all();

	}
	
	@SuppressWarnings("unchecked")
	@Test
	void testCase_07_PATCH() {
		
		JSONObject jObj = new JSONObject();
		jObj.put("name", "morpheus");
		jObj.put("job", "zion resident");

		given().
		header("Content-Type", "application/json").
		body(jObj.toJSONString()).
		when().
		patch("https://reqres.in/api/users/2").
		then().
		statusCode(200).
		log().all().
		body("name", equalTo("morpheus"));

	}


}
