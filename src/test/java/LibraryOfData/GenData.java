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

	public static String approvedCard (){
		String approvedCard = "4444444444444441";
		return approvedCard;
	}
	public static String declinedCard (){
		String declinedCard = "4444444444444442";
		return declinedCard;
	}
	public static String unknownCard (){
		String unknownCard = "4444444444444443";
		return unknownCard;
	}
}
