
import java.util.Scanner;

public class DifferenceBetweenDates {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		String firstDate;
		String secondDate;
		int earlierDate;
		int laterDate;
		int firstDateInt;
		int secondDateInt;
		boolean dateValidResult;

		do {
			System.out.println("Enter first date in yyyymmdd format.");
			firstDate = input.nextLine();

			dateValidResult = isValidDate(firstDate);
			if (dateValidResult == false) {
				System.out.println("Date is not valid, please try again.");
			}
		}

		while (dateValidResult == false);

		do {
			System.out.println("Enter second date in yyyymmdd format.");
			secondDate = input.nextLine();

			dateValidResult = isValidDate(secondDate);
			if (dateValidResult == false) {
				System.out.println("Date is not valid, please try again.");
			}
		} while (dateValidResult == false);

		input.close();

		int firstIntDate = 0;
		int secondIntDate = 0;

		try {
			firstIntDate = Integer.parseInt(firstDate);
			secondIntDate = Integer.parseInt(secondDate);
		} catch (NumberFormatException e) {
			System.exit(0); // Won't happen because values have already been verified.
		}

		if (firstIntDate < secondIntDate) {
			earlierDate = firstIntDate;
			laterDate = secondIntDate;
		} else {
			earlierDate = secondIntDate;
			laterDate = firstIntDate;
		}

		int earlierYear = earlierDate / 10000; // Isolate year, discard remainder
		int earlierMonth = (earlierDate % 10000) / 100; // Divide out year, keep remainder (month and day)
														// then divide away day to get month
		int earlierDay = earlierDate % 100; // Divide away month keep remainder (day)

		int laterYear = laterDate / 10000; // Repeat logic above for other date
		int laterMonth = (laterDate % 10000) / 100;
		int laterDay = laterDate % 100;

		int differenceYears = laterYear - earlierYear;

		if (laterMonth < earlierMonth) {
			differenceYears = differenceYears - 1;
			laterMonth = laterMonth + 12;
	}
		int differenceMonths = laterMonth - earlierMonth;
		int differenceDays = 0;
		if (laterDay < earlierDay) {
			differenceDays = laterDay + (daysInMonth(earlierYear, earlierMonth) - earlierDay);
			differenceMonths = differenceMonths - 1;
		} else {
			differenceDays = laterDay - earlierDay;
		}
		System.out.print("Difference is " + differenceYears + " years " + differenceMonths + " months ");
		System.out.println(differenceDays + " days.");
	}
	/*
	 * Following code found at
	 * https://stackoverflow.com/questions/11480542/fastest-way-to-tell-if-a-string-
	 * is-a-valid-date and copied in unchanged. Google is our friend. ;)
	 */
	public static boolean isValidDate(String dateString) {
		if (dateString == null || dateString.length() != "yyyyMMdd".length()) {
			return false;
		}

		int date;
		try {
			date = Integer.parseInt(dateString);
		} catch (NumberFormatException e) {
			return false;
		}

		int year = date / 10000;
		int month = (date % 10000) / 100;
		int day = date % 100;

		// leap years calculation not valid before 1581
		boolean yearOk = (year >= 1581) && (year <= 2500);
		boolean monthOk = (month >= 1) && (month <= 12);
		boolean dayOk = (day >= 1) && (day <= daysInMonth(year, month));

		return (yearOk && monthOk && dayOk);
	}

	private static int daysInMonth(int year, int month) {
		int daysInMonth;
		switch (month) {
		case 1: // fall through
		case 3: // fall through
		case 5: // fall through
		case 7: // fall through
		case 8: // fall through
		case 10: // fall through
		case 12:
			daysInMonth = 31;
			break;
		case 2:
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
				daysInMonth = 29;
			} else {
				daysInMonth = 28;
			}
			break;
		default:
			// returns 30 even for nonexistant months
			daysInMonth = 30;
		}
		return daysInMonth;
	}
}
