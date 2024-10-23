package com.gorest.tests;

import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.gorest.api.ApiData;
import com.gorest.api.ApiService;

public class UserApiTests {
	private static Response itemResponse;
	private static int userId; // To hold the created user ID
	private static String jsonContent = "";
	private static Map<Object, Object> updatedFields = new HashMap<Object, Object>();


	@Test(dependsOnMethods = {}, alwaysRun = true) // No dependency, initial test
	public void testCreateUser() {
		try {

			//add user details in parameters
			updatedFields.put(ApiData.MAIL, ApiData.UNQUIE_MAIL_ID);

			// calling getJsonPayloadWithUpdatedDetails method to update details
			jsonContent = ApiService.getJsonPayloadWithUpdatedDetails(ApiData.GOREST_PAYLOAD, updatedFields);
			System.out.println("Print User Details :"+jsonContent.toString());

			// Send POST request to create a new user
			itemResponse = ApiService.createUser(jsonContent);

			// Validate response
			Assert.assertEquals(itemResponse.getStatusCode(), 201, 
					"Expected status code 201, but got " + itemResponse.getStatusCode());

			// Extract the user ID from the response
			userId = itemResponse.jsonPath().getInt("id");
			Assert.assertNotNull(userId, "User ID should not be null");
			System.out.println("User created with ID: " + userId);

		} catch (Exception e) {
			Assert.fail("Exception in testCreateUser: " + e.getMessage());
		}
	}


	@Test(dependsOnMethods = "testCreateUser")
	public void testGetUserDetails() {
		try {
			// Send GET request to retrieve user details
			itemResponse  = ApiService.getUser(userId);

			// Validate response
			Assert.assertEquals(itemResponse.getStatusCode(), 200, 
					"Expected status code 200, but got " + itemResponse.getStatusCode());

			// Validate the user name
			String userName = itemResponse.jsonPath().getString("name");
			Assert.assertEquals(userName, "John Doe", "User name mismatch");

			// Print response for debugging
			itemResponse.prettyPrint();

		} catch (Exception e) {
			Assert.fail("Exception in testGetUserDetails: " + e.getMessage());
		}
	}

	@Test(dependsOnMethods = "testGetUserDetails")
	public void testUpdateUser() {
		try {
			//add user details in parameters
			updatedFields.put(ApiData.MAIL, ApiData.UNQUIE_MAIL_ID);
			updatedFields.put(ApiData.NAME, ApiData.UNQUIE_NAME);
			
			// calling getJsonPayloadWithUpdatedDetails method to update details
			jsonContent = ApiService.getJsonPayloadWithUpdatedDetails(ApiData.GOREST_PAYLOAD, updatedFields);
			System.out.println("Print User Details :"+jsonContent.toString());

			// Send PUT request to update user details
			itemResponse = ApiService.updateUser(userId, jsonContent);

			// Validate response
			Assert.assertEquals(itemResponse.getStatusCode(), 200, 
					"Expected status code 200, but got " + itemResponse.getStatusCode());

			// Validate the updated name
			String updatedUserName = itemResponse.jsonPath().getString("name");
			Assert.assertEquals(updatedUserName, ApiData.UNQUIE_NAME, 
					"User name mismatch after update");
		} catch (Exception e) {
			Assert.fail("Exception in testUpdateUser: " + e.getMessage());
		}
	}

	@Test(dependsOnMethods = "testUpdateUser")
	public void testDeleteUser() {
		try {
			// Send DELETE request to remove the user
			itemResponse = ApiService.deleteUser(userId);

			// Validate response
			Assert.assertEquals(itemResponse.getStatusCode(), 204, 
					"Expected status code 204, but got " + itemResponse.getStatusCode());

			// Try to get the deleted user (should return 404)
			Response getResponse = ApiService.getUser(userId);
			Assert.assertEquals(getResponse.getStatusCode(), 404, 
					"Expected status code 404 for deleted user, but got " + getResponse.getStatusCode());

			System.out.println("User with ID " + userId + " successfully deleted");
		} catch (Exception e) {
			Assert.fail("Exception in testDeleteUser: " + e.getMessage());
		}
	}
}
