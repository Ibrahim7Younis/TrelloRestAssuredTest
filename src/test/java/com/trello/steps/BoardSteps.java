package com.trello.steps;

import com.github.javafaker.Faker;
import com.trello.models.Board;

public class BoardSteps {

    public static Board generateBoard(){
        Faker faker = new Faker();
        String boardName = faker.book().genre();
        return new Board(boardName);
    }
}
