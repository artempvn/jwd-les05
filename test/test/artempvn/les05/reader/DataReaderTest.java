package test.artempvn.les05.reader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import by.artempvn.les05.exception.CustomException;
import by.artempvn.les05.reader.DataReader;

public class DataReaderTest {
	DataReader dataReader;

	@BeforeClass
	public void setUp() {
		dataReader = new DataReader();
	}

	@Test(dataProvider = "readFileTest")
	public void readFileTestPositive(String path) {
		String actual = null;
		try {
			actual = dataReader.readFile("path");
		} catch (CustomException e) {
			fail("exception occurred");
		}
		String expected = "Пожалуй, лучше всего делать записи изо дня в день."
				+ " Вести дневник, чтобы докопаться до сути. Не упускать "
				+ "оттенков, мелких фактов, даже если кажется, что они "
				+ "несущественны, и, главное, привести их в систему. Описывать,"
				+ " как я вижу этот стол, улицу, людей, мой кисет, потому что"
				+ " ЭТО-ТО и изменилось. Надо точно определить масштаб и "
				+ "характер этой перемены.";
		assertEquals(actual, expected, " Test failed as...");
	}

	@DataProvider
	public Object[][] readFileTest() {
		return new Object[][] { { "input/data.txt" }, { "input/wrongData.txt" },
				{ null } };
	}

	@Test(expectedExceptions = CustomException.class)
	public void readFileTestNegative() throws CustomException {
		dataReader.readFile("input/dataTest.txt");
	}

	@AfterClass
	public void tierDown() {
		dataReader = null;
	}
}
