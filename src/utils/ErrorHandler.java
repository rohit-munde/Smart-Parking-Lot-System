package utils;

public class ErrorHandler {
    public static void handle(String errorType, String message) {
        printErrorBox(errorType, message);
    }

    public static void handle(Exception e) {
        printErrorBox(e.getClass().getSimpleName(), e.getMessage());
    }

    private static void printErrorBox(String errorType, String message) {
        String title = "ERROR: " + errorType;
        int width = Math.max(title.length(), message != null ? message.length() : 10) + 4;

        String border = "+" + "-".repeat(width) + "+";
        String empty = "|" + " ".repeat(width) + "|";

        System.out.println();
        System.out.println(border);
        System.out.println(empty);
        System.out.printf("| %-" + (width - 2) + "s |%n", title);
        System.out.printf("| %-" + (width - 2) + "s |%n", message != null ? message : "Unknown error");
        System.out.println(empty);
        System.out.println(border);
        System.out.println();
    }
}
