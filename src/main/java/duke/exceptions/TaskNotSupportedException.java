package duke.exceptions;

/**
 * The TaskNotSupportedException class provides an exception.
 *
 * @author Ryan NgWH
 */
public class TaskNotSupportedException extends Exception {
  /**
   * Constructor for a TaskNotSupportedException
   *
   * @param errorMessage Error message
   */
  public TaskNotSupportedException(String errorMessage) {
    super(errorMessage);
  }
}
