import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		// if (argv.length == 0)
		// {
		// 	System.out.println("Usage : java Main <inputfile>");
		// }
		// else
		// {
		// 	for (int i = 0; i < argv.length; i++) 
		// 	{
		// 		Lexer obj = new Lexer(new java.io.FileReader(argv[i]));
		// 		Parser p=new Parser(obj);
		// 		System.out.println(argv[i] + ":");
		// 		try
		// 		{	
		// 			p.parse();
		// 		}
		// 		catch(Exception ex)
		// 		{
		// 			System.out.println(obj.get_line()+" line "+ obj.get_column()+" column");
		// 			System.out.println(ex.getMessage());
		// 		}
		// 		System.out.println();
		// 	}
		// }

		if (args.length == 0) {
			System.out.println("[E] Error: Missing input file");
			System.out.println("\nUsage: java Main [arguments] <inputfile>");
			System.out.println("argumets:");
			System.out.println("\t-h, --help: Print this help message");
			System.out.println("\t-e, --encoding <encoding>: Specify the encoding of the input file");
			System.out.println("\t\t<encoding>: The encoding of the input file, e.g. \"UTF-8\"");
			return;
		}

		String encodingName = "UTF-8";
		String inputFileName = null;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-h") || args[i].equals("--help")) {
				System.out.println("Usage: java Main [arguments] <inputfile>");
				System.out.println("argumets:");
				System.out.println("\t-h, --help: Print this help message");
				System.out.println("\t-e, --encoding <encoding>: Specify the encoding of the input file");
				System.out.println("\t\t<encoding>: The encoding of the input file, e.g. \"UTF-8\"");
				return;
			} else if (args[i].equals("-e") || args[i].equals("--encoding")) {
				if (i + 1 < args.length) {
					encodingName = args[++i];
				} else {
					System.out.println("[E] Error: Missing encoding name");
					System.out.println("\nUsage: java Main [arguments] <inputfile>");
					System.out.println("argumets:");
					System.out.println("\t-h, --help: Print this help message");
					System.out.println("\t-e, --encoding <encoding>: Specify the encoding of the input file");
					System.out.println("\t\t<encoding>: The encoding of the input file, e.g. \"UTF-8\"");
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
			System.out.println("\t-h, --help: Print this help message");
			System.out.println("\t-e, --encoding <encoding>: Specify the encoding of the input file");
			System.out.println("\t\t<encoding>: The encoding of the input file, e.g. \"UTF-8\"");
			return;
		}
		
		Lexer obj = new Lexer(new InputStreamReader(new FileInputStream(inputFileName), encodingName));
		Parser p = new Parser(obj);

		try {
			p.parse();
		} catch (Exception e) {
			System.out.println("[E] Syntax error at line " + obj.get_line() + ", column " + obj.get_column());
			System.out.println("[+] " + e.getMessage());
		}
	}
}