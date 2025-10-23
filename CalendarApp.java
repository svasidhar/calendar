import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

public class CalendarApp {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                LocalDate now = LocalDate.now();
                printMonth(now.getMonthValue(), now.getYear());
            } else if (args.length == 1) {
                String a = args[0];
                // If single numeric argument with 4 digits or >= 1000 -> print full year
                if (isInteger(a)) {
                    int v = Integer.parseInt(a);
                    if (a.length() == 4 || v >= 1000) {
                        printYear(v);
                    } else if (v >= 1 && v <= 12) {
                        int year = LocalDate.now().getYear();
                        printMonth(v, year);
                    } else {
                        usageAndExit("Single numeric argument must be month (1-12) or year (e.g. 2025).");
                    }
                } else {
                    usageAndExit("Unknown argument: " + a);
                }
            } else if (args.length == 2) {
                String a = args[0];
                String b = args[1];
                if (!isInteger(a) || !isInteger(b)) {
                    usageAndExit("Two arguments must be numeric: <month> <year>");
                }
                int month = Integer.parseInt(a);
                int year  = Integer.parseInt(b);
                if (month < 1 || month > 12) usageAndExit("Month must be 1..12");
                printMonth(month, year);
            } else {
                usageAndExit(null);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(2);
        }
    }

    private static boolean isInteger(String s) {
        if (s == null || s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i)) && !(i==0 && s.charAt(i)=='-')) return false;
        }
        return true;
    }

    private static void usageAndExit(String err) {
        if (err != null) System.err.println(err);
        System.out.println("Usage:");
        System.out.println("  java CalendarApp            # current month");
        System.out.println("  java CalendarApp <month>    # month (1-12) in current year");
        System.out.println("  java CalendarApp <year>     # full year (e.g. 2025)");
        System.out.println("  java CalendarApp <month> <year>");
        System.out.println("Examples:");
        System.out.println("  java CalendarApp");
        System.out.println("  java CalendarApp 6 2025");
        System.out.println("  java CalendarApp 2025");
        System.exit(1);
    }

    private static void printMonth(int month, int year) {
        YearMonth ym = YearMonth.of(year, month);
        String monthName = ym.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
        String header = monthName + " " + year;
        int width = 20; // "Su Mo Tu We Th Fr Sa" -> 20 chars with spaces
        int pad = Math.max(0, (width - header.length())/2);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pad; i++) sb.append(' ');
        sb.append(header);
        System.out.println(sb.toString());
        System.out.println("Su Mo Tu We Th Fr Sa");

        LocalDate first = ym.atDay(1);
        int firstDow = first.getDayOfWeek().getValue() % 7; // Sunday -> 0, Monday -> 1, ...
        // print initial spaces
        for (int i = 0; i < firstDow; i++) {
            System.out.print("   ");
        }
        int days = ym.lengthOfMonth();
        for (int day = 1; day <= days; day++) {
            System.out.printf("%2d", day);
            if ((firstDow + day) % 7 == 0) {
                System.out.println();
            } else {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    private static void printYear(int year) {
        System.out.println("Calendar - " + year);
        for (int m = 1; m <= 12; m++) {
            System.out.println();
            printMonth(m, year);
        }
    }
}