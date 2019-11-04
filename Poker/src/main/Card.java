package main;

import java.util.Comparator;

public class Card {

	private String suit;
	private int value;
	private boolean used;

	public Card(String suit, int value) {
		super();
		this.suit = suit;
		this.value = value;
		this.used = false;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public String getSuit() {
		return suit;
	}

	public int getValue() {
		return value;
	}

}