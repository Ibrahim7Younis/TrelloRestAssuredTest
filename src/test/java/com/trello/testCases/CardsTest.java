package com.trello.testCases;

import com.trello.apis.BoardApis;
import com.trello.apis.CardApis;
import com.trello.apis.ListApis;
import com.trello.base.BaseTest;
import com.trello.models.Attachment;
import com.trello.models.Board;
import com.trello.models.BoardList;
import com.trello.models.Card;
import com.trello.steps.BoardSteps;
import com.trello.steps.CardSteps;
import com.trello.steps.ListSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class CardsTest extends BaseTest {

    @Test
    public void testAddCardToList(){
        Board board = BoardSteps.generateBoard();
        Board returnedBoard = BoardApis.createBoard(board.name).body().as(Board.class);

        BoardList list = ListSteps.generateList();
        list.setIdBoard(returnedBoard.id);
        BoardList createdList = ListApis.addListToBoard(list).body().as(BoardList.class);

        Card card = CardSteps.generateCard();
        Response cardResponse = CardApis.addCardToList(card.getName(), createdList.getId());
        Card createdCard = cardResponse.body().as(Card.class);

        assertThat(cardResponse.statusCode(), equalTo(200));
        assertThat(card.getName(), equalTo(createdCard.getName()));
        assertThat(createdList.getId(), equalTo(createdCard.getIdList()));
    }

    @Test
    public void testCreateAttachmentOnCard(){
        Board board = BoardSteps.generateBoard();
        Board returnedBoard = BoardApis.createBoard(board.name).body().as(Board.class);

        BoardList list = ListSteps.generateList();
        list.setIdBoard(returnedBoard.id);
        BoardList createdList = ListApis.addListToBoard(list).body().as(BoardList.class);

        Card card = CardSteps.generateCard();
        Card createdCard = CardApis.addCardToList(card.getName(), createdList.getId()).body().as(Card.class);

        String filePath = "src/test/resources/file-sample_150kB.pdf";
        Response attachmentResponse = CardApis.addAttachmentToCard(createdCard.getId(), filePath);

        attachmentResponse.then().assertThat().statusCode(200);
        attachmentResponse.then().assertThat().body("$", hasKey("id"));
    }

    @Test
    public void testDeleteAttachment(){
        Board board = BoardSteps.generateBoard();
        Board returnedBoard = BoardApis.createBoard(board.name).body().as(Board.class);

        BoardList list = ListSteps.generateList();
        list.setIdBoard(returnedBoard.id);
        BoardList createdList = ListApis.addListToBoard(list).body().as(BoardList.class);

        Card card = CardSteps.generateCard();
        Card createdCard = CardApis.addCardToList(card.getName(), createdList.getId()).body().as(Card.class);

        String filePath = "src/test/resources/file-sample_150kB.pdf";
        Attachment createdAttachment = CardApis.addAttachmentToCard(createdCard.getId(), filePath).as(Attachment.class);

        Response deletedAttachResponse = CardApis.deleteAttachment(createdCard.getId(), createdAttachment.getId());
        assertThat(deletedAttachResponse.statusCode(), equalTo(200));
    }

    @Test
    public void testDeleteCard(){
        Board board = BoardSteps.generateBoard();
        Board returnedBoard = BoardApis.createBoard(board.name).body().as(Board.class);

        BoardList list = ListSteps.generateList();
        list.setIdBoard(returnedBoard.id);
        BoardList createdList = ListApis.addListToBoard(list).body().as(BoardList.class);

        Card card = CardSteps.generateCard();
        Card createdCard = CardApis.addCardToList(card.getName(), createdList.getId()).body().as(Card.class);

        Response deleteCardResponse = CardApis.deleteCard(createdCard.getId());
        assertThat(deleteCardResponse.statusCode(), equalTo(200));
    }
}
