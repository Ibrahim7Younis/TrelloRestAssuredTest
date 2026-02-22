package com.trello.steps;

import com.github.javafaker.Faker;
import com.trello.models.Card;

public class CardSteps {

    public static Card generateCard(){
        Faker faker = new Faker();
        String cardName = faker.book().author();
        return new Card(cardName);
    }
}
