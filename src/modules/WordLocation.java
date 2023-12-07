package modules;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class description: This class is used to store the location of a word in a
 * document. It stores the document name and the location of the word in the
 * document.
 *
 * @author Isaac, Jayden, David
 */
public class WordLocation implements Serializable {

    /**
     * The serial version UID
     */
    private static final long serialVersionUID = 1L;

    /**
     * the filename uses to retrieve information.
     */
    private String fileName;
    /**
     * the line number of the word in the file.
     */
    private List<Integer> lineNumbers;

    /**
     * Constructor of the WordLocation class.
     *
     * @param fileName the name of the file
     */
    public WordLocation(String fileName) {
        this.fileName = fileName;
        this.lineNumbers = new ArrayList<>();
    }

    /**
     * Get the file name.
     *
     * @return the file name
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Get the line number.
     *
     * @param fileName the name of the file
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the line number.
     *
     * @return the line number
     */
    public List<Integer> getLineNumbers() {
        return lineNumbers;
    }

    /**
     * Set the line number.
     *
     * @param lineNumbers the line number
     */
    public void setLineNumbers(List<Integer> lineNumbers) {
        this.lineNumbers = lineNumbers;
    }
}