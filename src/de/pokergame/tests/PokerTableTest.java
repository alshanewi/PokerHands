package de.pokergame.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import de.pokergame.PokerTable;

public class PokerTableTest {

	private String cardsInputLine1 = "5H 5C 6S 7S KD 2C 3S 8S 8D TD";
	private String cardsInputLine2 = "5D 8C 9S JS AC 2C 5C 7D 8S QH";
	private String cardsInputLine3 = "2D 9C AS AH AC 3D 6D 7D TD QD";
	private String cardsInputLine4 = "4D 6S 9H QH QC 3D 6D 7H QD QS";
	private String cardsInputLine5 = "2H 2D 4C 4D 4S 3C 3D 3S 9S 9D";

	@Test
	public void testPairOfEightWins() {
		assertTrue(PokerTable.SECOND_HAND_WINS.equals(PokerTable
				.pokerHands(cardsInputLine1)));
	}

	@Test
	public void testHighestCardAceWins() {
		assertTrue(PokerTable.FIRST_HAND_WINS.equals(PokerTable
				.pokerHands(cardsInputLine2)));
	}

	@Test
	public void testFlushWithDiamondsWins() {
		assertTrue(PokerTable.SECOND_HAND_WINS.equals(PokerTable
				.pokerHands(cardsInputLine3)));
	}

	@Test
	public void testPairOfQueensHighestCardNineWins() {
		assertTrue(PokerTable.FIRST_HAND_WINS.equals(PokerTable
				.pokerHands(cardsInputLine4)));
	}

	@Test
	public void testFullHouseWithThreeFoursWins() {
		assertTrue(PokerTable.FIRST_HAND_WINS.equals(PokerTable
				.pokerHands(cardsInputLine5)));
	}
}
