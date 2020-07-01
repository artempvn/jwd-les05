package test.artempvn.les05.parser;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import java.util.ArrayList;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les05.exception.CustomException;
import by.artempvn.les05.parser.DataParser;

public class DataParserTest {
	DataParser dataParser;
	List<String> words;

	@BeforeClass
	public void setUp() {
		dataParser = new DataParser();
		words = new ArrayList<>();
		words.add("Какие-то");
		words.add("слова");
		words.add("test");
	}

	@Test
	public void findWordsTestPositive() {
		List<String> actual = null;
		try {
			actual = dataParser.findWords("Какие-то слова, test.");
		} catch (CustomException e) {
			fail("exception occurred");
		}
		List<String> expected = words;
		assertEquals(actual, expected, " Test failed as...");
	}

	@Test(dataProvider = "findWordsTestNegative", 
			expectedExceptions = CustomException.class)
	public void findWordsTestNegative(String input) throws CustomException {
		dataParser.findWords(input);
	}

	@DataProvider
	public Object[][] findWordsTestNegative() {
		return new Object[][] { { null }, { ", !.. " }, { "" } };
	}

	@AfterClass
	public void tierDown() {
		dataParser = null;
		words = null;
	}
}
