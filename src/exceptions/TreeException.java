package exceptions;

public class TreeException extends Exception {
    /**
     * Default constructor for the TreeException class.
     */
    private static final long serialVersionUID = 1L;
	public TreeException() {
            super("Tree Exception");
        }
       public TreeException(String message) {
            super(message);
    }

}