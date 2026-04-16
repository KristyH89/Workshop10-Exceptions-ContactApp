package se.lexicon.exception;

public class ExceptionHandler {

    public static void handle(Exception e) {
        IO.println("ERROR: " + e.getMessage());
    }
}