package logger;

public class Logger {
    public static enum Level {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public static Level level = Level.ERROR;
    public static int maxLine = 50;
    public static java.io.PrintStream logFile = null;

    private static void printMsg(String m) {
        String message = m.trim();
        int restLength = maxLine;
        for (int i = 0; i < message.length(); i += 1) {
            if (message.charAt(i) == '\n') {
                System.out.println();
                System.out.print("[+] ");
                if (logFile != null) {
                    logFile.println();
                    logFile.print("[+] ");
                }
                restLength = maxLine;
                continue;
            }

            if (message.charAt(i) == ' ' && restLength == maxLine) {
                continue;
            }

            System.out.print(message.charAt(i));
            if (logFile != null) {
                logFile.print(message.charAt(i));
            }
            restLength -= 1;

            if (restLength == 0) {
                System.out.println();
                System.out.print("[+] ");
                if (logFile != null) {
                    logFile.println();
                    logFile.print("[+] ");
                }
                restLength = maxLine;
            }
        }
        
        if (message.charAt(message.length() - 1) != '\n') {
            System.out.println();
            if (logFile != null) {
                logFile.println();
            }
        }
    }

    public static void debug(String message) {
        if (level.ordinal() <= Level.DEBUG.ordinal()) {
            System.out.print("[D] ");
            if (logFile != null) {
                logFile.print("[D] ");
            }
            printMsg(message);
        }
    }
    
    public static void info(String message) {
        if (level.ordinal() <= Level.INFO.ordinal()) {
            System.out.print("[I] ");
            if (logFile != null) {
                logFile.print("[I] ");
            }
            printMsg(message);
        }
    }

    public static void warning(String message) {
        if (level.ordinal() <= Level.WARNING.ordinal()) {
            System.out.print("[W] ");
            if (logFile != null) {
                logFile.print("[W] ");
            }
            printMsg(message);
        }
    }

    public static void error(String message) {
        if (level.ordinal() <= Level.ERROR.ordinal()) {
            System.out.print("[E] ");
            if (logFile != null) {
                logFile.print("[E] ");
            }
            printMsg(message);
        }
    }
}
