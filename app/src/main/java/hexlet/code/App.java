package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "gendiff", description = "Compares two configuration files and shows a difference.")

public class App implements Runnable {

    @Parameters(index = "0", description = "Path to first file")
    private String filePath1;
    @Parameters(index = "1", description = "Path to second file")
    private String filePath2;

    @Option(names = {"-f", "--format"}, description = "Output format [default: stylish]", defaultValue = "stylish")
    private String format;
    @Option(names = {"-h", "--help"}, description = "Show this help message and exit.", help = true)
    private boolean helpRequested;
    @Option(names = {"-V", "--version"}, description = "Print version information and exit.", versionHelp = true)
    private boolean versionRequested;


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        if (helpRequested) {
            CommandLine.usage(this, System.out);
        } else if (versionRequested) {
            System.out.println("Version 1.0.0");
        } else {
            try {
                Differ.generate(filePath1, filePath2);
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }
}
