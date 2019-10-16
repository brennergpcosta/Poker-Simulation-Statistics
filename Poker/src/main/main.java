package main;

import java.util.ArrayList;
import java.util.Random;

public class main {

	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static ArrayList<Card> hand = new ArrayList<Card>();
	private static ArrayList<Card> table = new ArrayList<Card>();

	public static void main(String[] args) {

		deck = newDeck();
//		printDeck(deck);
		hand = newHand(deck);
		table = newTable(deck);
//		printDeck(deck);
		printDeck(hand);
		printDeck(table);
		System.out.println("Single pair: "+ checkPair());
		System.out.println("Double pairs: "+ checkDoublePair());
	}
	

	public static void line() {
		String line = "-";
		for (int i = 0; i < 50; i++) {
			line += "-";
		}
		System.out.println(line);
	}

	public static void printDeck(ArrayList<Card> cards) {
		for (int i = 0; i < cards.size(); i++) {
			System.out.println(
					(cards.get(i).getValue()) + " " + cards.get(i).getSuit() + " " + cards.get(i).isUsed());
		}
		line();
	}

	public static ArrayList<Card> newDeck() {
		String suit = "Hearts";
		for (int i = 0; i < 4; i++) {
			if (i == 1) {
				suit = "Spades";
			} else if (i == 2) {
				suit = "Clubs";
			} else if (i == 3) {
				suit = "Diamonds";
			}
			for (int j = 0; j < 13; j++) {
				deck.add(new Card(suit, j+1));
			}
		}
		return deck;
	}

	public static ArrayList<Card> newHand(ArrayList<Card> deck) {

//		numberOfHands += numberOfHands;

		Random random = new Random();
		while (true) {
			int i = random.nextInt(52);
			if (deck.get(i).isUsed() == false) {
				hand.add(deck.get(i));
				deck.get(i).setUsed(true);
			}
			if (hand.size() == 2) {
				break;
			}
		}
		return hand;
	}

	public static ArrayList<Card> newTable(ArrayList<Card> deck) {
		Random random = new Random();
		while (true) {
			int i = random.nextInt(52);
			if (deck.get(i).isUsed() == false) {
				table.add(deck.get(i));
				deck.get(i).setUsed(true);
			}
			if (table.size() == 5) {
				break;
			}
		}
		return table;
	}

	public static boolean checkPair() {

		// Returns true if the player have a pair on hand or one card on hand and the other on the table that have the same value.
		// It only check for ONE pair.
		if (hand.get(0).getValue() == hand.get(1).getValue()) {
			return true;
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < table.size(); j++) {
				if (hand.get(i).getValue() == table.get(j).getValue()) {
					return true;
				}
			}
		}
		return false;
	}

	public static boolean checkDoublePair() {

		if (hand.get(0).getValue() == hand.get(1).getValue()) {
			return false;
		}
		
		int pairNo = 0;
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < table.size(); j++) {
				if (hand.get(i).getValue() == table.get(j).getValue()) {
					pairNo++;
					break;
				}
			}
		}
		return pairNo == 2 ? true : false;

	}
	
	public static boolean checkThreeOfAKind() {
		ArrayList<Card> equalCardsOnTable = new ArrayList<Card>();
		
		//Check if table have 3 cards of the same value.
		for (int i = 0; i < table.size(); i++) {
			for (int j = 0; j < table.size(); j++) {
				if(i != j) {
					if(table.get(i).getValue() == table.get(j).getValue()) {
						equalCardsOnTable.add(table.get(i));							
						equalCardsOnTable.add(table.get(j));
						if(equalCardsOnTable.size() == 3) {
							return false;
						}
					}
				}
			}
		}
		
		//If player have same value cards on hands AND one more card of the same value on the table.
		if (hand.get(0).getValue() == hand.get(1).getValue() && equalCardsOnTable.size() == 0) {
			for (int i = 0; i < table.size(); i++) {
				if(table.get(i).getValue() == hand.get(0).getValue()) {
					return true;
				}
			}
		}else if(hand.get(0).getValue() != hand.get(1).getValue()) {
			for (int i = 0; i < table.size(); i++) {
				for (int j = 0; j < hand.size(); j++) {
					if(hand.get(j).getValue() == table.get(i).getValue() && hand.get(j).getValue() == equalCardsOnTable.get(0).getValue()) {
						return true;
					}
				}
			}
		}
		return false;
		
	}

}
