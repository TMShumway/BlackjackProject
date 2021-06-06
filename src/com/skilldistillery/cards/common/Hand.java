package com.skilldistillery.cards.common;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {

	private List<Card> cards = new ArrayList<>();

	public Hand() {

	}

	public boolean hasAceAndATen() {
		boolean hasAce = false;
		boolean hasATen = false;

		for (Card card : cards) {
			char symbol = card.getRankSymbol();

			if (symbol == 'A') {
				hasAce = true;
			}
			if (symbol == 'X' || symbol == 'J' || symbol == 'Q' || symbol == 'K') {
				hasATen = true;
			}
		}

		return hasAce && hasATen ? true : false;
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public int getHandValue() {
		int value = 0;
		for (Card card : cards) {
			value += card.getValue();
		}
		return value;
	}

	public void fold() {
		cards.clear();
	}

	public void displayHand() {
		if (cards.size() == 0) {
			System.out.println("No cards in hand.");
		} else {
			for (Card card : cards) {
				System.out.print(card + "  ");
			}
		}
	}

	public List<Card> getCards() {
		return cards;
	}

}
