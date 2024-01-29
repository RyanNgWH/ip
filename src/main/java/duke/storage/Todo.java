package duke.storage;

/**
 * The Todo class defines a 'ToDo' task used for the application
 *
 * @author Ryan NgWH
 */
public class Todo extends Task {
    /**
     * Create a Todo task
     *
     * @param description Description of the todo task
     */
    public Todo(String description) {
        super(description, TaskType.TODO, false);
    }

    /**
     * Create a Todo task
     *
     * @param description Description of the todo task
     * @param isDone      Status of the task
     */
    public Todo(String description, boolean isDone) {
        super(description, TaskType.TODO, isDone);
    }

    /**
     * String representation of a Todo
     *
     * @return String representation of the Todo
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Indicates whether some other object is "equal to" this command
     *
     * @param obj Object to be checked against
     *
     * @return True if equal, False otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj instanceof Todo) {
            Todo task = (Todo) obj;

            return super.equals(task);
        }

        return false;
    }
}
