package by.artempvn.les05.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.artempvn.les05.entity.TypeOfEditing;
import by.artempvn.les05.exception.CustomException;
import by.artempvn.les05.service.impl.CharArrayWordEditorServiceImpl;
import by.artempvn.les05.service.impl.RegexWordEditorServiceImpl;
import by.artempvn.les05.service.impl.StringWordEditorServiceImpl;

public class TextService {

	private static final String SPACE_DELIMITER = " ";
	private static final String PATTERN_WORD = "(?<=\\b)([\\p{L}\\d]"
			+ "(-([\\p{L}\\d])+)?)+?(?=\\b)";

	private WordEditorService chooseTypeOfEditing(TypeOfEditing type) {
		WordEditorService wordEditorService = null;
		switch (type) {
		case CHAR_ARRAY:
			wordEditorService = new CharArrayWordEditorServiceImpl();
			break;
		case REGEX:
			wordEditorService = new RegexWordEditorServiceImpl();
			break;
		case STRING:
			wordEditorService = new StringWordEditorServiceImpl();
			break;
		}
		return wordEditorService;
	}

	// these methods work with parsed text (into array of words) without
	// punctuation, etc.

	public String replaceCharAtPosition(List<String> words, TypeOfEditing type,
			int positionForReplace, char newChar) throws CustomException {
		if (words == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		for (int i = 0; i < words.size(); i++) {
			words.set(i, wordEditorService.replaceCharAtPosition(words.get(i),
					positionForReplace, newChar));
		}
		return (String.join(SPACE_DELIMITER, words));
	}

	public String correctCharByPrevious(List<String> words, TypeOfEditing type,
			char preMistakeChar, char mistakeChar, char correctChar)
			throws CustomException {
		if (words == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		for (int i = 0; i < words.size(); i++) {
			words.set(i, wordEditorService.correctCharByPrevious(words.get(i),
					preMistakeChar, mistakeChar, correctChar));
		}
		return (String.join(SPACE_DELIMITER, words));
	}

	public String replaceWordByLength(List<String> words, TypeOfEditing type,
			int length, String newString) throws CustomException {
		if (words == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		for (int i = 0; i < words.size(); i++) {
			words.set(i, wordEditorService.replaceWordByLength(words.get(i),
					length, newString));
		}
		return (String.join(SPACE_DELIMITER, words));
	}

	public String deleteNotLetterChar(List<String> words, TypeOfEditing type)
			throws CustomException {
		if (words == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		for (int i = 0; i < words.size(); i++) {
			words.set(i, wordEditorService.deleteNotLetterChar(words.get(i)));
		}
		return (String.join(SPACE_DELIMITER, words));
	}

	public String deleteWordByLengthStartConsonant(List<String> words,
			TypeOfEditing type, int length) throws CustomException {
		if (words == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		for (int i = 0; i < words.size(); i++) {
			words.set(i, wordEditorService
					.deleteWordByLengthStartConsonant(words.get(i), length));
		}
		return (String.join(SPACE_DELIMITER, words));
	}

	// these methods work with original text saving punctuation, etc.

	public String replaceCharAtPosition(String originalString,
			TypeOfEditing type, int positionForReplace, char newChar)
			throws CustomException {
		if (originalString == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		StringBuilder text = new StringBuilder(originalString);
		Pattern patternWord = Pattern.compile(PATTERN_WORD);
		Matcher matcherWord = patternWord.matcher(text);
		while (matcherWord.find()) {
			int start = matcherWord.start();
			int end = matcherWord.end();
			text.replace(start, end, wordEditorService.replaceCharAtPosition(
					text.substring(start, end), positionForReplace, newChar));
		}
		return (text.toString());
	}

	public String correctCharByPrevious(String originalString,
			TypeOfEditing type, char preMistakeChar, char mistakeChar,
			char correctChar) throws CustomException {
		if (originalString == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		StringBuilder text = new StringBuilder(originalString);
		Pattern patternWord = Pattern.compile(PATTERN_WORD);
		Matcher matcherWord = patternWord.matcher(text);
		while (matcherWord.find()) {
			int start = matcherWord.start();
			int end = matcherWord.end();
			text.replace(start, end,
					wordEditorService.correctCharByPrevious(
							text.substring(start, end), preMistakeChar,
							mistakeChar, correctChar));
		}
		return (text.toString());
	}

	public String replaceWordByLength(String originalString, TypeOfEditing type,
			int length, String newString) throws CustomException {
		if (originalString == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		StringBuilder text = new StringBuilder(originalString);
		Pattern patternWord = Pattern.compile(PATTERN_WORD);
		Matcher matcherWord = patternWord.matcher(originalString);
		int delta = 0;
		while (matcherWord.find()) {
			int start = matcherWord.start();
			int end = matcherWord.end();
			String originalWord = originalString.substring(start, end);
			String changedWord = wordEditorService
					.replaceWordByLength(originalWord, length, newString);
			text.replace(start + delta, end + delta, changedWord);
			delta += changedWord.length() - originalWord.length();
		}
		return (text.toString());
	}

	public String deleteNotLetterChar(String originalString, TypeOfEditing type)
			throws CustomException {
		if (originalString == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		String outputString = wordEditorService
				.deleteNotLetterChar(originalString);
		return outputString;
	}

	public String deleteWordByLengthStartConsonant(String originalString,
			TypeOfEditing type, int length) throws CustomException {
		if (originalString == null || type == null) {
			throw new CustomException("Incorrect input data (null)");
		}
		WordEditorService wordEditorService = chooseTypeOfEditing(type);
		StringBuilder text = new StringBuilder(originalString);
		Pattern patternWord = Pattern.compile(PATTERN_WORD);
		Matcher matcherWord = patternWord.matcher(originalString);
		int delta = 0;
		while (matcherWord.find()) {
			int start = matcherWord.start();
			int end = matcherWord.end();
			String originalWord = originalString.substring(start, end);
			String changedWord = wordEditorService
					.deleteWordByLengthStartConsonant(originalWord, length);
			text.replace(start + delta, end + delta, changedWord);
			delta += changedWord.length() - originalWord.length();
		}
		return (text.toString());
	}
}
