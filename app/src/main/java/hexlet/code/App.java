package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;

import static hexlet.code.Differ.generate;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")

public class App implements Runnable {

    @Parameters(index = "0", description = "Path to first file")
    private String filePath1;
    @Parameters(index = "1", description = "Path to second file")
    private String filePath2;

    @Option(names = {"-f", "--format"}, description = "Output format [default: stylish]", defaultValue = "stylish")
    private  String formatName;
    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.", help = true)
    private boolean helpRequested;
    @Option(names = {"-V", "--version"}, description = "Print version information and exit.", versionHelp = true)
    private boolean versionRequested;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public final void run() {

        try {
            String diffString = generate(filePath1, filePath2, formatName);
            System.out.println(diffString);
        } catch (IOException e) {
            System.out.println("Can't read file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Comparison error: " + e.getMessage());
        }
    }
}
