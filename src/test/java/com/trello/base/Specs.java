package com.trello.base;

import com.trello.data.Route;
import com.trello.utils.ConfigManager;
import io.restassured.specification.RequestSpecification;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

public class Specs {
    protected static final ConfigManager config = ConfigManager.getInstance();
    public static RequestSpecification getRequestSpecs(){
        return given().baseUri(Route.PROD_URI).contentType(ContentType.JSON).log().all()
                .auth().oauth(
                        config.getConsumerKey(),
                        config.getConsumerSecret(),
                        config.getAccessToken(),
                        config.getTokenSecret());
    }
}
