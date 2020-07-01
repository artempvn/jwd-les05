package by.artempvn.les05.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import by.artempvn.les05.exception.CustomException;

public class DataReader {
	private static final String DEFAULT_PASS = "input/data.txt";
	private static final String SPACE_DELIMITER = " ";

	public String readFile(String path) throws CustomException {
		if (path == null || !Files.isReadable(Paths.get(path))) {
			path = DEFAULT_PASS;
		}
		List<String> strings = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			strings = stream.collect(Collectors.toList());
		} catch (IOException ex) {
			throw new CustomException("Error: could't open data file.", ex);
		}
		String data = String.join(SPACE_DELIMITER, strings);
		if (data.isEmpty()) {
			throw new CustomException("Data is empty");
		}
		return data;
	}

	public String readConsole() throws CustomException {
		System.out.println("Input text data");
		Scanner scanner = new Scanner(System.in);
		String data = scanner.nextLine();
		if (data.isEmpty()) {
			throw new CustomException("Data is empty");
		}
		return data;
	}
}
