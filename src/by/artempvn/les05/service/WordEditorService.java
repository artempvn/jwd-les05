package by.artempvn.les05.service;

import by.artempvn.les05.exception.CustomException;

public interface WordEditorService {

	String replaceCharAtPosition(String word, int positionForReplace,
			char newChar) throws CustomException;

	String correctCharByPrevious(String word, char preMistakeChar,
			char mistakeChar, char correctChar) throws CustomException;

	String replaceWordByLength(String word, int length, String newString)
			throws CustomException;

	String deleteNotLetterChar(String word) throws CustomException;

	String deleteWordByLengthStartConsonant(String word, int length)
			throws CustomException;

}
