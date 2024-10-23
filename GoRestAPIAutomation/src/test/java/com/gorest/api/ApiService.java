package com.gorest.api;

import io.github.cdimascio.dotenv.Dotenv;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.json.JSONObject;

public class ApiService {
	
	// Load the environment variables from the .env file
    private static final Dotenv dotenv = Dotenv.load();
    
    // Base URL for the GoRest API. All endpoints will be appended to this URL.
    private static final String BASE_URL = "https://gorest.co.in/public/v2";
    
 // Get the API token from the .env file
    private static final String ACCESS_TOKEN = "Bearer " + dotenv.get("GOREST_API_TOKEN");

    /**
     * Creates a new user.
     * 
     * @param jsonBody - The JSON string containing user details.
     * @return Response - The response object containing the status and data of the created user.
     */
    public static Response createUser(String jsonBody) {
        try {
            return given()
            		.header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", ACCESS_TOKEN)
                    .body(jsonBody)
                    .when()
                    .post(BASE_URL + "/users");
        } catch (Exception e) {
            System.err.println("Error while creating user: " + e.getMessage());
            throw new RuntimeException("Failed to create user", e); // Re-throwing exception after logging
        }
    }

    /**
     * Retrieves the details of a specific user.
     * 
     * @param userId - The unique ID of the user to be retrieved.
     * @return Response - The response object containing user details.
     */
    public static Response getUser(int userId) {
        try {
            return given()
            		.header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", ACCESS_TOKEN)
                    .when()
                    .get(BASE_URL + "/users/" + userId);
        } catch (Exception e) {
            System.err.println("Error while retrieving user: " + e.getMessage());
            throw new RuntimeException("Failed to retrieve user", e);
        }
    }

    /**
     * Updates an existing user's details.
     * 
     * @param userId - The unique ID of the user to be updated.
     * @param jsonBody - The JSON string containing updated user details.
     * @return Response - The response object containing the status and updated user data.
     */
    public static Response updateUser(int userId, String jsonBody) {
        try {
            return given()
            		.header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", ACCESS_TOKEN)
                    .body(jsonBody)
                    .when()
                    .put(BASE_URL + "/users/" + userId);
        } catch (Exception e) {
            System.err.println("Error while updating user: " + e.getMessage());
            throw new RuntimeException("Failed to update user", e);
        }
    }

    /**
     * Deletes a specific user.
     * 
     * @param userId - The unique ID of the user to be deleted.
     * @return Response - The response object containing the status of the delete operation.
     */
    public static Response deleteUser(int userId) {
        try {
            return given()
            		.header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .header("Authorization", ACCESS_TOKEN)
                    .when()
                    .delete(BASE_URL + "/users/" + userId);
        } catch (Exception e) {
            System.err.println("Error while deleting user: " + e.getMessage());
            throw new RuntimeException("Failed to delete user", e);
        }
    }
    
    /**
     * Reads JSON from a file and dynamically updates specified fields.
     *
     * @param filePath   - The path to the JSON file.
     * @param parameters - A map containing the fields to be updated.
     * @return The updated JSON payload as a string.
     * @throws IOException If there is an error reading the file.
     */
    public static String getJsonPayloadWithUpdatedDetails(String filePath, Map<Object, Object> parameters) throws IOException {
        // Read JSON file as a string
        String jsonString = new String(Files.readAllBytes(Paths.get(filePath)));

        // Convert the string to a JSONObject
        JSONObject jsonObject = new JSONObject(jsonString);

        // Loop through the map and update the JSON object with new values
        for (Map.Entry<Object, Object> entry : parameters.entrySet()) {
            jsonObject.put(entry.getKey().toString(), entry.getValue());
        }

        // Return the updated JSON string
        return jsonObject.toString();
    }

}
