package de.pokergame.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import de.pokergame.Hand;

public class HandTest {
	private Hand hand;

	@Before
	public void executedBeforeEach() {
		hand = new Hand();
	}

	@Test
	public void testIsStraight() {
		String cardString = "2H 3D 4S 5C 6D";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isStraight());
	}

	@Test
	public void testIsStraightDiffernetSortingFromTheBeginning() {
		String cardString = "2H 3D 6S 5C 4D";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isStraight());
	}

	@Test
	public void testIsNotStraight() {
		String cardString = "2H 3D 4S 5C 5D";
		hand.drawFromDeck(cardString);
		assertFalse(hand.isStraight());
	}

	@Test
	public void testIsFlush() {
		String cardString = "2H 6H 3H 5H 6H";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isFlush());
	}

	@Test
	public void testIsStraightFlush() {
		String cardString = "2H 3H 4H 5H 6H";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isStraightFlush());
	}

	@Test
	public void testIsFullHouse() {
		String cardString = "9H 9H 9H 2H 2H";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isFullHouse());
	}

	@Test
	public void testIsNotFullHouse() {
		String cardString = "9H 9H 9H 3H 2H";
		hand.drawFromDeck(cardString);
		assertFalse(hand.isFullHouse());
	}

	@Test
	public void testIsFourOfKind() {
		String cardString = "9C 9H 9D 9S 2H";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isFourOfKind());
	}

	@Test
	public void testIsThreeOfKind() {
		String cardString = "9C 9H 9D 8S 8H";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isThreeOfKind());
	}

	@Test
	public void testIsTwoPairs() {
		String cardString = "9C 9H 8D 8S 7H";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isTwoPairs());
	}

	@Test
	public void testIsNotTwoPairs() {
		String cardString = "9C 9H 8D 8S 8H";
		hand.drawFromDeck(cardString);
		assertFalse(hand.isTwoPairs());
	}

	@Test
	public void testIsPair() {
		String cardString = "9C 9H 6D 5S 8H";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isPair());
	}
	
	@Test
	public void testIsPair1() {
		String cardString = "5H 5C 6S 7S KD";
		hand.drawFromDeck(cardString);
		assertTrue(hand.isPair());
	}

	@Test
	public void testIsNotPair() {
		String cardString = "9C 9H 6D 6S 8H";
		hand.drawFromDeck(cardString);
		assertFalse(hand.isPair());
	}
}
