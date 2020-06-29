package by.artempvn.les05.service;

public class CharArrayService {
	private static final int SPACE_CHAR = 32;
	private static final int FIRST_LATIN_CHAR = 65;
	private static final int LAST_LATIN_CHAR = 90;
	private static final int FIRST_CYRILLIC_CHAR = 1040;
	private static final int LAST_CYRILLIC_CHAR = 1103;
	private static final int LOWERCASE_LETTER_SHIFTING = 32;
	private static final int[] VOWEL_ARRAY = { 65, 69, 73, 79, 85, 89, 1040,
			1045, 1048, 1054, 1059, 1067, 1069, 1070, 1071 };

	public String replaceCharAtPosition(String word, int positionForReplace,
			char newChar) {
		// TODO validator
		String changedWord;
		char[] wordToCharArray = word.toCharArray();
		if (wordToCharArray.length > positionForReplace) {
			wordToCharArray[positionForReplace] = newChar;
			changedWord = String.valueOf(wordToCharArray);
		} else {
			changedWord = word;
		}
		return changedWord;
	}

	public String correctCharByPrevious(String word, char preMistakeChar,
			char mistakeChar, char correctChar) {
		// TODO validator
		String changedWord;
		char[] wordToCharArray = word.toCharArray();
		for (int i = 1; i < wordToCharArray.length; i++) {
			if (wordToCharArray[i] == mistakeChar
					&& wordToCharArray[i - 1] == preMistakeChar) {
				wordToCharArray[i] = correctChar;
			}
		}
		changedWord = String.valueOf(wordToCharArray);
		return changedWord;
	}

	public String replaceWordByLength(String word, int length,
			String newString) {
		// TODO validator
		String changedWord;
		char[] wordToCharArray = word.toCharArray();
		if (wordToCharArray.length == length) {
			changedWord = newString;
		} else {
			changedWord = word;
		}
		return changedWord;
	}

	public String deleteNotLetterChar(String word) {
		// TODO validator
		String changedWord;
		char[] wordToCharArray = word.toCharArray();
		for (int i = 0; i < wordToCharArray.length; i++) {
			if (!isCharLetter(wordToCharArray[i])) {
				wordToCharArray[i] = SPACE_CHAR;
			}
		}
		changedWord = String.valueOf(wordToCharArray);
		return changedWord;
	}

	private boolean isCharLetter(char someChar) {
		boolean isCharLetter;
		isCharLetter = (someChar >= FIRST_LATIN_CHAR + LOWERCASE_LETTER_SHIFTING
				&& someChar <= LAST_LATIN_CHAR + LOWERCASE_LETTER_SHIFTING)
				|| (someChar >= FIRST_LATIN_CHAR && someChar <= LAST_LATIN_CHAR)
				|| (someChar >= FIRST_CYRILLIC_CHAR
						&& someChar <= LAST_CYRILLIC_CHAR);
		return isCharLetter;
	}

	public String deleteWordByLengthStartConsonant(String word, int length) {
		// TODO validator
		String changedWord;
		char[] wordToCharArray = word.toCharArray();
		if (wordToCharArray.length == length
				&& isLetterConsonant(wordToCharArray[0])) {
			changedWord = "";
		} else {
			changedWord = word;
		}
		return changedWord;
	}

	private boolean isLetterConsonant(char someChar) {
		boolean isLetterConsonant = true;
		if (isCharLetter(someChar)) {
			int i = 0;
			while (isLetterConsonant && i < VOWEL_ARRAY.length) {
				if (someChar == VOWEL_ARRAY[i]) {
					isLetterConsonant = false;
				} else {
					i++;
				}
			}
		} else {
			isLetterConsonant = false;
		}
		return isLetterConsonant;
	}
}
