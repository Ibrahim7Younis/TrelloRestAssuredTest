package com.trello.testCases;

import com.trello.apis.BoardApis;
import com.trello.apis.ListApis;
import com.trello.base.BaseTest;
import com.trello.models.Board;
import com.trello.models.BoardList;
import com.trello.steps.BoardSteps;
import com.trello.steps.ListSteps;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ListsTest extends BaseTest {

    @Test
    public void addListToBoardTest(){
        Board board = BoardSteps.generateBoard();
        Board returnedBoard = BoardApis.createBoard(board.name).body().as(Board.class);

        BoardList list = ListSteps.generateList();
        list.setIdBoard(returnedBoard.id);

        BoardList returnedList = ListApis.addListToBoard(list).body().as(BoardList.class);

        assertThat(returnedList.getIdBoard(), equalTo(returnedBoard.getId()));
        assertThat(returnedList.getName(), equalTo(list.getName()));
    }

    @Test
    public void testGetList(){
        Board board = BoardSteps.generateBoard();
        Board returnedBoard = BoardApis.createBoard(board.name).body().as(Board.class);

        BoardList list = ListSteps.generateList();
        list.setIdBoard(returnedBoard.id);

        BoardList createdList = ListApis.addListToBoard(list).body().as(BoardList.class);
        BoardList gottenList = ListApis.getList(createdList).body().as(BoardList.class);

        assertThat(createdList.getId(), equalTo(gottenList.getId()));
        assertThat(createdList.getName(), equalTo(gottenList.getName()));
    }
}
