package by.artempvn.les05.service.impl;

import by.artempvn.les05.exception.CustomException;
import by.artempvn.les05.service.WordEditorService;

public class StringWordEditorServiceImpl implements WordEditorService {

	private static final String EMPTY_STRING = "";
	private static final String LETTER_REGEX = "\\p{L}";
	private static final char SPACE = ' ';
	private static final char HYPHEN = '-';
	private static final String VOWEL_STRING = "AEIOUYaeiouyАЕИОУЫЭЮЯаеиоуыэюя";

	@Override
	public String replaceCharAtPosition(String word, int positionForReplace,
			char newChar) throws CustomException {
		if (word == null || positionForReplace < 0) {
			throw new CustomException("Incorrect input data");
		}
		StringBuilder changedWord = new StringBuilder(word);
		if (word.length() > positionForReplace) {
			changedWord.setCharAt(positionForReplace, newChar);
		}
		return changedWord.toString();
	}

	@Override
	public String correctCharByPrevious(String word, char preMistakeChar,
			char mistakeChar, char correctChar) throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		String incorrectSequence = String.format("%c%c", preMistakeChar,
				mistakeChar);
		String correctSequence = String.format("%c%c", preMistakeChar,
				correctChar);
		word = word.replace(incorrectSequence, correctSequence);
		return word;
	}

	@Override
	public String replaceWordByLength(String word, int length, String newString)
			throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		return ((word.length() == length) ? newString : word);
	}

	@Override
	public String deleteNotLetterChar(String word) throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		StringBuilder changedWord = new StringBuilder(word);
		for (int i = 0; i < changedWord.length(); i++) {
			if (!isCharLetter(changedWord.charAt(i))) {
				if (i > 0 && i < changedWord.length() - 1
						&& changedWord.charAt(i) == HYPHEN) {
					if (isCharPartOfWord(changedWord.charAt(i - 1),
							changedWord.charAt(i + 1))) {
						continue;
					}
				}
				changedWord.setCharAt(i, SPACE);
			}
		}
		return changedWord.toString();
	}

	private boolean isCharLetter(char someChar) {
		String someCharToString = String.valueOf(someChar);
		return someCharToString.matches(LETTER_REGEX);
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
		if (word.length() == length && isLetterConsonant(word.charAt(0))) {
			changedWord = EMPTY_STRING;
		} else {
			changedWord = word;
		}
		return changedWord;
	}

	private boolean isLetterConsonant(char someChar) {
		String someCharToString = String.valueOf(someChar);
		return (isCharLetter(someChar)
				&& !VOWEL_STRING.contains(someCharToString));
	}
}
