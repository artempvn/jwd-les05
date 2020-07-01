package by.artempvn.les05.service.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.artempvn.les05.exception.CustomException;
import by.artempvn.les05.service.WordEditorService;

public class RegexWordEditorServiceImpl implements WordEditorService {

	private static final char SPACE = ' ';
	private static final String VOWEL_STRING = "AEIOUYaeiouyАЕИОУЫЭЮЯаеиоуыэюя";
	private static final String REGEX_POSITION_START = "(?<=.{";
	private static final String REGEX_POSITION_END = "}).(?=.*)";
	private static final String REGEX_LENGTH_START = ".{";
	private static final String REGEX_LENGTH_END = "}";
	private static final String REGEX_NON_LETTER = "([^-\\p{L}])"
			+ "|((?<!\\p{L})\\-(?!\\p{L}))|((?<!\\p{L})\\-(?=\\p{L}))|"
			+ "((?<=\\p{L})\\-(?!\\p{L}))";
	private static final String REGEX_CLASS_START = "[";
	private static final String REGEX_CLASS_END = "]";
	private static final String EMPTY_STRING = "";

	@Override
	public String replaceCharAtPosition(String word, int positionForReplace,
			char newChar) throws CustomException {
		if (word == null || positionForReplace < 0) {
			throw new CustomException("Incorrect input data");
		}
		Pattern patternReplacedChar = Pattern.compile(String.format("%s%d%s",
				REGEX_POSITION_START, positionForReplace, REGEX_POSITION_END));
		Matcher matcherReplacedChar = patternReplacedChar.matcher(word);
		String changedWord = matcherReplacedChar
				.replaceFirst(String.valueOf(newChar));
		return changedWord;
	}

	@Override
	public String correctCharByPrevious(String word, char preMistakeChar,
			char mistakeChar, char correctChar) throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		Pattern patternIncorrectSequence = Pattern
				.compile(String.format("%c%c", preMistakeChar, mistakeChar));
		Matcher matcherIncorrectSequence = patternIncorrectSequence
				.matcher(word);
		String changedWord = matcherIncorrectSequence
				.replaceAll(String.format("%c%c", preMistakeChar, correctChar));
		return changedWord;
	}

	@Override
	public String replaceWordByLength(String word, int length, String newString)
			throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		Pattern patternLength = Pattern.compile(String.format("%s%d%s",
				REGEX_LENGTH_START, length, REGEX_LENGTH_END));
		Matcher matcherLength = patternLength.matcher(word);
		return ((matcherLength.matches()) ? newString : word);
	}

	@Override
	public String deleteNotLetterChar(String word) throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		Pattern patternNotLetterChar = Pattern.compile(REGEX_NON_LETTER);
		Matcher matcherNotLetterChar = patternNotLetterChar.matcher(word);
		String changedWord = matcherNotLetterChar
				.replaceAll(String.valueOf(SPACE));
		return changedWord;
	}

	@Override
	public String deleteWordByLengthStartConsonant(String word, int length)
			throws CustomException {
		if (word == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		Pattern patternLength = Pattern.compile(String.format("%s%d%s",
				REGEX_LENGTH_START, length, REGEX_LENGTH_END));
		Matcher matcherLength = patternLength.matcher(word);
		Pattern patternVowel = Pattern.compile(String.format("%s%s%s",
				REGEX_CLASS_START, VOWEL_STRING, REGEX_CLASS_END));
		Matcher matcherVowel = patternVowel
				.matcher(String.valueOf(word.charAt(0)));
		return ((matcherLength.matches() && !matcherVowel.matches())
				? EMPTY_STRING
				: word);
	}
}
