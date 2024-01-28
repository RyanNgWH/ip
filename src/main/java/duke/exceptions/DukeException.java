package duke.exceptions;

/**
 * The DukeException class defines an exception specific to the Duke
 * application.
 *
 * @author Ryan NgWH
 */
public class DukeException extends Exception {
	/**
	 * Creates a DukeException
	 *
	 * @param errorMessage Error message
	 */
	public DukeException(String errorMessage) {
		super(errorMessage);
	}
}
