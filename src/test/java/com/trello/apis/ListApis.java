package com.trello.apis;

import com.trello.base.Specs;
import com.trello.data.Route;
import com.trello.models.BoardList;
import io.restassured.response.Response;

public class ListApis {

    public static Response addListToBoard(BoardList list){
        return Specs.getRequestSpecs().queryParams("name",list.getName(),"idBoard",list.getIdBoard())
                .when().post(Route.LISTS)
                .then().log().all().extract().response();
    }

    public static Response getList(BoardList list){
        return Specs.getRequestSpecs()
                .when().get(Route.LISTS+"/"+list.getId())
                .then().log().all().extract().response();
    }
}
