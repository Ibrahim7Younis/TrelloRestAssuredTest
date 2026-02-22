package com.trello.base;

import com.trello.apis.BoardApis;
import com.trello.models.Board;
import org.testng.annotations.AfterSuite;

import java.util.List;

/**
 * Base class for all test classes.
 * Provides shared suite-level lifecycle hooks.
 * All test classes (BoardsTest, ListsTest, CardsTest) extend this class.
 */
public class BaseTest {

    /**
     * Runs once after the entire test suite completes.
     * Fetches all boards belonging to the authenticated member and deletes them,
     * ensuring a clean state in the Trello account after each test run.
     */
    @AfterSuite
    public void deleteAllBoards(){
        List<Board> boards = BoardApis.getMemberBoards().body().jsonPath().getList("$", Board.class);
        for(Board board : boards){
            BoardApis.deleteBoard(board.getId());
        }
    }
}
