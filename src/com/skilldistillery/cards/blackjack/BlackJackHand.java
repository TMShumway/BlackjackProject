package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Hand;

interface BlackJackHand {

	boolean isBlackJack(Hand hand);
	boolean isTwentyOne();
	boolean isBust();
	boolean isPush(Hand hand);
	
}
