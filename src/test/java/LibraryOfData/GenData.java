package LibraryOfData;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GenData {

	public static String generateMonth(int shift) {
		return LocalDate.now().plusMonths(shift).format(DateTimeFormatter.ofPattern("MM"));
	}

	public static String generateYear(int shift) {
		return LocalDate.now().plusYears(shift).format(DateTimeFormatter.ofPattern("YY"));
	}
}
