package util;

public class ErrorHandling {

    public static void printTraceAndExit(Throwable e) {
        e.printStackTrace();
        System.exit(1);
    }
}
