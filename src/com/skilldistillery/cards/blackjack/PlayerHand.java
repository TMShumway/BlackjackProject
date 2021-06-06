package com.skilldistillery.cards.blackjack;

import com.skilldistillery.cards.common.Hand;

public class PlayerHand extends Hand implements BlackJackHand{

	public PlayerHand() {
		super();
	}
	
	
	public boolean isBlackJackLimited() {
		if(super.hasAceAndATen()) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isBlackJack(Hand dealerHand) {
		if(super.hasAceAndATen() && !dealerHand.hasAceAndATen()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isTwentyOne() {
		if(super.getHandValue() == 21) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isBust() {
		if(super.getHandValue() > 21) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isPush(Hand dealerHand) {
		if(this.isTwentyOne() && ((DealerHand)dealerHand).isTwentyOne()) {
			return true;
		} else if(this.getHandValue() == dealerHand.getHandValue()) {
			return true;
		} else {
			return false;			
		}
	}
	
}
