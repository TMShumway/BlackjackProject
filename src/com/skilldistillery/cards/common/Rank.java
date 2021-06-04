package com.skilldistillery.cards.common;

public enum Rank {

	TWO(2, '*'),
	THREE(3, '*'),
	FOUR(4, '*'),
	FIVE(5, '*'),
	SIX(6, '*'),
	SEVEN(7, '*'),
	EIGHT(8, '*'),
	NINE(9, '*'),
	TEN(10, '*'),
	JACK(10, 'J'),
	QUEEN(10, 'Q'),
	KING(10, 'K'),
	ACE(11, 'A');
	
	private int value;
	private char rankSymbol;
	
	Rank(int v, char c) {
		value = v;
		rankSymbol = c;
	}
	
    public int getValue() {
        return this.value;
      }

	public char getRankSymbol() {
		return rankSymbol;
	}
	
}
