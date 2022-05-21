import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.ArrayList;
import java.util.List;

/* Выделение из текстового файла его конца некоторого размера:
        ● fileN задаёт имя входного файла. Если параметр отсутствует, следует
        считывать входные данные с консольного ввода. Если параметров несколько,
        то перед выводом для каждого файла следует вывести его имя в отдельной
        строке.
        ● Флаг -o ofile задаёт имя выходного файла (в данном случае ofile). Если
        параметр отсутствует, следует выводить результат на консольный вывод.
        ● Флаг -с num, где num это целое число, говорит о том, что из файла нужно
        извлечь последние num символов.
        ● Флаг -n num, где num это целое число, говорит о том, что из файла нужно
        извлечь последние num строк.
        Command line: tail [-c num|-n num] [-o ofile] file0 file1 file2 …
        В случае, когда какое-нибудь из имён файлов неверно или указаны одновременно
        флаги -c и -n, следует выдать ошибку. Если ни один из этих флагов не указан, следует
        вывести последние 10 строк.
        Кроме самой программы, следует написать автоматические тесты к ней.*/

public class TailLauncher {
    @Option(name = "-o", usage = "output file name")
    public String outputFileName;

    @Option(name = "-c", usage = "extract last num symbols")
    public int symbolsToExtract = -1;

    @Option(name = "-n", usage = "extract last num strings", forbids = {"-c"})
    public int stringsToExtract = 10;

    @Argument()
    public List<String> fileNames = new ArrayList<>();

    public static void main(String[] args) {
        new TailLauncher().launch(args);
    }

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);

        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("java -jar Tail.jar [-c num|-n num] [-o ofile] file0 file1 file2 ... ");
            parser.printUsage(System.err);
            return;
        }

        Tail tail = new Tail();

        if (!fileNames.isEmpty()) {
            for (String fileName : fileNames) {
                ArrayList<String> strings = tail.readFromFile(fileName);
                if (outputFileName != null) new Tail().setOutToFile(outputFileName);
                System.out.println(fileName);
                if (symbolsToExtract != -1)
                    tail.extractSymbols(strings, symbolsToExtract);
                else
                    tail.extractStrings(strings, stringsToExtract);
            }
        }
        else {
            List<String> strings = tail.readFromConsole();
            if (outputFileName != null) new Tail().setOutToFile(outputFileName);
            if (symbolsToExtract != -1)
                tail.extractSymbols(strings, symbolsToExtract);
            else
                tail.extractStrings(strings, stringsToExtract);
        }
    }
}