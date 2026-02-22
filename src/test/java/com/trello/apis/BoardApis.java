package com.trello.apis;

import com.trello.base.Specs;
import com.trello.data.Route;
import com.trello.models.Board;
import io.restassured.response.Response;

public class BoardApis {

    public static Response createBoard(String boardName){
        return Specs.getRequestSpecs().queryParam("name",boardName)
                .when().post(Route.BOARDS)
                .then().log().all().extract().response();
    }

    public static Response getBoard(String boardId){
        return Specs.getRequestSpecs()
                .when().get(Route.BOARDS+"/"+boardId)
                .then().log().all().extract().response();
    }

    public static Response deleteBoard(String boardId){
        return Specs.getRequestSpecs()
                .when().delete(Route.BOARDS+"/"+boardId)
                .then().log().all().extract().response();
    }

    public static Response getMemberBoards(){
        return Specs.getRequestSpecs()
                .when().get(Route.MEMBERS+"/me/"+Route.BOARDS)
                .then().log().all().extract().response();
    }
}
