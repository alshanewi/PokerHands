package de.pokergame;
public class Card implements Comparable<Card> {
	private int numericValue;
	private char suit;

	/**
	 * Returns numeric value of this card. An integer number in range 0-12.
	 */
	public int getNumericValue() {
		return numericValue;
	}

	public char getSuit() {
		return suit;
	}

	public Card(String card) {
		int rank = rankCard(card);
		int lengthSuits = Suits.values().length;
		numericValue = rank / lengthSuits;
		suit = Suits.values()[rank % lengthSuits].getChar();
	}

	private int rankCard(String card) {
		int lengthSuits = Suits.values().length;
		char value = card.charAt(0);
		char suit = card.charAt(1);
		int i = -1;
		int j = -1;

		for (Ranks rankValue : Ranks.values()) {
			i++;
			if (rankValue.getChar() == value) {
				for (Suits suitValue : Suits.values()) {
					j++;
					if (suitValue.getChar() == suit) {
						int k = i * lengthSuits + j;
						return k;
					}
				}
			}
		}
		return -1;
	}

	public int compareTo(Card otherCard) {
		if (this.numericValue > otherCard.getNumericValue())
			return 1;
		else if (this.numericValue < otherCard.getNumericValue())
			return -1;
		else
			return 0;
	}

	@Override
	public boolean equals(Object other) {
		Card otherCard = (Card) other;
		return this.numericValue == otherCard.getNumericValue();
	}
}