package com.trello.steps;

import com.github.javafaker.Faker;
import com.trello.models.BoardList;

public class ListSteps {

    public static BoardList generateList(){
        Faker faker = new Faker();
        String listName = faker.book().title();
        return new BoardList(listName);
    }
}
