package com.trello.apis;

import com.trello.base.Specs;
import com.trello.data.Route;
import io.restassured.response.Response;

import java.io.File;

public class CardApis {

    public static Response addCardToList(String cardName, String listID){
        return Specs.getRequestSpecs().queryParams("name",cardName,"idList",listID)
                .when().post(Route.CARDS).then().log().all().extract().response();
    }

    public static Response addAttachmentToCard(String cardID, String attachmentPath){
        File attach = new File(attachmentPath);
        return Specs.getRequestSpecs().contentType("multipart/form-data").queryParam("name","attachment").multiPart("file",attach)
                .when().post(Route.CARDS+"/"+cardID+"/"+Route.ATTACHMENTS)
                .then().log().all().extract().response();
    }

    public static Response deleteAttachment(String cardID, String attachID){
        return Specs.getRequestSpecs()
                .when().delete(Route.CARDS+"/"+cardID+"/"+Route.ATTACHMENTS+"/"+attachID)
                .then().log().all().extract().response();
    }

    public static Response deleteCard(String cardId){
        return Specs.getRequestSpecs()
                .when().delete(Route.CARDS+"/"+cardId)
                .then().log().all().extract().response();
    }
}
