package com.skilldistillery.cards.common;

public enum Suit {
		
	HEARTS("Hearts", '\u2661'),
	SPADES("Spades", '\u2664'),
	CLUBS("Clubs", '\u2667'),
	DIAMONDS("Diamonds", '\u2662');
	
	private String name;
	private char suitSymbol;

	Suit(String s, char c){
		name = s;
		suitSymbol = c;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getName() {
		return this.name;
	}

	public char getSuitSymbol() {
		return suitSymbol;
	}
}
