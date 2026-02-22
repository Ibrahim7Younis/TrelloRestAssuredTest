package com.trello.base;

import com.trello.data.Route;
import com.trello.utils.ConfigManager;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class Specs {

    protected static final ConfigManager config = ConfigManager.getInstance();

    /**
     * Builds a base RequestSpecification shared across all API calls.
     * Uses OAuth 1.0 authentication with credentials loaded from auth.properties.
     * All requests log full details (method, URI, headers, body) for debugging.
     */
    public static RequestSpecification getRequestSpecs(){
        return given().baseUri(Route.PROD_URI).contentType(ContentType.JSON).log().all()
                .auth().oauth(
                        config.getConsumerKey(),    // API Key from Trello developer portal
                        config.getConsumerSecret(), // API Key Secret from Trello developer portal
                        config.getAccessToken(),    // Generated Token (never expires by default)
                        config.getTokenSecret());   // Generated Token Secret
    }
}
