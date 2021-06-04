package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

	List<Card> deck;
	
	public Deck() {
		deck = fillDeck();
	}
	
	private List<Card> fillDeck() {
		List<Card> deckTemp = new ArrayList<>(52);
		
	    for(Suit suit : Suit.values()) {
	        for(Rank rank : Rank.values()) {
	          deckTemp.add(new Card(suit, rank));
	        }
	      }
		return deckTemp;
	}
	
	public int checkDeckSize() {
		return deck.size();
	}
	
	public Card dealCard() {
		int random = (int)(Math.random() * checkDeckSize());
		return deck.get(random);
	}

	public void shuffle() {
		Collections.shuffle(deck);
	}
	
}
