package de.pokergame;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PokerTable {
	private static final String OUTPUT_TXT = "C:\\pokerGame\\output.txt";
	private static final String OUTPUT_INTERN_TXT = "output.txt";
	public static final String FIRST_HAND_WINS = "First hand wins";
	public static final String SECOND_HAND_WINS = "Second hand wins";
	public static final String TIE = "Tie";

	public static void main(String[] args) throws IOException {
		if (checkIfFileExists()) {
			BufferedWriter wr = null;
			BufferedWriter wrIntern = null;
			try {
				File f = new File(OUTPUT_TXT);
				f.getParentFile().mkdirs();
				f.createNewFile();
			} catch (IOException e) {
			}
			BufferedReader br = generateFileReader();
			String line;
			try {
				wr = new BufferedWriter(new FileWriter(OUTPUT_TXT));
				wrIntern = new BufferedWriter(new FileWriter(OUTPUT_INTERN_TXT));
				while ((line = br.readLine()) != null) {
					String result = pokerHands(line);
					wr.write(result);
					wrIntern.write(result);
					String newline = System.getProperty("line.separator");
					wr.write(newline);
					wrIntern.write(newline);
				}
				br.close();
				wr.close();
				wrIntern.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			System.out.println("File doesnt exists.");
		}
	}

	private static BufferedReader generateFileReader()
			throws FileNotFoundException {
		FileReader fileReader = new FileReader("input.txt");
		BufferedReader br = new BufferedReader(fileReader);
		return br;
	}

	public static String pokerHands(String cardsInput) {
		cardsInput = cardsInput.trim();
		String[] cards = cardsInput.split(" ");
		StringBuilder firstHandString = new StringBuilder(), secondHandString = new StringBuilder();

		for (int i = 0; i < 5; i++) {
			firstHandString.append(cards[i]);
			if (i != 4)
				firstHandString.append(" ");
		}
		for (int i = 5; i < 10; i++) {
			secondHandString.append(cards[i]);
			if (i != 9)
				secondHandString.append(" ");
		}

		Hand firstHand = new Hand();
		firstHand.drawFromDeck(firstHandString.toString());
		Hand secondHand = new Hand();
		secondHand.drawFromDeck(secondHandString.toString());

		int firstRank = firstHand.getRank();
		int secondRank = secondHand.getRank();

		if (firstRank > secondRank) {
			return FIRST_HAND_WINS;
		} else if (secondRank > firstRank) {
			return SECOND_HAND_WINS;
		} else {
			System.out.println(TIE);
			return TIE;
		}
	}

	private static boolean checkIfFileExists() {
		File file = new File("input.txt");

		boolean fileExists = file.exists();
		System.out.println(fileExists);
		return fileExists;
	}

}