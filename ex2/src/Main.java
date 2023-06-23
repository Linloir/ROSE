import java.io.*;
import exceptions.*;
import lexer.*;

public class Main {
    public static void main(String argv[]) {
        if (argv.length == 0) {
            System.out.println("Missing arguments.");
            System.out.println("Usage: java Main <inputfile(s)>");
            System.out.println("    <inputfile(s)>: Input file(s) to be scanned, e.g. \"test.001\" \"test.002\"");
            return;
        }

        String encodingName = "UTF-8";

        for (int i = 0; i < argv.length; i++) {
            System.out.println("[I] Scanning file \"" + argv[i] + "\"");

            Lexer scanner = null;

            try {
                FileInputStream stream = new FileInputStream(argv[i]);
                Reader reader = new InputStreamReader(stream, encodingName);
                
                scanner = new Lexer(reader);

                do {
                    System.out.println("[+]" + scanner.next_token());
                } while (!scanner.yyatEOF());
            } catch (OberonException e) {
                System.out.println("[E] Lexical error occurred when scanning \"" + argv[i] + "\":");
                System.out.println("[+] " + e);
            } catch (FileNotFoundException e) {
                System.out.println("[E] Input file \"" + argv[i] + "\" does not exists.");
            } catch (IOException e) {
                System.out.println("[E] IO error occurred when scanning \"" + argv[i] + "\":");
                System.out.println("[+] " + e);
            } catch (Exception e) {
                System.out.println("[E] Unexpected exception occurred:");
                e.printStackTrace();
            }
        }
    }
}