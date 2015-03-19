import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.Math.pow;

import java.util.Arrays;

public class Hand {
	public final static int MAX_CARD = 5;

	public static final int PAIR = 300000;
	public static final int TWO_PAIRS = 600000;
	public static final int THREE_OF_A_KIND = 1200000;
	public static final int STRAIGHT = 2400000;
	public static final int FLUSH = 4800000;
	public static final int FULL_HOUSE = 9600000;
	public static final int FOUR_OF_KIND = 18200000;
	public static final int STRAIGHT_FLUSH = 36400000;

	Card[] cards;
	int rank = Integer.MIN_VALUE;

	public Hand(String handString) {
		cards = new Card[MAX_CARD];
		String[] cardsFromDeck = handString.split(" ");
		for (int i = 0; i < MAX_CARD; i++) {
			cards[i] = new Card(cardsFromDeck[i]);
		}
		Arrays.sort(cards);
	}

	/**
	 * Hand contains 5 cards with consecutive values.
	 */
	public boolean isStraight() {
		return (cards[4].getNumericValue() - cards[0].getNumericValue() == 4);
	}

	private int rankStraight() {
		return STRAIGHT + cards[4].getNumericValue();
	}

	/**
	 * Hand contains 5 cards of the same suit.
	 */
	public boolean isFlush() {
		for (int i = 1; i < MAX_CARD; i++) {
			if (cards[i].getSuit() != cards[i - 1].getSuit())
				return false;
		}
		return true;
	}

	private int rankFlush() {
		return FLUSH + rankHighCard();
	}

	private int rankHighCard() {
		return (int) (pow(cards[4].getNumericValue(), 5)
				+ pow(cards[3].getNumericValue(), 4)
				+ pow(cards[2].getNumericValue(), 3)
				+ pow(cards[1].getNumericValue(), 2) + pow(
					cards[0].getNumericValue(), 1));
	}

	/**
	 * Returns true if five cards are of the same suit with consecutive values.
	 */
	public boolean isStraightFlush() {
		return isFlush() && isStraight();
	}

	private int rankStraightFlush() {
		return STRAIGHT_FLUSH + cards[4].getNumericValue();
	}

	/**
	 * 3 cards of the same value, with the remaining 2 cards forming a pair.
	 */
	public boolean isFullHouse() {
		int card1 = cards[0].getNumericValue();
		int card2 = cards[1].getNumericValue();
		int card3 = cards[2].getNumericValue();
		int card4 = cards[3].getNumericValue();
		int card5 = cards[4].getNumericValue();

		return ((card1 == card2 && card2 != card3 && card3 == card5) || (card1 == card3
				&& card3 != card4 && card4 == card5));
	}

	private int rankFullHouse() {
		int card1 = cards[0].getNumericValue();
		int card3 = cards[2].getNumericValue();
		int card5 = cards[4].getNumericValue();

		int rank = FULL_HOUSE;
		if (card1 == card3) {
			rank += card1;
		} else if (card3 == card5) {
			rank += card3;
		}

		return rank;
	}

	/**
	 * 4 cards with the same value.
	 */
	public boolean isFourOfKind() {
		// Because of isFlush() && isStraight();
		return (cards[3].getNumericValue() == cards[0].getNumericValue() || cards[4]
				.getNumericValue() == cards[1].getNumericValue());
	}

	private int rankFourOfKind() {
		int rank = FOUR_OF_KIND;
		if (cards[3].getNumericValue() == cards[0].getNumericValue())
			rank += cards[3].getNumericValue();
		else if (cards[4].getNumericValue() == cards[1].getNumericValue())
			rank += cards[4].getNumericValue();
		return rank;
	}

	/**
	 * Three of the cards in the hand have the same value.
	 */
	public boolean isThreeOfKind() {
		int card1 = cards[0].getNumericValue();
		int card2 = cards[1].getNumericValue();
		int card3 = cards[2].getNumericValue();
		int card4 = cards[3].getNumericValue();
		int card5 = cards[4].getNumericValue();

		return (card1 == card3 || card2 == card4 || card3 == card5);
	}

	private int rankThreeOfKind() {
		int card1 = cards[0].getNumericValue();
		int card2 = cards[1].getNumericValue();
		int card3 = cards[2].getNumericValue();
		int card4 = cards[3].getNumericValue();
		int card5 = cards[4].getNumericValue();

		int rank = THREE_OF_A_KIND;
		if (card1 == card3)
			rank += card1;
		else if (card2 == card4)
			rank += card2;
		else if (card3 == card5)
			rank += card3;

		return rank;
	}

	/**
	 * The hand contains 2 different pairs.
	 */
	public boolean isTwoPairs() {
		int card1 = cards[0].getNumericValue();
		int card2 = cards[1].getNumericValue();
		int card3 = cards[2].getNumericValue();
		int card4 = cards[3].getNumericValue();
		int card5 = cards[4].getNumericValue();

		return ((card1 == card2 && card2 != card3 && card3 == card4 && card4 != card5) || (card1 != card2
				&& card2 == card3 && card3 != card4 && card4 == card5));
	}

	private int rankTwoPairs() {
		int card1 = cards[0].getNumericValue();
		int card2 = cards[1].getNumericValue();
		int card3 = cards[2].getNumericValue();
		int card4 = cards[3].getNumericValue();
		int card5 = cards[4].getNumericValue();

		int pair1Val = Integer.MIN_VALUE;
		int pair2Val = Integer.MIN_VALUE;
		int remainVal = Integer.MIN_VALUE;

		if (card1 == card2 && card2 != card3 && card3 == card4
				&& card4 != card5) {
			pair1Val = card1;
			pair2Val = card3;
			remainVal = card5;
		} else if (card1 != card2 && card2 == card3 && card3 != card4
				&& card4 == card5) {
			pair1Val = card2;
			pair2Val = card4;
			remainVal = card1;
		}

		return TWO_PAIRS
				+ (int) (pow(max(pair1Val, pair2Val), 3)
						+ pow(min(pair1Val, pair2Val), 2) + remainVal);
	}

	/**
	 * 2 of the 5 cards in the hand have the same value.
	 */
	public boolean isPair() {
		int card1 = cards[0].getNumericValue();
		int card2 = cards[1].getNumericValue();
		int card3 = cards[2].getNumericValue();
		int card4 = cards[3].getNumericValue();
		int card5 = cards[4].getNumericValue();

		return ((card1 == card2 && card2 != card3)
				|| (card2 == card3 && card2 != card1 && card3 != card4)
				|| (card3 == card4 && card3 != card2 && card4 != card5) || (card4 == card5 && card4 != card3));
	}

	private int rankPair() {
		int card1 = cards[0].getNumericValue();
		int card2 = cards[1].getNumericValue();
		int card3 = cards[2].getNumericValue();
		int card4 = cards[3].getNumericValue();
		int card5 = cards[4].getNumericValue();

		int rank = PAIR;

		if (card1 == card2 && card2 != card3) {
			rank += pow(card1, 4) + pow(card5, 3) + pow(card4, 2)
					+ pow(card3, 1);
		} else if (card2 == card3 && card2 != card1 && card3 != card4) {
			rank += pow(card2, 4) + pow(card5, 3) + pow(card4, 2)
					+ pow(card1, 1);
		} else if (card3 == card4 && card3 != card2 && card4 != card5) {
			rank += pow(card3, 4) + pow(card5, 3) + pow(card2, 2)
					+ pow(card1, 1);
		} else if (card4 == card5 && card4 != card3) {
			rank += pow(card4, 4) + pow(card3, 3) + pow(card2, 2)
					+ pow(card1, 1);
		}

		return rank;
	}

	/**
	 * Poker hands are ranked.
	 */
	public int getRank() {
		if (rank == Integer.MIN_VALUE) {
			if (isStraightFlush())
				rank = rankStraightFlush();
			else if (isFourOfKind())
				rank = rankFourOfKind();
			else if (isFullHouse())
				rank = rankFullHouse();
			else if (isFlush())
				rank = rankFlush();
			else if (isStraight())
				rank = rankStraight();
			else if (isThreeOfKind())
				rank = rankThreeOfKind();
			else if (isTwoPairs())
				rank = rankTwoPairs();
			else if (isPair())
				rank = rankPair();
			else
				rank = rankHighCard();
		}
		return rank;
	}
}
