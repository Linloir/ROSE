import java.io.*;

import exceptions.*;
import lexer.*;
import parser.*;
import logger.*;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("[E] Error: Missing input file");
            System.out.println("\nUsage: java Main [arguments] <inputfile>");
            System.out.println("argumets:");
            System.out.println("\t-d, --debug: Print debug information");
            System.out.println("\t-h, --help: Print this help message");
            System.out.println("\t-e, --encoding <encoding>: Specify the encoding of the input file");
            System.out.println("\t\t<encoding>: The encoding of the input file, e.g. \"UTF-8\"");
            System.out.println("\t-l, --log <logfile>: Specify the log file");
            System.out.println("\t\t<logfile>: The log file, e.g. \"log.txt\"");
            return;
        }

        String encodingName = "UTF-8";
        boolean debug = false;
        String logFileName = null;
        String inputFileName = null;
        boolean delay = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-d") || args[i].equals("--debug")) {
                debug = true;
            } else if (args[i].equals("-h") || args[i].equals("--help")) {
                System.out.println("Usage: java Main [arguments] <inputfile>");
                System.out.println("argumets:");
                System.out.println("\t-d, --debug: Print debug information");
                System.out.println("\t    --delay: Delay procedure signature checking");
                System.out.println("\t-e, --encoding <encoding>: Specify the encoding of the input file");
                System.out.println("\t\t<encoding>: The encoding of the input file, e.g. \"UTF-8\"");
                System.out.println("\t-h, --help: Print this help message");
                System.out.println("\t-l, --log <logfile>: Specify the log file");
                System.out.println("\t\t<logfile>: The log file, e.g. \"log.txt\"");
                return;
            } else if (args[i].equals("--delay")) {
                delay = true;
            } else if (args[i].equals("-e") || args[i].equals("--encoding")) {
                if (i + 1 < args.length) {
                    encodingName = args[++i];
                } else {
                    System.out.println("[E] Error: Missing encoding name");
                    System.out.println("\nUsage: java Main [arguments] <inputfile>");
                    System.out.println("argumets:");
                    System.out.println("\t-d, --debug: Print debug information");
                    System.out.println("\t    --delay: Delay procedure signature checking");
                    System.out.println("\t-e, --encoding <encoding>: Specify the encoding of the input file");
                    System.out.println("\t\t<encoding>: The encoding of the input file, e.g. \"UTF-8\"");
                    System.out.println("\t-h, --help: Print this help message");
                    System.out.println("\t-l, --log <logfile>: Specify the log file");
                    System.out.println("\t\t<logfile>: The log file, e.g. \"log.txt\"");
                    return;
                }
            } else if (args[i].equals("-l") || args[i].equals("--log")) {
                if (i + 1 < args.length) {
                    logFileName = args[++i];
                } else {
                    System.out.println("[E] Error: Missing log file name");
                    System.out.println("\nUsage: java Main [arguments] <inputfile>");
                    System.out.println("argumets:");
                    System.out.println("\t-d, --debug: Print debug information");
                    System.out.println("\t    --delay: Delay procedure signature checking");
                    System.out.println("\t-e, --encoding <encoding>: Specify the encoding of the input file");
                    System.out.println("\t\t<encoding>: The encoding of the input file, e.g. \"UTF-8\"");
                    System.out.println("\t-h, --help: Print this help message");
                    System.out.println("\t-l, --log <logfile>: Specify the log file");
                    System.out.println("\t\t<logfile>: The log file, e.g. \"log.txt\"");
                    return;
                }
            } else {
                inputFileName = args[i];
            }
        }

        if (inputFileName == null) {
            System.out.println("[E] Error: Missing input file");
            System.out.println("\nUsage: java Main [arguments] <inputfile>");
            System.out.println("argumets:");
            System.out.println("\t-d, --debug: Print debug information");
            System.out.println("\t    --delay: Delay procedure signature checking");
            System.out.println("\t-e, --encoding <encoding>: Specify the encoding of the input file");
            System.out.println("\t\t<encoding>: The encoding of the input file, e.g. \"UTF-8\"");
            System.out.println("\t-h, --help: Print this help message");
            System.out.println("\t-l, --log <logfile>: Specify the log file");
            System.out.println("\t\t<logfile>: The log file, e.g. \"log.txt\"");
            return;
        }

        try {
            Logger.level = debug ? Logger.Level.DEBUG : Logger.Level.ERROR;
            Logger.maxLine = 100;

            Parser.DELAY_CALL_VERIFY = delay;

            if (logFileName != null) {
                FileOutputStream logStream = new FileOutputStream(logFileName);
                Logger.logFile = new PrintStream(logStream);
            }

            FileInputStream stream = new FileInputStream(inputFileName);
            Reader reader = new InputStreamReader(stream, encodingName);

            Logger.debug("File opened");

            Lexer lexer = new Lexer(reader);
            Parser parser = new Parser(lexer::next_token);

            parser.parse();
        } catch (FileNotFoundException e) {
            Logger.error("Error: Input file \"" + inputFileName + "\" does not exist");
        } catch (IOException e) {
            Logger.error("Error: IO error " + e);
        } catch (OberonException e) {
            Logger.error(""  + e);
        } catch (Exception e) {
            Logger.error("Error: Unexpected error " + e);
        }
    }
}
