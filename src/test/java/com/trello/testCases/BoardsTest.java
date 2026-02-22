package com.trello.testCases;

import com.trello.apis.BoardApis;
import com.trello.base.BaseTest;
import com.trello.models.Board;
import com.trello.steps.BoardSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BoardsTest extends BaseTest {

    @Test
    public void testCreateBoard(){
        Board board = BoardSteps.generateBoard();
        Response response = BoardApis.createBoard(board.name);
        Board returnedBoard = response.body().as(Board.class);

        assertThat(response.statusCode(), equalTo(200));
        assertThat(board.getName(), equalTo(returnedBoard.getName()));
    }

    @Test
    public void testGetBoard(){
        Board board = BoardSteps.generateBoard();
        Board returnedBoard = BoardApis.createBoard(board.name).body().as(Board.class);
        String createdBoardID = returnedBoard.getId();

        Board gettedBoard = BoardApis.getBoard(createdBoardID).body().as(Board.class);

        assertThat(createdBoardID, equalTo(gettedBoard.getId()));
        assertThat(returnedBoard.getName(), equalTo(gettedBoard.getName()));
    }

    @Test
    public void testDeleteBoard(){
        Board board = BoardSteps.generateBoard();
        Board returnedBoard = BoardApis.createBoard(board.name).body().as(Board.class);

        Response deleteBoardResponse = BoardApis.deleteBoard(returnedBoard.getId());
        assertThat(deleteBoardResponse.statusCode(), equalTo(200));
    }


}
