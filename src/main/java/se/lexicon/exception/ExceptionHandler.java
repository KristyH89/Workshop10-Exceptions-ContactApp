package se.lexicon.exception;

/// Centralized exception handler — single place to change error output format.
public class ExceptionHandler {

    private ExceptionHandler() {}

    /// Logs `[ERROR] ClassName: message` to stderr.
    public static void handle(Exception e) {
        System.err.println("[ERROR] " + e.getClass().getSimpleName() + ": " + e.getMessage());
    }
}
