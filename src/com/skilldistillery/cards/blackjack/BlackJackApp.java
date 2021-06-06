/*  Game rules follow a subset of the standard from:
 *  from https://www.bettingexpert.com/casino/blackjack/rules */

package com.skilldistillery.cards.blackjack;

import java.util.Scanner;

import com.skilldistillery.cards.common.Deck;

public class BlackJackApp {

	Deck deck = new Deck();
	DealerHand dealerHand = new DealerHand();
	PlayerHand playerHand = new PlayerHand();
	boolean isDealerStand;

	public static void main(String[] args) {
		BlackJackApp app = new BlackJackApp();

		app.launch();

	}

	private void launch() {
		printWelcomeHeader();

		startGame();
	}

	private void startGame() {
		Scanner kb = new Scanner(System.in);
		deck.shuffle();
		dealFirstTwoCards();
		
		int roundCounter = 1;

		boolean keepGoing = checkForWinBustOrPush();

		while(!keepGoing) {
			printHandsPreWin();
			playersTurn(roundCounter, kb);
			keepGoing = checkForWinBustOrPush();
			if (keepGoing) {
				System.exit(0);
				;
			}
			dealersTurn();
			keepGoing = checkForWinBustOrPush();
			roundCounter++;
		} 

	}

	private void dealersTurn() {
		boolean keepGoing = true;
		int userChoice = dealerHitOrStandLogic();

		do {
			if (userChoice <= 16) {
				System.out.println("\n-----------------------------------------------------------------------");
				System.out.print("\nDealer's turn. \n");
				userChoice = dealerHitOrStandLogic();
				switch (userChoice) {
				case 1:
					System.out.println("\nDealer Hits.");
					dealerHand.addCard(deck.dealCard());
					printHandsPreWin();
					break;
				case 2:
					System.out.println("\nDealer stands.");
					isDealerStand = true;
					keepGoing = false;
					break;
				default:
					System.err.println("Input out of range.\n\n");
					break;
				}
			} else {
				break;
			}
		} while (keepGoing);

	}

	private int dealerHitOrStandLogic() {
		if (dealerHand.getHandValue() <= 16) {
			return 1;
		} else {
			return 2;
		}
	}

	private void playersTurn(int roundCounter, Scanner kb) {
		if (roundCounter == 1 && isBlackJack()) {
			System.out.println("\n-----------------------------------------------------------------------");

			printHandsWin();

			System.out.println("\n\nBlackjack on opening hand! Player Wins!");
			System.exit(0);
		}

		hitOrStand(kb);
	}

	private boolean isPush() {
		return (dealerHand.isPush(playerHand) && playerHand.isPush(dealerHand));
	}

	private boolean isBlackJack() {
		return playerHand.isBlackJack(dealerHand);
	}

	private boolean checkForWinBustOrPush() {
		if (!isDealerStand) {
			if (playerHand.isBust()) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\nPlayer busts. Dealer Wins!");
				return true;
			} else if (playerHand.isBlackJackLimited()) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\nBlackJack. Player Wins!");
				return true;
			}
		}

		if (isDealerStand) {
			if (dealerHand.isBust() && !playerHand.isBust()) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\nDealer busts. Player Wins!");
				return true;
			} else if (playerHand.isBust() && dealerHand.isBust()) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\nPlayer and dealer are bust. No winner.");
				return true;
			} else if (playerHand.isBust()) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\nPlayer busts. Dealer Wins!");
				return true;
			} else if (playerHand.getHandValue() > dealerHand.getHandValue()) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\nPlayer hand beats dealer hand! Player wins!");
				return true;
			} else if (playerHand.getHandValue() < dealerHand.getHandValue()) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\nDealer hand beats player hand! Dealer wins!");
				return true;
			}
			if (playerHand.isPush(dealerHand) && dealerHand.isPush(playerHand)) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\nGame is a push. No winner.");
				return true;
			}
			if (playerHand.isBlackJack(dealerHand) && dealerHand.isBlackJack(playerHand)) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\nBoth Dealer and player have 21. Game is a push. No winner.");
				return true;
			} else if (playerHand.isBlackJack(dealerHand) && !dealerHand.isBlackJack(playerHand)) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\n21! Player wins!");
				return true;
			} else if (!playerHand.isBlackJack(dealerHand) && dealerHand.isBlackJack(playerHand)) {
				System.out.println("\n-----------------------------------------------------------------------");
				printHandsWin();
				System.out.println("\n\n21! Dealer wins!");
				return true;
			} 
		}
		return false;

	}

	private void hitOrStand(Scanner kb) {
		boolean keepGoing = true;

		do {
			if (playerHand.getHandValue() < 21) {
				System.out.println("\n-----------------------------------------------------------------------");
				System.out.print("\nWould you like to hit[1] or stand[2]?: ");
				int userChoice = validateIntInput(kb);
				switch (userChoice) {
				case 1:
					playerHand.addCard(deck.dealCard());
					printHandsPreWin();
					break;
				case 2:
					System.out.println("\nPlayer has chosen to stand.");
					keepGoing = false;
					break;
				default:
					System.err.println("Input out of range.\n\n");
					break;
				}
			} else {
				break;
			}
		} while (keepGoing);

	}

//	private void stand() {
//		System.out.println("\nStand chosen.");
//		
//	}
//
//	private void hit(Scanner kb) {
//		boolean keepGoing = false;
//		
//		do {
//			playerHand.addCard(deck.dealCard());
//			printHandsPreWin();
//			hitOrStand(kb);
//		} while (keepGoing);
//	}

	private void printHandsPreWin() {
		System.out.println("\nDealer's Hand - Value: ? + " + dealerHand.getHiddenHandValue());
		dealerHand.displayHiddenHand();

		System.out.println("\n\nPlayer's Hand - Value: " + playerHand.getHandValue());
		playerHand.displayHand();
		System.out.println();
	}

	private void printHandsWin() {
		System.out.println("\nDealer's Hand - Value: " + dealerHand.getHandValue());
		dealerHand.displayHand();

		System.out.println("\n\nPlayer's Hand - Value: " + playerHand.getHandValue());
		playerHand.displayHand();
	}

	private void dealFirstTwoCards() {
		int cardsToDeal = 2;

		System.out.println("Dealing player and dealer cards...");

		for (int i = 0; i < cardsToDeal; i++) {
			playerHand.addCard(deck.dealCard());
			dealerHand.addCard(deck.dealCard());
		}

	}

	private void printWelcomeHeader() {
		System.out.println("****************************** Blackjack ******************************");
		System.out.println("-----------------------------------------------------------------------");

		System.out.println("                   Welcome to the table. Let's play!\n");

	}

	private int validateIntInput(Scanner kb) {
		boolean isInt = false;
		int number = 0;

		do {
			if (kb.hasNextInt()) {
				number = kb.nextInt();
				isInt = true;
			} else {
				System.err.println("\nError. Please enter a 1 or 2 only.\n");
				kb.next();
			}
		} while (!(isInt));

		return number;
	}

}