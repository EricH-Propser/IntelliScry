package com.martyworm.cards;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardManagerTests {

    CardManager cardManager = new CardManager(null, null);

    @Test
    public void shouldShowNoneSelectedWhenNoneSelected(){

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(null, null, 1, 1));
        cards.add(new Card(null, null, 2, 1));
        assertTrue(cardManager.noneSelected(cards));
    }

    @Test
    public void shouldSelectedWhenSelected(){

        List<Card> cards = new ArrayList<>();
        cards.add(new Card(null, null, 1, 1));
        Card card2 = new Card(null, null, 2, 1);
        card2.setSelected(true);
        cards.add(card2);
        assertFalse(cardManager.noneSelected(cards));
    }


}
