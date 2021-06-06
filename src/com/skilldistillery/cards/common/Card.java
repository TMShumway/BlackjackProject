package com.skilldistillery.cards.common;

public class Card {

	private Suit suit;
	private Rank rank;
	private String cardTemplate;

	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
		this.cardTemplate = createCardTemplate(suit, rank);
	}

	public String toString() {
		return rank + " of " + suit;
	}

	public int getValue() {
		return this.rank.getValue();
	}
	
	public char getRankSymbol() {
		return this.rank.getRankSymbol();
	}

	public String getCardTemplate() {
		return cardTemplate;
	}

	private String createCardTemplate(Suit suit, Rank rank) {
		StringBuilder template = new StringBuilder();
		
		String rankName = rank.name();
		int rankValue = rank.getValue();
		char suitSymbol = suit.getSuitSymbol(); 
		char rankSymbol = rank.getRankSymbol();

		template.append(" --------\n");
		if (rankValue < 10) {
			template.append(String.format("|%d       |\n", rankValue));
		} else {
			if (rankName == "KING" || rankName == "QUEEN" || rankName == "JACK" || rankName == "ACE") {
				template.append(String.format("|%c       |\n", rankSymbol));
			} else {
				template.append(String.format("|%d      |\n", rankValue));
			}
		}
		template.append("|        |\n");
		template.append(String.format("|    %c   |\n", suitSymbol));
		template.append("|        |\n");
		if (rankValue < 10) {
			template.append(String.format("|       %d|\n", rankValue));
		} else {
			if (rankName == "KING" || rankName == "QUEEN" || rankName == "JACK" || rankName == "ACE") {
				template.append(String.format("|       %c|\n", rankSymbol));
			} else {
				template.append(String.format("|      %d|\n", rankValue));
			}
		}
		template.append(" --------");

		return template.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rank == null) ? 0 : rank.hashCode());
		result = prime * result + ((suit == null) ? 0 : suit.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (rank != other.rank)
			return false;
		if (suit != other.suit)
			return false;
		return true;
	}
}
