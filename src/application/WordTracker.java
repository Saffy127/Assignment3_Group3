package application;

import exceptions.TreeException;
import modules.WordLocation;
import utilities.BSTree;
import utilities.Iterator;
import modules.Word;

import java.io.*;
import java.util.*;

/**
 * The WordTracker program processes text files, building a binary search tree
 * to store and track occurrences of unique words. It records each word's
 * occurrences along with the line numbers and filenames. This program
 * supports serializing the word tree into a binary file and
 * can generate reports based on user input.
 */
public class WordTracker {

    private static final long serialVersionUID = 1L;
    private static String fileOutputName;
    private static String fileInputName;
    private static String userOption;
    static final File REPOSITORY_FILE = new File("ser/repository.ser");

    /**
     * The main method that initiates the WordTracker program.
     * @param args Arguments from the command line to control program behavior.
     */
    public static void main(String[] args) {
        BSTree<Word> wordTree;

        try {
            if (!parseArgs(args)) return;

            Scanner scanner = new Scanner(new File(fileInputName));
            wordTree = initializeWordTree();

            if (fileOutputName != null) {
                redirectOutputToFile();
            }

            processInputFile(scanner, wordTree);
            saveWordTree(wordTree);

            printWordTree(wordTree);
        } catch (IOException | TreeException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Processes the input file, reading words and updating the word tree.
     * @param scanner  Scanner to read the input file.
     * @param wordTree Binary search tree to store the words.
     * @throws TreeException If tree operations encounter issues.
     */
    private static void processInputFile(Scanner scanner, BSTree<Word> wordTree) throws TreeException {
        int lineCount = 0;

        while (scanner.hasNextLine()) {
            lineCount++;
            String line = sanitizeLine(scanner.nextLine());

            for (String wordStr : line.split("\\s+")) {
                if (wordStr.isEmpty()) continue;

                Word word = new Word(wordStr.toLowerCase());
                word.addCount(lineCount, fileInputName);

                if (wordTree.isEmpty() || wordTree.search(word) == null) {
                    wordTree.add(word);
                } else {
                    wordTree.search(word).getElement().addCount(lineCount, fileInputName);
                }
            }
        }
    }

    /**
     * Sanitizes a line of text by removing non-letter characters.
     * @param line The line to be sanitized.
     * @return The sanitized line.
     */
    private static String sanitizeLine(String line) {
        return line.replaceAll("[^a-zA-Z\\s]", " ").trim();
    }

    /**
     * Initializes the word tree from the repository if it exists, or creates a new one.
     * @return The initialized or new word tree.
     * @throws IOException If there is an I/O issue.
     * @throws ClassNotFoundException If the class for a serialized object cannot be found.
     */
    private static BSTree<Word> initializeWordTree() throws IOException, ClassNotFoundException {
        BSTree<Word> wordTree;
        if (REPOSITORY_FILE.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(REPOSITORY_FILE))) {
                wordTree = (BSTree<Word>) ois.readObject();
            }
        } else {
            wordTree = new BSTree<>();
        }
        return wordTree;
    }

    /**
     * Redirects the standard output to a file.
     * @throws FileNotFoundException If the specified file cannot be found.
     */
    private static void redirectOutputToFile() throws FileNotFoundException {
        System.setOut(new PrintStream(new FileOutputStream(fileOutputName)));
    }

    /**
     * Prints the contents of the word tree.
     * @param wordTree The word tree to print.
     */
    private static void printWordTree(BSTree<Word> wordTree) {
        System.out.println("The number of words in the file is " + wordTree.size());

        Iterator<Word> iterator = wordTree.inorderIterator();
        while (iterator.hasNext()) {
            printWordDetails(iterator.next());
        }
    }

    /**
     * Prints details of a word, such as file names and line numbers.
     * @param word The word to print details for.
     */
    private static void printWordDetails(Word word) {
        System.out.println("Word: " + word.getWord());
        for (WordLocation wl : word.getWordLocations()) {
            String fileName = extractFileName(wl.getFileName());
            System.out.println("File name: " + fileName);

            if ("l".equals(userOption) || "o".equals(userOption)) {
                wl.getLineNumbers().forEach(lineNumber -> System.out.println("Line number: " + lineNumber));
            }

            if ("o".equals(userOption)) {
                System.out.println("Occurrence: " + word.getCount());
            }
        }
    }

    /**
     * Extracts the file name from a file path.
     * @param fullPath The full path of the file.
     * @return The name of the file.
     */
    private static String extractFileName(String fullPath) {
        return fullPath.substring(fullPath.lastIndexOf('/') + 1);
    }

    /**
     * Saves the word tree to the repository file.
     * @param wordTree The word tree to save.
     * @throws IOException If there is an I/O issue.
     */
    private static void saveWordTree(BSTree<Word> wordTree) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(REPOSITORY_FILE))) {
            oos.writeObject(wordTree);
        }
    }

    /**
     * Parses command-line arguments to set program parameters.
     * @param args The command-line arguments.
     * @return true if arguments are valid, false otherwise.
     */
    private static boolean parseArgs(String[] args) {
        if (args.length < 2 || args.length > 4) {
            System.out.println("There were errors in the arguments");
            return false;
        }

        fileInputName = args[0].toLowerCase();
        for (int i = 1; i < args.length; i++) {
            if (args[i].startsWith("-f")) {
                fileOutputName = args[i + 1];
            } else if (args[i].startsWith("-p") && args[i].length() == 3) {
                userOption = args[i].substring(2);
            }
        }
        return true;
    }
}

