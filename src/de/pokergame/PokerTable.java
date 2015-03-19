package de.pokergame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class PokerTable {
	private static final String OUTPUT_TXT = "C:\\pokerGame\\output.txt";
	private static final String OUTPUT_INTERN_TXT = "output.txt";
	public static final String FIRST_HAND_WINS = "First hand wins";
	public static final String SECOND_HAND_WINS = "Second hand wins";
	public static final String TIE = "Tie";
	private static ArrayList<String> results;

	public static void main(String[] args) throws IOException {
		if (checkIfFileExists()) {
			results = new ArrayList<String>();
			BufferedReader br = generateFileReader();
			String line;
			try {
				while ((line = br.readLine()) != null) {
					String result = pokerHands(line);
					results.add(result);
				}
				br.close();
			} catch (IOException e1) {
			}
		}
		createFolderFile();
		createOutput(OUTPUT_TXT);
		createOutput(OUTPUT_INTERN_TXT);
	}

	/**
	 * Compare pairs of poker hands and indicate which, if either, has a higher
	 * rank.
	 * 
	 * @param cardsInput
	 *            given cards from deck e.g. 5H 5C 6S 7S KD 2C 3S 8S 8D TD
	 * @return
	 */
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
			return TIE;
		}
	}

	private static void createFolderFile() {
		File file = new File(OUTPUT_TXT);
		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
				file.createNewFile();
			} catch (IOException e) {
			}
		}
	}

	private static void createOutput(String outputPath) throws IOException {
		OutputStream outputStream = new FileOutputStream(outputPath);
		Writer writer = new OutputStreamWriter(outputStream);
		String newline = System.getProperty("line.separator");
		for (String result : results) {
			writer.write(result);
			writer.write(newline);
		}
		writer.close();
	}

	private static BufferedReader generateFileReader()
			throws FileNotFoundException {
		FileReader fileReader = new FileReader("input.txt");
		BufferedReader br = new BufferedReader(fileReader);
		return br;
	}

	private static boolean checkIfFileExists() {
		File file = new File("input.txt");
		boolean fileExists = file.exists();
		return fileExists;
	}

}