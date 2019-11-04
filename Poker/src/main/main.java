package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class main {

	private static ArrayList<Card> deck = new ArrayList<Card>();
	private static ArrayList<Card> hand = new ArrayList<Card>();
	private static ArrayList<Card> table = new ArrayList<Card>();

	public static void main(String[] args) {

		int pair = 0;
		int doublePair = 0;
		int threeOfAKind = 0;
		int fourOfAKind = 0;
		int flush = 0;
		int straight = 0;
		int straightFlush = 0;
		
//		Card hand1 = new Card("test", 5);
//		Card hand2 = new Card("test", 1);
//
//		Card table1 = new Card("test", 4);
//		Card table2 = new Card("test", 2);
//		Card table3 = new Card("test", 3);
//		Card table4 = new Card("test", 9);
//		Card table5 = new Card("test", 7);

		int count = 0;
		boolean aux = true;
		while (count < 1000000) {
			
			deck = newDeck();
			hand = newHand(deck);
			table = newTable(deck);
			
			if(checkPair()) {
				pair++;
			}
			if(checkDoublePair()) {
				doublePair++;
			}
			if(checkThreeOfAKind()) {
				threeOfAKind++;
			}
			if(checkFourOfAKind()) {
				fourOfAKind++;
			}
			if(checkFlush()) {
				flush++;
			}
			if(checkStraight()) {
				straight++;
			}
			if(checkStraightFlush()) {
				straightFlush++;
			}
		
//			table.add(table1);
//			table.add(table2);
//			table.add(table3);
//			table.add(table4);
//			table.add(table5);
//
//			hand.add(hand1);
//			hand.add(hand2);

//			printDeck(hand);
//			printDeck(table);
//			System.out.println("Equal Cards on Table");
//			printDeck(equalCardsOnTable());
//			System.out.println("Single pair: " + checkPair());
//			System.out.println("Double pairs: " + checkDoublePair());
//			System.out.println("Three of a Kind: " + checkThreeOfAKind());
//			System.out.println("Full House: " + checkFullHouse());
//			System.out.println("Four of a Kind: " + checkFourOfAKind());
//			System.out.println("Flush: " + checkFlush());
//			System.out.println("Straight: " + checkStraight());
//			System.out.println("Royal Stright: " + checkStraightFlush());
//			line();
			
			count++;
			System.out.println(count);
			if(count%10000 == 0) {
				line();
				System.out.println(count);
			}
			
			deck.clear();
			hand.clear();
			table.clear();
		}
		line();
		System.out.println("Total Count: " + count);
		line();
		System.out.println("Pairs: " + pair);
		System.out.println("Double Pairs: "+doublePair);
		System.out.println("Three of a Kind: " + threeOfAKind);
		System.out.println("Four of a Kind: " + fourOfAKind);
		System.out.println("Flush: " + flush);
		System.out.println("Straight : " + straight);
		System.out.println("Straight Flush: " + straightFlush);

//		deck = newDeck();
//		hand = newHand(deck);
//		table = newTable(deck);
//		printDeck(hand);
//		printDeck(table);
//		System.out.println("Equal Cards on Table");
//		printDeck(equalCardsOnTable());
//		System.out.println("Single pair: " + checkPair());
//		System.out.println("Double pairs: " + checkDoublePair());
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
			System.out.println((cards.get(i).getValue()) + " " + cards.get(i).getSuit() + " " + cards.get(i).isUsed());
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
				deck.add(new Card(suit, j + 1));
			}
		}
		return deck;
	}

	public static ArrayList<Card> newHand(ArrayList<Card> deck) {
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

	public static boolean checkPairOnHand() {
		if (hand.get(0).getValue() == hand.get(1).getValue()) {
			return true;
		}
		return false;
	}

	public static boolean checkPair() {

		// Returns true if the player have a pair on hand or one card on hand and the
		// other on the table that have the same value.
		// It only check for ONE pair.
		if (checkPairOnHand()) {
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

		if (checkPairOnHand()) {
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

	// Return an array of cards that have the same value (up to 2 different values).
	// It has to have more then one card of the same value to enter the array.
	public static ArrayList<Card> equalCardsOnTable() {
		ArrayList<Card> equalCardsOnTable = new ArrayList<Card>();
		int aux = 0;
		boolean referenceCounted = true;

		for (int i = 0; i < table.size(); i++) {
			if (equalCardsOnTable.size() == 0 || (table.get(i).getValue() != aux
					&& table.get(i).getValue() != equalCardsOnTable.get(0).getValue())) {
				referenceCounted = true;
				for (int j = 0; j < table.size(); j++) {
					if (i != j && table.get(i).getValue() == table.get(j).getValue()) {
						if (referenceCounted) {
							aux = table.get(i).getValue();
							equalCardsOnTable.add(table.get(i));
							referenceCounted = false;
						}
						equalCardsOnTable.add(table.get(j));
					}
				}
			}
		}
		return equalCardsOnTable;
	}

	public static ArrayList<Card> firstSameValue() {
		ArrayList<Card> equalCardsOnTable = equalCardsOnTable();
		ArrayList<Card> firstSameValue = new ArrayList<>();

		if (equalCardsOnTable.size() >= 2) {
			for (int i = 0; i < equalCardsOnTable.size(); i++) {
				if (equalCardsOnTable.get(i).getValue() == equalCardsOnTable.get(0).getValue()) {
					firstSameValue.add(equalCardsOnTable.get(i));
				}
			}
		} else {
			firstSameValue.add(new Card("", -1));
		}
		return firstSameValue;
	}

	public static ArrayList<Card> secondSameValue() {
		ArrayList<Card> equalCardsOnTable = equalCardsOnTable();
		ArrayList<Card> secondSameValue = new ArrayList<>();

		if (equalCardsOnTable.size() >= 4 && equalCardsOnTable.get(0).getValue() != equalCardsOnTable
				.get(equalCardsOnTable.size() - 1).getValue()) {
			for (int i = 0; i < equalCardsOnTable.size(); i++) {
				if (equalCardsOnTable.get(i).getValue() != equalCardsOnTable.get(0).getValue()) {
					secondSameValue.add(equalCardsOnTable.get(i));
				}
			}
		} else {
			secondSameValue.add(new Card("", -1));
		}
		return secondSameValue;
	}

	public static boolean checkThreeOfAKind() {
		ArrayList<Card> equalCardsOnTable = equalCardsOnTable();
		ArrayList<Card> sameValue = new ArrayList<>();
		ArrayList<Card> sameValue2 = new ArrayList<>();

		if (checkPairOnHand()) {
			if (equalCardsOnTable.size() > 0) {
				for (int i = 0; i < table.size(); i++) {
					for (int j = 0; j < equalCardsOnTable.size(); j++) {
						if (table.get(i).getValue() == hand.get(0).getValue()
								&& hand.get(0).getValue() == equalCardsOnTable.get(j).getValue()) {
							return true;
						}
					}
				}
			} else {
				for (int i = 0; i < table.size(); i++) {
					if (table.get(i).getValue() == hand.get(0).getValue()) {
						return true;
					}
				}
			}
		} else {
			if (equalCardsOnTable.size() >= 2) {
				if (equalCardsOnTable.get(0).getValue() != equalCardsOnTable.get(equalCardsOnTable.size() - 1)
						.getValue()) {
					for (int i = 0; i < equalCardsOnTable.size(); i++) {
						if (equalCardsOnTable.get(i).getValue() == equalCardsOnTable.get(0).getValue()) {
							sameValue.add(equalCardsOnTable.get(i));
						} else {
							sameValue2.add(equalCardsOnTable.get(i));
						}
					}
				}
				for (int i = 0; i < hand.size(); i++) {
					if (firstSameValue().size() >= 2 && hand.get(i).getValue() == firstSameValue().get(0).getValue()) {
						return true;
					}
					if (secondSameValue().size() >= 2
							&& hand.get(i).getValue() == secondSameValue().get(0).getValue()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean checkFullHouse() {
		if (checkDoublePair()) {
			boolean hand1 = false;
			boolean hand2 = false;

			for (int j = 0; j < table.size(); j++) {
				if (hand.get(0).getValue() == table.get(j).getValue()) {
					hand1 = true;
				}
			}
			for (int j = 0; j < table.size(); j++) {
				if (hand.get(1).getValue() == table.get(j).getValue()) {
					hand2 = true;
				}
			}

			if ((hand.get(0).getValue() == firstSameValue().get(0).getValue() && hand1)
					|| hand.get(1).getValue() == firstSameValue().get(0).getValue() && hand2) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkFourOfAKind() {
		ArrayList<Card> equalCardsOnTable = equalCardsOnTable();
		int count = 0;

		if (checkPairOnHand()) {
			for (int i = 0; i < equalCardsOnTable.size(); i++) {
				if (hand.get(0).getValue() == equalCardsOnTable.get(i).getValue()) {
					count++;
					if (count == 2) {
						return true;
					}
				}
			}
		} else {
			for (int j = 0; j < hand.size(); j++) {
				for (int i = 0; i < equalCardsOnTable.size(); i++) {
					if (hand.get(j).getValue() == equalCardsOnTable.get(i).getValue()) {
						count++;
						if (count == 3) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}

	public static boolean checkFlush() {

		ArrayList<Card> suitCount = new ArrayList<Card>();

		if (hand.get(0).getSuit().equals(hand.get(1).getSuit())) {
			suitCount.add(hand.get(0));
			suitCount.add(hand.get(1));
			for (Card table : table) {
				if (table.getSuit().equals(hand.get(0).getSuit())) {
					suitCount.add(table);
					if (suitCount.size() == 5) {
						return true;
					}
				}
			}
		} else {
			boolean firstTime = true;
			boolean countHand = true;
			for (Card hand : hand) {
				if (!firstTime) {
					suitCount.removeAll(suitCount);
				}
				for (Card table : table) {
					if (hand.getSuit().contentEquals(table.getSuit())) {
						if (countHand) {
							suitCount.add(hand);
							countHand = false;
						}
						suitCount.add(table);
						if (suitCount.size() == 5) {
							return true;
						}
					}
				}
				if (firstTime) {
					firstTime = false;
				}
			}
		}
		return false;
	}

	public static boolean checkStraight() {

		ArrayList<Card> straightCount = new ArrayList<Card>();
		ArrayList<Card> handPlustable = new ArrayList<Card>();
		handPlustable.addAll(hand);
		handPlustable.addAll(table);
		
		Collections.sort(handPlustable, new Comparator<Card>() {
			public int compare(Card a, Card b) {
				return a.getValue() - b.getValue();
			}
		});
		boolean straight = false;
		boolean checkHandCardUse = false;
		Card aux = handPlustable.get(handPlustable.size()-1);

		boolean includedInicialCard = false;
		boolean changeInicialCard = false;
		
		if(aux.getValue() > 4) {
			int changeIncialCardIndex = 0;
			while (!straight) {
				changeIncialCardIndex++;
				if(changeInicialCard) {
					aux = handPlustable.get(handPlustable.size()-changeIncialCardIndex);
				}
				changeInicialCard = true;
				if(changeIncialCardIndex > 2) {
					straight = true;					
				}
				for (Card card : handPlustable) {
					if (card.getValue() == aux.getValue() - 1 || (card.getValue() == 14 && aux.getValue() == 1)) {
						if (!includedInicialCard) {
							straightCount.add(aux);
							includedInicialCard = true;
						}
						straightCount.add(card);
						aux = card;
						if (aux.equals(hand.get(0)) || aux.equals(hand.get(1))) {
							checkHandCardUse = true;
						}
						for (int i = 0; i < 2; i++) {
							if(card.getValue() == hand.get(i).getValue() && !checkHandCardUse) {
								int index = straightCount.indexOf(card);
								straightCount.remove(index);
								straightCount.add(index, hand.get(i));
								checkHandCardUse = true;
							}
						}
						straight = false;
						if (straightCount.size() >= 5 && checkHandCardUse) {
							return true;
						}
						changeInicialCard = false;
						break;
					}else {
						changeInicialCard = true;
					}
					
				}
			}
		}
		
		return false;
	}

	public static boolean checkStraightFlush() {
		ArrayList<Card> straightCount = new ArrayList<Card>();
		ArrayList<Card> handPlustable = new ArrayList<Card>();
		handPlustable.addAll(hand);
		handPlustable.addAll(table);
		
		Collections.sort(handPlustable, new Comparator<Card>() {
			public int compare(Card a, Card b) {
				return a.getValue() - b.getValue();
			}
		});
		boolean straight = false;
		boolean checkHandCardUse = false;
		Card aux = handPlustable.get(handPlustable.size()-1);

		boolean includedInicialCard = false;
		boolean changeInicialCard = false;
		
		if(aux.getValue() > 4) {
			int changeIncialCardIndex = 0;
			while (!straight) {
				changeIncialCardIndex++;
				if(changeInicialCard) {
					aux = handPlustable.get(handPlustable.size()-changeIncialCardIndex);
				}
				changeInicialCard = true;
				if(changeIncialCardIndex > 2) {
					straight = true;					
				}
				for (Card card : handPlustable) {
					if ((card.getValue() == aux.getValue() - 1 || (card.getValue() == 14 && aux.getValue() == 1)) && card.getSuit() == aux.getSuit()) {
						if (!includedInicialCard) {
							straightCount.add(aux);
							includedInicialCard = true;
						}
						straightCount.add(card);
						aux = card;
						if (aux.equals(hand.get(0)) || aux.equals(hand.get(1))) {
							checkHandCardUse = true;
						}
						for (int i = 0; i < 2; i++) {
							if(card.getValue() == hand.get(i).getValue() && !checkHandCardUse) {
								int index = straightCount.indexOf(card);
								straightCount.remove(index);
								straightCount.add(index, hand.get(i));
								checkHandCardUse = true;
							}
						}
						straight = false;
						if (straightCount.size() >= 5 && checkHandCardUse) {
							return true;
						}
						changeInicialCard = false;
						break;
					}else {
						changeInicialCard = true;
					}
					
				}
			}
		}
		
		return false;
	}
	
}
