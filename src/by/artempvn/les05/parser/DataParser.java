package by.artempvn.les05.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import by.artempvn.les05.exception.CustomException;

public class DataParser {
	private static final String PATTERN_WORD = "(?<=\\b)"
			+ "([\\p{L}\\d](-([\\p{L}\\d])+)?)+?(?=\\b)";

	public List<String> findWords(String input) throws CustomException {
		if (input == null || input.isEmpty()) {
			throw new CustomException("Null or empty input data");
		}
		List<String> words = new ArrayList<>();
		Pattern patternWord = Pattern.compile(PATTERN_WORD);
		Matcher matcherWord = patternWord.matcher(input);
		while (matcherWord.find()) {
			words.add(input.substring(matcherWord.start(), matcherWord.end()));
		}
		if (words.size() < 1) {
			throw new CustomException("Can't find any word");
		}
		return words;
	}
}
