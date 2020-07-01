package test.artempvn.les05.service.impl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les05.exception.CustomException;
import by.artempvn.les05.service.impl.CharArrayWordEditorServiceImpl;

public class CharArrayWordEditorServiceImplTest {
	CharArrayWordEditorServiceImpl charArrayWordEditorServiceImpl;

	@BeforeClass
	public void setUp() {
		charArrayWordEditorServiceImpl = new CharArrayWordEditorServiceImpl();
	}

	@Test(dataProvider = "replaceCharAtPositionTestPositive")
	public void replaceCharAtPositionTestPositive(String word,
			int positionForReplace, char newChar, String expected) {
		String actual = null;
		try {
			actual = charArrayWordEditorServiceImpl.replaceCharAtPosition(word,
					positionForReplace, newChar);
		} catch (CustomException e) {
			fail("exception occurred");
		}
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] replaceCharAtPositionTestPositive() {
		return new Object[][] { { "test", 2, 'x', "text" },
				{ "test", 4, 'x', "test" } };
	}

	@Test(dataProvider = "replaceCharAtPositionTestNegative",
			expectedExceptions = CustomException.class)
	public void replaceCharAtPositionTestNegative(String word,
			int positionForReplace) throws CustomException {
		charArrayWordEditorServiceImpl.replaceCharAtPosition(word,
				positionForReplace, 'x');
	}

	@DataProvider
	public Object[][] replaceCharAtPositionTestNegative() {
		return new Object[][] { { "test", -1 }, { null, 4 } };
	}

	@Test(dataProvider = "correctCharByPreviousTestPositive")
	public void correctCharByPreviousTestPositive(String word,
			char preMistakeChar, char mistakeChar, char correctChar,
			String expected) {
		String actual = null;
		try {
			actual = charArrayWordEditorServiceImpl.correctCharByPrevious(word,
					preMistakeChar, mistakeChar, correctChar);
		} catch (CustomException e) {
			fail("exception occurred");
		}
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] correctCharByPreviousTestPositive() {
		return new Object[][] { { "test", 'e', 's', 'x', "text" },
				{ "test", 'e', 't', 's', "test" } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void correctCharByPreviousTestNegative() throws CustomException {
		charArrayWordEditorServiceImpl.correctCharByPrevious(null, 'e', 's',
				'x');
	}

	@Test(dataProvider = "replaceWordByLengthTestPositive")
	public void replaceWordByLengthTestPositive(String word, int length,
			String newString, String expected) {
		String actual = null;
		try {
			actual = charArrayWordEditorServiceImpl.replaceWordByLength(word,
					length, newString);
		} catch (CustomException e) {
			fail("exception occurred");
		}
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] replaceWordByLengthTestPositive() {
		return new Object[][] { { "test", 4, "text", "text" },
				{ "test", 3, "text", "test" } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void replaceWordByLengthTestNegative() throws CustomException {
		charArrayWordEditorServiceImpl.replaceWordByLength(null, 4, "text");
	}

	@Test(dataProvider = "deleteNotLetterCharTestPositive")
	public void deleteNotLetterCharTestPositive(String word, String expected) {
		String actual = null;
		try {
			actual = charArrayWordEditorServiceImpl.deleteNotLetterChar(word);
		} catch (CustomException e) {
			fail("exception occurred");
		}
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] deleteNotLetterCharTestPositive() {
		return new Object[][] { { "Tes7t,", "Tes t " },
				{ "-Как-то8-так-", " Как-то  так " } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void deleteNotLetterCharTestNegative() throws CustomException {
		charArrayWordEditorServiceImpl.deleteNotLetterChar(null);
	}

	@Test(dataProvider = "deleteWordByLengthStartConsonantTestPositive")
	public void deleteWordByLengthStartConsonantTestPositive(String word,
			int length, String expected) {
		String actual = null;
		try {
			actual = charArrayWordEditorServiceImpl
					.deleteWordByLengthStartConsonant(word, length);
		} catch (CustomException e) {
			fail("exception occurred");
		}
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] deleteWordByLengthStartConsonantTestPositive() {
		return new Object[][] { { "Test", 4, "" }, { "Test", 3, "Test" },
				{ "east", 4, "east" }, { "юг", 2, "юг" }, { "север", 5, "" } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void deleteWordByLengthStartConsonantTestNegative()
			throws CustomException {
		charArrayWordEditorServiceImpl.deleteWordByLengthStartConsonant(null,
				3);
	}

	@AfterClass
	public void tierDown() {
		charArrayWordEditorServiceImpl = null;
	}
}
