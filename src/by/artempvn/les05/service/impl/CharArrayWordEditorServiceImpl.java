package by.artempvn.les05.service.impl;

import by.artempvn.les05.exception.CustomException;
import by.artempvn.les05.service.WordEditorService;

public class CharArrayWordEditorServiceImpl implements WordEditorService {
	private static final int SPACE_UTF = 32;
	private static final int FIRST_LATIN_UTF = 65;
	private static final int LAST_LATIN_UTF = 90;
	private static final int FIRST_CYRILLIC_UTF = 1040;
	private static final int LAST_CYRILLIC_UTF = 1103;
	private static final int LOWERCASE_LETTER_SHIFTING = 32;
	private static final int[] VOWEL_ARRAY = { 65, 69, 73, 79, 85, 89, 1040,
			1045, 1048, 1054, 1059, 1067, 1069, 1070, 1071 };
	private static final int HYPHEN_UTF = 45;

	@Override
	public String replaceCharAtPosition(String word, int positionForReplace,
			char newChar) throws CustomException {
		if (word == null || positionForReplace < 0) {
			throw new CustomException("Incorrect input data");
		}
		String changedWord;
		char[] wordToCharArray = word.toCharArray();
		if (positionForReplace < wordToCharArray.length) {
			wordToCharArray[positionForReplace] = newChar;
			changedWord = String.valueOf(wordToCharArray);
		} else {
			changedWord = word;
		}
		return changedWord;
	}

	@Override
	public String correctCharByPrevious(String word, char preMistakeChar,
			char mistakeChar, char correctChar) throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
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

	@Override
	public String replaceWordByLength(String word, int length, String newString)
			throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		char[] wordToCharArray = word.toCharArray();
		return ((wordToCharArray.length == length) ? newString : word);
	}

	@Override
	public String deleteNotLetterChar(String word) throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		String changedWord;
		char[] wordToCharArray = word.toCharArray();
		for (int i = 0; i < wordToCharArray.length; i++) {
			if (!isCharLetter(wordToCharArray[i])) {
				if (i > 0 && i < wordToCharArray.length - 1
						&& wordToCharArray[i] == HYPHEN_UTF) {
					if (isCharPartOfWord(wordToCharArray[i - 1],
							wordToCharArray[i + 1])) {
						continue;
					}
				}
				wordToCharArray[i] = SPACE_UTF;
			}
		}
		changedWord = String.valueOf(wordToCharArray);
		return changedWord;
	}

	private boolean isCharLetter(char someChar) {
		boolean isCharLetter;
		isCharLetter = (someChar >= FIRST_LATIN_UTF + LOWERCASE_LETTER_SHIFTING
				&& someChar <= LAST_LATIN_UTF + LOWERCASE_LETTER_SHIFTING)
				|| (someChar >= FIRST_LATIN_UTF && someChar <= LAST_LATIN_UTF)
				|| (someChar >= FIRST_CYRILLIC_UTF
						&& someChar <= LAST_CYRILLIC_UTF);
		return isCharLetter;
	}

	private boolean isCharPartOfWord(char preChar, char postChar) {
		return (isCharLetter(preChar) && isCharLetter(postChar));
	}

	@Override
	public String deleteWordByLengthStartConsonant(String word, int length)
			throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
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
				if (someChar == VOWEL_ARRAY[i] || someChar == VOWEL_ARRAY[i]
						+ LOWERCASE_LETTER_SHIFTING) {
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
