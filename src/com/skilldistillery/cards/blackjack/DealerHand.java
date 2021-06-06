package com.skilldistillery.cards.blackjack;

import java.util.List;

import com.skilldistillery.cards.common.Card;
import com.skilldistillery.cards.common.Hand;

public class DealerHand extends Hand implements BlackJackHand{
	
	public DealerHand() {
		super();
	}

	@Override
	public boolean isBlackJack(Hand playerHand) {
		if(super.hasAceAndATen() && !playerHand.hasAceAndATen()) {
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
	public boolean isPush(Hand playerHand) {
		if(this.isTwentyOne() && ((PlayerHand)playerHand).isTwentyOne()) {
			return true;
		} else if(this.getHandValue() == playerHand.getHandValue()) {
			return true;
		} else {
			return false;			
		}
	}

	public void displayHiddenHand() {
		List<Card> superCards = super.getCards();
		
		if (superCards.size() == 0) {
			System.out.println("No cards in hand.");
		} else {
			for(int i = 0; i < superCards.size(); i++) {
				if(i == 0) {
					System.out.print("[Card Hidden]  ");					
				} else {
					System.out.print(superCards.get(i) + "  ");										
				}
			}
		}
	}
	
	public int getHiddenHandValue() {
		List<Card> superCards = super.getCards();
		int value = 0;
		
		if(superCards.size() == 0 || superCards == null) {
			return 0;
		}
		
		for(int i = 1; i < superCards.size(); i++) {
			value += superCards.get(i).getValue();			
		}
		
		return value;
	}
}
