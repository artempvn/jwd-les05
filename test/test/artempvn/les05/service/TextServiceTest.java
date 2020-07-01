package test.artempvn.les05.service;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les05.entity.TypeOfEditing;
import by.artempvn.les05.exception.CustomException;
import by.artempvn.les05.service.TextService;

public class TextServiceTest {
	TextService textService;
	List<String> inputArrayWords;
	String inputText;
	String outputStringReplaceCharAtPosition;
	String outputStringCorrectCharByPrevious;
	String outputStringReplaceWordByLength;
	String outputStringDeleteNotLetterChar;
	String outputStringDeleteWordByLengthStartConsonant;
	String outputString2ReplaceCharAtPosition;
	String outputString2CorrectCharByPrevious;
	String outputString2ReplaceWordByLength;
	String outputString2DeleteNotLetterChar;
	String outputString2DeleteWordByLengthStartConsonant;

	@BeforeClass
	public void setUp() {
		textService = new TextService();
		outputStringReplaceCharAtPosition = "Ja3a is th3 1 pr3gramming "
				+ "la3guage an3 de3elopment pl3tform";
		outputStringCorrectCharByPrevious = "Javo is the 1 programming"
				+ " language and development platform";
		outputStringReplaceWordByLength = "Java is the 1 programming"
				+ " java and development java";
		outputStringDeleteNotLetterChar = "Java is the   programming"
				+ " language and development platform";
		outputStringDeleteWordByLengthStartConsonant = "Java is the 1 "
				+ "programming  and development ";
		inputText = "Java is the #1 programming language and development "
				+ "platform.";
		outputString2ReplaceCharAtPosition = "Ja3a is th3 #1 pr3gramming"
				+ " la3guage an3 de3elopment pl3tform.";
		outputString2CorrectCharByPrevious = "Javo is the #1 programming"
				+ " language and development platform.";
		outputString2ReplaceWordByLength = "Java is the #1 programming"
				+ " java and development java.";
		outputString2DeleteNotLetterChar = "Java is the    programming"
				+ " language and development platform ";
		outputString2DeleteWordByLengthStartConsonant = "Java is the #1 "
				+ "programming  and development .";
	}

	@BeforeMethod
	public void beforeMethod() {
		inputArrayWords = new ArrayList<>();
		String[] words = { "Java", "is", "the", "1", "programming", "language",
				"and", "development", "platform" };
		inputArrayWords.addAll(Arrays.asList(words));
	}

	@Test(dataProvider = "typeOfEditing")
	public void replaceCharAtPositionTestPositive(TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService.replaceCharAtPosition(inputArrayWords, type, 2,
					'3');
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputStringReplaceCharAtPosition;
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] typeOfEditing() {
		return new Object[][] { { TypeOfEditing.CHAR_ARRAY },
				{ TypeOfEditing.REGEX }, { TypeOfEditing.STRING } };
	}

	@Test(dataProvider = "testNegative",
			expectedExceptions = CustomException.class)
	public void replaceCharAtPositionTestNegative(List<String> words,
			TypeOfEditing type) throws CustomException {
		textService.replaceCharAtPosition(words, type, 2, '3');
	}

	@DataProvider
	public Object[][] testNegative() {
		beforeMethod();
		return new Object[][] { { null, TypeOfEditing.CHAR_ARRAY },
				{ null, null }, { inputArrayWords, null } };
	}

	@Test(dataProvider = "typeOfEditing")
	public void correctCharByPreviousTestPositive(TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService.correctCharByPrevious(inputArrayWords, type,
					'v', 'a', 'o');
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputStringCorrectCharByPrevious;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "testNegative",
			expectedExceptions = CustomException.class)
	public void correctCharByPreviousTestNegative(List<String> words,
			TypeOfEditing type) throws CustomException {
		textService.correctCharByPrevious(words, type, 'e', 's', 'x');
	}

	@Test(dataProvider = "typeOfEditing")
	public void replaceWordByLengthTestPositive(TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService.replaceWordByLength(inputArrayWords, type, 8,
					"java");
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputStringReplaceWordByLength;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "testNegative",
			expectedExceptions = CustomException.class)
	public void replaceWordByLengthTestNegative(List<String> words,
			TypeOfEditing type) throws CustomException {
		textService.replaceWordByLength(words, type, 8, "java");
	}

	@Test(dataProvider = "typeOfEditing")
	public void deleteNotLetterCharTestPositive(TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService.deleteNotLetterChar(inputArrayWords, type);
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputStringDeleteNotLetterChar;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "testNegative", 
			expectedExceptions = CustomException.class)
	public void deleteNotLetterCharTestNegative(List<String> words,
			TypeOfEditing type) throws CustomException {
		textService.deleteNotLetterChar(words, type);
	}

	@Test(dataProvider = "typeOfEditing")
	public void deleteWordByLengthStartConsonantTestPositive(
			TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService
					.deleteWordByLengthStartConsonant(inputArrayWords, type, 8);
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputStringDeleteWordByLengthStartConsonant;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "testNegative",
			expectedExceptions = CustomException.class)
	public void deleteWordByLengthStartConsonantTestNegative(List<String> words,
			TypeOfEditing type) throws CustomException {
		textService.deleteWordByLengthStartConsonant(words, type, 3);
	}

	@Test(dataProvider = "typeOfEditing")
	public void replaceCharAtPositionTest2Positive(TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService.replaceCharAtPosition(inputText, type, 2, '3');
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputString2ReplaceCharAtPosition;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "test2Negative",
			expectedExceptions = CustomException.class)
	public void replaceCharAtPositionTest2Negative(String text,
			TypeOfEditing type) throws CustomException {
		textService.replaceCharAtPosition(text, type, 2, '3');
	}

	@DataProvider
	public Object[][] test2Negative() {
		beforeMethod();
		return new Object[][] { { null, TypeOfEditing.CHAR_ARRAY },
				{ null, null }, { inputText, null } };
	}

	@Test(dataProvider = "typeOfEditing")
	public void correctCharByPreviousTest2Positive(TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService.correctCharByPrevious(inputText, type, 'v',
					'a', 'o');
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputString2CorrectCharByPrevious;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "test2Negative",
			expectedExceptions = CustomException.class)
	public void correctCharByPreviousTest2Negative(String text,
			TypeOfEditing type) throws CustomException {
		textService.correctCharByPrevious(text, type, 'e', 's', 'x');
	}

	@Test(dataProvider = "typeOfEditing")
	public void replaceWordByLengthTest2Positive(TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService.replaceWordByLength(inputText, type, 8,
					"java");
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputString2ReplaceWordByLength;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "test2Negative", 
			expectedExceptions = CustomException.class)
	public void replaceWordByLengthTest2Negative(String text,
			TypeOfEditing type) throws CustomException {
		textService.replaceWordByLength(text, type, 8, "java");
	}

	@Test(dataProvider = "typeOfEditing")
	public void deleteNotLetterCharTest2Positive(TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService.deleteNotLetterChar(inputText, type);
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputString2DeleteNotLetterChar;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "test2Negative",
			expectedExceptions = CustomException.class)
	public void deleteNotLetterCharTest2Negative(String text,
			TypeOfEditing type) throws CustomException {
		textService.deleteNotLetterChar(text, type);
	}

	@Test(dataProvider = "typeOfEditing")
	public void deleteWordByLengthStartConsonantTest2Positive(
			TypeOfEditing type) {
		String actual = null;
		try {
			actual = textService.deleteWordByLengthStartConsonant(inputText,
					type, 8);
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = outputString2DeleteWordByLengthStartConsonant;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "test2Negative",
			expectedExceptions = CustomException.class)
	public void deleteWordByLengthStartConsonantTest2Negative(String text,
			TypeOfEditing type) throws CustomException {
		textService.deleteWordByLengthStartConsonant(text, type, 3);
	}

	@AfterMethod
	public void afterMethod() {
		inputArrayWords = null;
	}

	@AfterClass
	public void tierDown() {
		textService = null;
	}
}
