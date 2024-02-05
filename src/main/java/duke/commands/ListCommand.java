package duke.commands;

import java.time.Instant;

import duke.exceptions.InvalidArgumentException;
import duke.storage.TaskList;
import duke.ui.Ui;

/**
 * The ListCommand class defines a command to list tasks stored in the Duke
 * application
 *
 * @author Ryan NgWH
 */
public class ListCommand extends Command {
    /**
     * Date filter for the list command
     */
    private Instant date;

    /**
     * Creates a list command without any filters
     */
    public ListCommand() {
        super(false);
        this.date = null;
    }

    /**
     * Creates a list command with a date filter
     *
     * @param date Date to filter
     */
    public ListCommand(Instant date) {
        super(false);
        this.date = date;
    }

    /**
     * Executes the command
     *
     * @param taskList Tasklist used for the command
     *
     * @return String containing the output of the command
     */
    @Override
    public String execute(TaskList taskList) throws InvalidArgumentException {
        String tasks;
        if (this.date != null) {
            tasks = taskList.getTasks(this.date);
        } else {
            tasks = taskList.getTasks();
        }

        return tasks;
    }

    /**
     * Executes the list command
     *
     * @param taskList Tasklist used for the command
     * @param ui       UI used for the command
     */
    @Override
    public void execute(TaskList taskList, Ui ui) throws InvalidArgumentException {
        String successMessage = this.execute(taskList);

        // Print tasks
        System.out.println(successMessage);
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

        if (obj instanceof ListCommand) {
            ListCommand command = (ListCommand) obj;

            if (this.date != null && command.date != null) {
                return super.equals(command) && this.date.equals(command.date);
            } else if (this.date == null && command.date == null) {
                return super.equals(command);
            }
        }

        return false;
    }
}
