package com.trello.testCases;

import com.trello.apis.BoardApis;
import com.trello.models.Board;
import com.trello.steps.BoardSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.trello.apis.BoardApis.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class BoardsTest {

    @Test
    public void testCreateBoard(){
        // generate board
        Board board = BoardSteps.generateBoard();
        // send create board request
        Response response =createBoard(board.name);
        // store the response data in pojo class
        Board returnedBoard=  response.body().as(Board.class);

        assertThat(response.statusCode(),equalTo(200));
        assertThat(board.getName(),equalTo(returnedBoard.getName()));
    }

    @Test
    public void getBoard(){
        // generate board
        Board board = BoardSteps.generateBoard();
        // send create board request
        Response response =createBoard(board.name);
        Board returnedBoard=  response.body().as(Board.class);
        String createdBoardID =returnedBoard.getId();


        Response getBoardResponse = BoardApis.getBoard(createdBoardID);
        Board gettedBoard= getBoardResponse.body().as(Board.class);

        assertThat(getBoardResponse.statusCode(),equalTo(200));
        assertThat(createdBoardID,equalTo(gettedBoard.getId()));
        assertThat(returnedBoard.getName(),equalTo(gettedBoard.getName()));
    }
}
