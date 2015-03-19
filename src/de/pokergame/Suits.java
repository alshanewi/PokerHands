package de.pokergame;
public enum Suits {
	  CLUBS('C'),DIAMONDS('D'), HEARTS('H'),SPADES('S');
	
	private final char c;


	private Suits(final char c) {
		this.c = c;
	}


	public char getChar() {
		return c;
	}
}